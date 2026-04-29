package com.zp.gmall.module.system.service.permission;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.permission.dto.RoleDTO;
import com.zp.gmall.module.system.controller.admin.permission.dto.RolePageDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RoleVO;
import com.zp.gmall.module.system.entity.permission.RoleDO;
import jakarta.validation.Valid;

/**
 * @author : zhengpanone
 * Date : 2026/4/24 23:39
 * Version : v1.0.0
 * Description:
 */
public interface IRoleService extends IService<RoleDO> {
    /**
     * 创建角色
     *
     * @param roleDTO 角色信息
     * @return 角色编号
     */
    String createRole(@Valid RoleDTO roleDTO);

    /**
     * 获取角色分页
     *
     * @param reqVO 分页参数
     * @return 角色分页
     */
    PageResult<RoleVO> getRolePage(RolePageDTO reqVO);


    /**
     * 通过角色编号获取角色
     *
     * @param roleId 角色编号
     * @return 角色
     */
    RoleVO getRoleById(String roleId);

    /**
     * 更新角色
     *
     * @param roleUpdateDTO 角色信息
     * @return 角色编号
     */
    void updateRole(RoleDTO roleUpdateDTO);

    /**
     * 删除角色
     *
     * @param ids 角色编号
     */
    void deleteRole(Ids ids);
}
