package com.zp.framework.common.util.tree;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * 树节点，所有需要实现树节点的，都需要继承该类
 *
 * @author zhengpanone@hotmail.com
 * @since 1.0.0
 */
@EqualsAndHashCode
public abstract class TreeNode implements ITreeNode, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Schema(description = "节点ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String treeNodeId;

    @Schema(description = "节点名称", accessMode = Schema.AccessMode.READ_ONLY)
    private String treeNodeName;

    @Schema(description = "父节点ID", accessMode = Schema.AccessMode.READ_ONLY)
    private String treeNodeParent;

    @Schema(description = "节点类型", accessMode = Schema.AccessMode.READ_ONLY)
    private String treeNodeType;

    @Schema(description = "节点业务类型", accessMode = Schema.AccessMode.READ_ONLY)
    private String treeNodeBizType;

    @Schema(description = "子节点", accessMode = Schema.AccessMode.READ_ONLY)
    private Collection<? extends ITreeNode> children;

    @Schema(description = "排序", accessMode = Schema.AccessMode.READ_ONLY)
    private String sort;
    @Schema(description = "是否被选中", accessMode = Schema.AccessMode.READ_ONLY)
    private boolean selected;
    @Schema(description = "子孙数量", accessMode = Schema.AccessMode.READ_ONLY)
    private String sonNum;

    public TreeNode() {
        this.children = Collections.emptyList();
        this.sort = "0";
        this.selected = false;
        this.sonNum = "0";
    }

    public TreeNode(String treeNodeId, String treeNodeName, String treeNodeParent, String treeNodeType, String treeNodeBizType) {
        this.children = Collections.emptyList();
        this.sort = "0";
        this.selected = false;
        this.sonNum = "0";
        this.setTreeNodeId(treeNodeId);
        this.setTreeNodeName(treeNodeName);
        this.setTreeNodeParent(treeNodeParent);
        this.setTreeNodeType(treeNodeType);
        this.setTreeNodeBizType(treeNodeBizType);
    }

    @Override
    public String getTreeNodeId() {
        return treeNodeId;
    }

    @Override
    public void setTreeNodeId(String treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    @Override
    public String getTreeNodeName() {
        return treeNodeName;
    }

    @Override
    public void setTreeNodeName(String treeNodeName) {
        this.treeNodeName = treeNodeName;
    }

    @Override
    public void setTreeNodeParent(String treeNodeParent) {
        this.treeNodeParent = treeNodeParent;
    }

    @Override
    public String getTreeNodeParent() {
        return treeNodeParent;
    }

    @Override
    public void setTreeNodeType(String treeNodeType) {
        this.treeNodeType = treeNodeType;
    }

    @Override
    public String getTreeNodeType() {
        return this.treeNodeType;
    }

    @Override
    public String getTreeNodeBizType() {
        return treeNodeBizType;
    }

    @Override
    public void setTreeNodeBizType(String treeNodeBizType) {
        this.treeNodeBizType = treeNodeBizType;
    }


    @Override
    public void setChildren(Collection<? extends ITreeNode> children) {
        this.children = children;
    }

    @Override
    public Collection<? extends ITreeNode> getChildren() {
        return children;
    }

    @Override
    public String getSort() {
        return sort;
    }

    @Override
    public void setSort(String sort) {
        this.sort = sort;
    }

}