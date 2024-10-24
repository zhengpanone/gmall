package com.zp.framework.common.util.tree;

import io.swagger.v3.oas.annotations.media.Schema;
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