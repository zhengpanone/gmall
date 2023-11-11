package com.zp.framework.common.utils.tree;

import java.util.Collection;

/**
 * Author : zhengpanone
 * Date : 2023/11/10 23:16
 * Version : v1.0.0
 
 */
public interface ITreeNode {
    String DEFAULT_ROOT="0";
    void setTreeNodeId(String id);
    void setTreeNodeName(String name);
    void setTreeNodeParent(String pid);
    void setTreeNodeType(String type);
    void setTreeNodeBizType(String bizType);
    void setChildren(Collection<? extends ITreeNode> children);
    void setSort(String sort);
    String getSort();
    String getTreeNodeId();
    String getTreeNodeName();
    String getTreeNodeParent();
    String getTreeNodeType();
    String getTreeNodeBizType();
    Collection<? extends ITreeNode> getChildren();

}
