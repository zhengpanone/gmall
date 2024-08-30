package com.zp.framework.datapermission.core.rule.dept;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zp.framework.common.enums.UserTypeEnum;
import com.zp.framework.common.util.collection.CollectionUtils;
import com.zp.framework.common.util.json.JsonUtils;
import com.zp.framework.datapermission.core.rule.DataPermissionRule;
import com.zp.framework.mybatis.core.MyBatisUtils;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.framework.security.core.LoginUser;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import com.zp.module.system.api.permission.PermissionApi;
import com.zp.module.system.api.permission.dto.DeptDataPermissionVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 21:26
 * Version : v1.0.0
 * <p>
 * 基于部门的 {@link DataPermissionRule} 数据权限规则实现
 * <p>
 * 注意，使用 DeptDataPermissionRule 时，需要保证表中有 dept_id 部门编号的字段，可自定义。
 * <p>
 * 实际业务场景下，会存在一个经典的问题？当用户修改部门时，冗余的 dept_id 是否需要修改？
 * 1. 一般情况下，dept_id 不进行修改，则会导致用户看不到之前的数据。【yudao-server 采用该方案】
 * 2. 部分情况下，希望该用户还是能看到之前的数据，则有两种方式解决：【需要你改造该 DeptDataPermissionRule 的实现代码】
 * 1）编写洗数据的脚本，将 dept_id 修改成新部门的编号；【建议】
 * 最终过滤条件是 WHERE dept_id = ?
 * 2）洗数据的话，可能涉及的数据量较大，也可以采用 user_id 进行过滤的方式，此时需要获取到 dept_id 对应的所有 user_id 用户编号；
 * 最终过滤条件是 WHERE user_id IN (?, ?, ? ...)
 * 3）想要保证原 dept_id 和 user_id 都可以看的到，此时使用 dept_id 和 user_id 一起过滤；
 * 最终过滤条件是 WHERE dept_id = ? OR user_id IN (?, ?, ? ...)
 */
@AllArgsConstructor
@Slf4j
public class DeptDataPermissionRule implements DataPermissionRule {
    /**
     * LoginUser 的 Context 缓存 Key
     */
    protected static final String CONTEXT_KEY = DeptDataPermissionRule.class.getSimpleName();

    private static final String DEPT_COLUMN_NAME = "dept_id";
    private static final String USER_COLUMN_NAME = "user_id";

    static final Expression EXPRESSION_NULL = new NullValue();

    private final PermissionApi permissionApi;

    /**
     * 基于部门的表字段配置
     * 一般情况下，每个表的部门编号字段是 dept_id，通过该配置自定义。
     * <p>
     * key：表名
     * value：字段名
     */
    private final Map<String, String> deptColumns = new HashMap<>();

    /**
     * 基于用户的表字段配置
     * 一般情况下，每个表的部门编号字段是 dept_id，通过该配置自定义。
     * <p>
     * key：表名
     * value：字段名
     */
    private final Map<String, String> userColumns = new HashMap<>();
    /**
     * 所有表名，是 {@link #deptColumns} 和 {@link #userColumns} 的合集
     */
    private final Set<String> TABLE_NAMES = new HashSet<>();

    @Override
    public Set<String> getTableNames() {
        return TABLE_NAMES;
    }

