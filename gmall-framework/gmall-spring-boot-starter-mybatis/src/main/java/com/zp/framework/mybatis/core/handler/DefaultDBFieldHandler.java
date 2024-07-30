package com.zp.framework.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zp.framework.mybatis.core.dataobject.BaseDO;
import com.zp.framework.web.core.util.WebFrameworkUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 21:51
 * Version : v1.0.0
 * Description: 通用参数填充实现类
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO) {
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseDO.getCreateTime())) {
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }
            String userId = WebFrameworkUtils.getLoginUserId();
            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
            if (StringUtils.isNoneBlank(userId) && StringUtils.isBlank(baseDO.getCreator())) {
                baseDO.setCreator(userId);
            }
            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
            if (StringUtils.isNotEmpty(userId) && StringUtils.isBlank(baseDO.getUpdater())) {
                baseDO.setUpdater(userId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(updateTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        Object updater = getFieldValByName("updater", metaObject);
        String userId = WebFrameworkUtils.getLoginUserId();
        if (Objects.nonNull(userId) && Objects.isNull(updater)) {
            setFieldValByName("updater", userId, metaObject);
        }
    }
}
