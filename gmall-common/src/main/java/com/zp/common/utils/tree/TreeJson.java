package com.zp.common.utils.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Author : zhengpanone
 * Date : 2023/11/10 23:34
 * Version : v1.0.0
 * Description: TODO
 */
@ApiModel(description = "树形节点")
@NoArgsConstructor
@Getter
public class TreeJson extends TreeNode {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "节点ID", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String id;
    @ApiModelProperty(value = "节点名称", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String name;
    @ApiModelProperty(value = "父节点ID", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String parentId;
    @ApiModelProperty(value = "节点类型", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String type;
    @ApiModelProperty(value = "编码", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String code;
    @ApiModelProperty(value = "选中", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String selFag;


    public TreeJson(String id, String code, String name, String type, String parentId) {
        this.setId(id);
        this.setCode(code);
        this.setName(name);
        this.setType(type);
        this.setParentId(parentId);
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        this.setSelFag(selected ? "1" : "0");
    }
    public void setId(String id) {
        this.id = id;
        this.setTreeNodeId(id);
    }

    public void setName(String name) {
        this.name = name;
        this.setTreeNodeName(name);
    }
    public void setCode(String code) {
        this.code =code;
    }
    public void setType(String type) {
        this.type =type;
        this.setTreeNodeType(type);
    }
    public void setSelFag(String selFag){
        this.selFag = selFag;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
        this.setTreeNodeParent(parentId);
    }

}