    @Override
    public Expression getExpression(String tableName, Alias tableAlias) {
        // 只有登录用户的情况下，才进行数据权限的处理
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser == null) {
            return null;
        }
        // 只有管理员类型的用户，才进行数据权限的处理
        if (ObjectUtil.notEqual(loginUser.getUserType(), UserTypeEnum.ADMIN.getValue())) {
            return null;
        }
        // 获得数据权限
        DeptDataPermissionVO deptDataPermission = loginUser.getContext(CONTEXT_KEY, DeptDataPermissionVO.class);
        // 从上下文中拿不到，则调用逻辑进行获取
        if (deptDataPermission == null) {
            deptDataPermission = permissionApi.getDeptDataPermission(loginUser.getId()).getCheckedData();
            if (deptDataPermission == null) {
                log.error("[getExpression][LoginUser({}) 获取数据权限为 null", JsonUtils.toJsonString(loginUser));
                throw new NullPointerException(String.format("LoginUser(%s) Table(%s/%s) 未返回数据权限", loginUser.getId(), tableName, tableAlias.getName()));
            }
            // 添加到上下文中，避免重复计算
            loginUser.setContext(CONTEXT_KEY, deptDataPermission);
        }
        // 情况一、如果是ALL，可查看全部，则无需拼接条件
        if (deptDataPermission.getAll()) {
            return null;
        }
        // 情况二、既不能查看部门，又不能查看自己，则说明100%无权限
        if (CollUtil.isEmpty(deptDataPermission.getDeptIds()) && Boolean.FALSE.equals(deptDataPermission.getSelf())) {
            // where null=null,可以保证返回的数据为空
            return new EqualsTo(null, null);
        }
        // 情况三、拼接Dept和User的条件，最后组合
        Expression deptExpression = buildDeptExpression(tableName, tableAlias, deptDataPermission.getDeptIds());
        Expression userExpression = buildUserExpression(tableName, tableAlias, deptDataPermission.getSelf(), loginUser.getId());
        if (deptExpression == null && userExpression == null) {
            log.warn("[getExpression][LoginUser({}) Table({}/{}) DeptDataPermission({}) 构建的条件为空]", JsonUtils.toJsonString(loginUser), tableName, tableAlias, JsonUtils.toJsonString(deptDataPermission));
            return EXPRESSION_NULL;
        }
        if (deptExpression == null) {
            return userExpression;
        }
        if (userExpression == null) {
            return deptExpression;
        }
        // 目前，如果有指定部门 + 可查看自己，采用 OR 条件。即，WHERE (dept_id IN ? OR user_id = ?)
        return new Parenthesis(new OrExpression(deptExpression, userExpression));
    }

    private Expression buildDeptExpression(String tableName, Alias tableAlias, Set<String> deptIds) {
        // 如果不存在配置，则无需作为条件
        String columnName = deptColumns.get(tableName);
        if (StrUtil.isEmpty(columnName)) {
            return null;
        }
        // 如果为空，则无条件
        if (CollUtil.isEmpty(deptIds)) {
            return null;
        }
        // 构造 IN 表达式
        return new InExpression(MyBatisUtils.buildColumn(tableName, tableAlias, columnName), new ExpressionList(CollectionUtils.convertList(deptIds, StringValue::new)));
    }

    private Expression buildUserExpression(String tableName, Alias tableAlias, Boolean self, String userId) {
        // 如果不查看自己，则无需作为条件
        if (Boolean.FALSE.equals(self)) {
            return null;
        }
        String columnName = userColumns.get(tableName);
        if (StrUtil.isEmpty(columnName)) {
            return null;
        }
        return new EqualsTo(MyBatisUtils.buildColumn(tableName, tableAlias, columnName), new StringValue(userId));
    }

    // ==================== 添加配置 ====================
    public void addDeptColumn(Class<? extends BaseDO> entityClass) {
        addDeptColumn(entityClass, DEPT_COLUMN_NAME);
    }

    public void addDeptColumn(Class<? extends BaseDO> entityClass, String columnName) {
        String tableName = TableInfoHelper.getTableInfo(entityClass).getTableName();
        addDeptColumn(tableName, columnName);
    }

    public void addDeptColumn(String tableName, String columnName) {
        deptColumns.put(tableName, columnName);
        TABLE_NAMES.add(tableName);
    }

    public void addUserColumn(Class<? extends BaseDO> entityClass) {
        addUserColumn(entityClass, USER_COLUMN_NAME);
    }

    public void addUserColumn(Class<? extends BaseDO> entityClass, String columnName) {
        String tableName = TableInfoHelper.getTableInfo(entityClass).getTableName();
        addUserColumn(tableName, columnName);
    }

    public void addUserColumn(String tableName, String columnName) {
        userColumns.put(tableName, columnName);
        TABLE_NAMES.add(tableName);
    }


}
