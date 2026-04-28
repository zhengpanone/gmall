package com.zp.gmall.module.system.controller.admin.permission;

import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.common.domain.vo.TreeSelectVO;
import com.zp.gmall.module.system.controller.admin.permission.dto.MenuDTO;
import com.zp.gmall.module.system.controller.admin.permission.vo.MenuVO;
import com.zp.gmall.module.system.controller.admin.permission.vo.RouteVO;
import com.zp.gmall.module.system.service.permission.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
@Validated
@Tag(name = "管理后台 - 菜单")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService menuService;

    @Operation(summary = "新增菜单")
    @PostMapping("/create")
    public Result<?> add(@Valid @RequestBody MenuDTO dto) {
        if (!menuService.checkMenuKeyUnique(dto)) {
            return Result.failed("新增菜单'" + dto.getName() + "'失败，菜单标识已存在");
        }
        menuService.addMenu(dto);
        return Result.ok();
    }

    @Operation(summary = "修改菜单")
    @PutMapping("/update")
    public Result<Void> edit(@Valid @RequestBody MenuDTO dto) {
        if (!menuService.checkMenuKeyUnique(dto)) {
            return Result.failed("修改菜单'" + dto.getName() + "'失败，菜单标识已存在");
        }
        if (dto.getId().equals(dto.getParentId())) {
            return Result.failed("修改菜单'" + dto.getName() + "'失败，上级菜单不能选择自己");
        }
        menuService.updateMenu(dto);
        return Result.ok();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/delete/{id}")
    public Result<Void> remove(
            @PathVariable @Parameter(description = "菜单ID", required = true, example = "1") String id) {
//        if (menuService.hasChildByMenuId(menuId)) {
//            return Result.failed("存在子菜单，不允许删除");
//        }
//        if (menuService.checkMenuExistRole(menuId)) {
//            return Result.failed("菜单已分配给角色，不允许删除");
//        }
//        menuService.deleteMenu(menuId);
        return Result.ok();
    }

    /**
     * 获取用户菜单
     */
    @GetMapping("/list")
    public Result<List<MenuVO>> getMenuList() {
        // 获取当前用户ID
        String userId = "1";

        // 查询用户菜单
        List<MenuVO> menus = menuService.getMenuByUserId(userId);

        // 构建树形结构
        List<MenuVO> menuTree = buildMenuTree(menus, "0");
        return Result.ok(menuTree);
    }

    /**
     * 获取用户权限码
     */
    @GetMapping("/perm")
    public Result<List<String>> getPermCode() {
        String userId = "0";
        List<String> perms = menuService.getPermsByUserId(userId);
        return Result.ok(perms);
    }

    /**
     * 获取路由列表
     */
    @GetMapping("/routes")
    public Result<List<RouteVO>> getRoutes() {
        String userId = "";
        List<RouteVO> routes = menuService.getRoutesByUserId(userId);
        return Result.ok(routes);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/options")
    @Operation(summary = "获取菜单下拉树列表")
    public Result<List<TreeSelectVO>> getMenuOptions() {
        List<TreeSelectVO> options = menuService.getMenuTreeSelect();
        return Result.ok(options);
    }

    // 构建菜单树
    private List<MenuVO> buildMenuTree(List<MenuVO> menus, String parentId) {
        return menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .peek(menu -> menu.setChildren(buildMenuTree(menus, menu.getId())))
                .sorted(Comparator.comparing(MenuVO::getSort))
                .collect(Collectors.toList());
    }
}