package com.zp.common.utils.tree;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 树节点，所有需要实现树节点的，都需要继承该类
 *
 * @author zhengpanone@hotmail.com
 * @since 1.0.0
 */
@Data
public abstract class TreeNode implements ITreeNode,Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "节点ID", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String treeNodeId;

    @ApiModelProperty(value = "节点名称", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String treeNodeName;
    @ApiModelProperty(value = "父节点ID", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String treeNodeParent;
    @ApiModelProperty(value = "节点类型", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String treeNodeType;
    @ApiModelProperty(value = "节点业务类型", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String treeNodeBizType;

    @ApiModelProperty(value = "子节点", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Collection<? extends ITreeNode> children;
    @ApiModelProperty(value = "排序", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private String sort;

    private boolean selected;

    private String sonNum;

    public TreeNode(){
        this.children = Collections.EMPTY_LIST;
        this.sort="0";
        this.selected = false;
        this.sonNum="0";
    }

    public TreeNode(String treeNodeId, String treeNodeName, String treeNodeParent, String treeNodeType, String treeNodeBizType) {
        this.children = Collections.EMPTY_LIST;
        this.sort = "0";
        this.selected = false;
        this.sonNum = "0";
        this.setTreeNodeId(treeNodeId);
        this.setTreeNodeName(treeNodeName);
        this.setTreeNodeParent(treeNodeParent);
        this.setTreeNodeType(treeNodeType);
        this.setTreeNodeBizType(treeNodeBizType);
    }

    public void setChildren(Collection<? extends ITreeNode> children){
        this.children = children;
    }

    public void setTreeNodeParent(String treeNodeParent) {
        this.treeNodeParent = treeNodeParent;
    }


}