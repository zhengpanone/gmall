package com.zp.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "树形节点")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeJson extends TreeNode {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "节点ID")
    private String id;
    @ApiModelProperty(value = "节点名称")
    private String name;
    @ApiModelProperty(value = "父节点ID")
    private String parentId;
    @ApiModelProperty(value = "节点类型")
    private String type;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "选中")
    private String selFlag;

    public TreeJson(String id, String code, String name, String type, String parentId) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setType(type);
        this.setParentId(parentId);
    }
}
