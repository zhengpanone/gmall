package com.zp.common.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Data
public abstract class TreeNode implements ITreeNode, Serializable {

    @ApiModelProperty(value = "节点ID")
    private String treeNodeId;
    @ApiModelProperty(value = "节点名称")
    private String treeNodeName;
    @ApiModelProperty(value = "父节点ID")
    private String treeNodeParent;
    @ApiModelProperty(value = "节点类型")
    private String treeNodeType;
    @ApiModelProperty(value = "节点业务类型")
    private String treeNodeBizType;
    @ApiModelProperty(value = "子节点")
    private Collection children;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    private boolean selected;
    private String sonNum;


    public TreeNode() {
        this.children = Collections.EMPTY_LIST;
        this.sort = 0;
        this.selected = false;
        this.sonNum = "0";
    }

    public TreeNode(String treeNodeId, String treeNodeName, String treeNodeParent, String treeNodeType, String treeNodeBizType) {
        this.children = Collections.EMPTY_LIST;
        this.sort = 0;
        this.selected = false;
        this.sonNum = "0";
        this.setTreeNodeId(treeNodeId);
        this.setTreeNodeName(treeNodeName);
        this.setTreeNodeParent(treeNodeParent);
        this.setTreeNodeType(treeNodeType);
        this.setTreeNodeBizType(treeNodeBizType);
    }
}

