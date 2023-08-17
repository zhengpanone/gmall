package com.zp.sys.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zp.common.page.PageData;
import com.zp.common.security.password.PasswordUtils;
import com.zp.common.security.user.SecurityUser;
import com.zp.common.security.user.UserDetail;
import com.zp.sys.model.dto.SysUserDTO;
import com.zp.sys.model.entity.SysUserEntity;
import com.zp.sys.model.enums.SuperAdminEnum;
import com.zp.sys.mapper.SysUserMapper;
import com.zp.sys.service.SysDeptService;
import com.zp.sys.service.SysRoleUserService;
import com.zp.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {
    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysDeptService sysDeptService;

    @Override
    public PageData<SysUserDTO> page(Map<String, Object> params) {
        //转换成like
        /*paramsToLike(params, "username");

        //分页
        IPage<SysUserEntity> page = getPage(params, Constant.CREATE_DATE, false);

        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }

        //查询
        List<SysUserEntity> list = mapper.selectListByMap(params);

        return getPageData(list, page.getTotal(), SysUserDTO.class);*/
        return null;
    }

    @Override
    public List<SysUserDTO> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }

        List<SysUserEntity> entityList = mapper.selectListByMap(params);

//        return ConvertUtils.sourceToTarget(entityList, SysUserDTO.class);
        return null;
    }

    @Override
    public SysUserDTO get(Long id) {
        SysUserEntity entity = mapper.selectOneById(id);

//        return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
        return null;
    }

    @Override
    public SysUserDTO getByUsername(String username) {
        SysUserEntity entity = mapper.getByUsername(username);
        //return ConvertUtils.sourceToTarget(entity, SysUserDTO.class);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDTO dto) {
       /* SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

        //密码加密
        String password = PasswordUtils.encode(entity.getPassword());
        entity.setPassword(password);

        //保存用户
        entity.setSuperAdmin(SuperAdminEnum.NO.value());
        save(entity);

        //保存角色用户关系
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserDTO dto) {
        /*SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);

        //密码加密
        if (StringUtils.isBlank(dto.getPassword())) {
            entity.setPassword(null);
        } else {
            String password = PasswordUtils.encode(entity.getPassword());
            entity.setPassword(password);
        }

        //更新用户
        updateById(entity);

        //更新角色用户关系
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());*/
    }

    @Override
    public void delete(Long[] ids) {
        //删除用户
        mapper.deleteBatchByIds(Arrays.asList(ids));

        //删除角色用户关系
        sysRoleUserService.deleteByUserIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);

        mapper.updatePassword(id, newPassword);
    }

    @Override
    public int getCountByDeptId(Long deptId) {
        return mapper.getCountByDeptId(deptId);
    }

    @Override
    public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
        return mapper.getUserIdListByDeptId(deptIdList);
    }

}
