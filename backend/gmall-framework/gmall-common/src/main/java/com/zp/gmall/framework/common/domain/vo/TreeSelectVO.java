package com.zp.gmall.framework.common.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 树形选择器视图对象
 */
@Data
@Schema(title = "树形选择器VO", description = "树形选择器视图对象")
public class TreeSelectVO {

    @Schema(title = "节点ID")
    private Long id;

    @Schema(title = "节点标签")
    private String label;

    @Schema(title = "节点值")
    private Long value;

    @Schema(title = "父节点ID")
    private Long parentId;

    @Schema(title = "菜单标识")
    private String menuKey;

    @Schema(title = "是否禁用")
    private Boolean disabled = false;

    @Schema(title = "子节点")
    private List<TreeSelectVO> children;

    public TreeSelectVO() {
    }

    public TreeSelectVO(Long id, String label) {
        this.id = id;
        this.value = id;
        this.label = label;
    }
}