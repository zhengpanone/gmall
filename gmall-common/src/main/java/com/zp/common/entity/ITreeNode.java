package com.zp.common.entity;


import java.util.Collection;

public interface ITreeNode {
    String DEFAULT_ROOT = "0";

    void setTreeNodeId(String var1);

    void setTreeNodeName(String var1);

    void setTreeNodeParent(String var1);

    void setTreeNodeType(String var1);

    void setTreeNodeBizType(String var1);

    void setChildren(Collection<? extends ITreeNode> var1);

    void setSort(Integer var1);

    Integer getSort();

    String getTreeNodeId();

    String getTreeNodeName();

    String getTreeNodeParent();

    String getTreeNodeType();

    String getTreeNodeBizType();

    Collection<? extends ITreeNode> getChildren();
}

