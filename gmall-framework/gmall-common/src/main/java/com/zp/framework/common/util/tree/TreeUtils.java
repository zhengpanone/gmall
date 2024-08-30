package com.zp.framework.common.util.tree;


import cn.hutool.core.collection.CollectionUtil;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.text.Collator;
import java.util.*;

/**
 * 树形结构工具类，如：菜单、部门等
 *
 * @author zhengpanone
 * @since 1.0.0
 */
public class TreeUtils {

    /**
     * 构建树节点
     */
    public static <T extends ITreeNode> List<T> toTree(Collection<T> source) {
        return toTree(source, false);
    }

    /**
     * 构建树节点
     */
    public static <T extends ITreeNode> List<T> toTree(Collection<T> source, boolean sortEnabled) {
        return toTree(source, sortEnabled, false);
    }

    /**
     * 构建树节点
     */
    public static <T extends ITreeNode> List<T> toTree(Collection<T> source, boolean sortEnabled, boolean childrenEmptyToNull) {
        if (CollectionUtil.isEmpty(source)) {
            return Collections.emptyList();
        } else {
            List<T> resultTree = new ArrayList<>();
            List<T> roots = findRootNode(source);
            for (T root : roots) {
                root.setChildren(buildChildren(source, root.getTreeNodeId(), sortEnabled, childrenEmptyToNull));
                resultTree.add(root);
            }
            if (sortEnabled) {
                resultTree.sort((o1, o2) -> Collator.getInstance(Locale.CHINA).compare(o1.getTreeNodeName(), o2.getTreeNodeName()));
            }
            return resultTree;
        }
    }

    public static <T extends ITreeNode> List<T> findRootNode(@NotNull Collection<T> nodes) {
        List<T> roots = new ArrayList<>();
        for (T node : nodes) {
            boolean hasParent = false;
            if (!StringUtils.isEmpty(node.getTreeNodeParent()) && !"0".equals(node.getTreeNodeParent())) {
                for (T parent : nodes) {
                    if (node.getTreeNodeParent().equals(parent.getTreeNodeId())) {
                        hasParent = true;
                        break;
                    }
                }
                if (!hasParent) {
                    roots.add(node);
                }
            } else {
                roots.add(node);
            }
        }
        return roots;

    }


    public static <T extends ITreeNode> Collection<T> buildChildren(Collection<T> nodes, String parentId, boolean childrenEmptyToNull) {
        return buildChildren(nodes, parentId, true, childrenEmptyToNull);
    }

    public static <T extends ITreeNode> Collection<T> buildChildren(Collection<T> nodes, String parentId, boolean sortEnable, boolean childrenEmptyToNull) {
        if (CollectionUtil.isEmpty(nodes)) {
            return Collections.emptySet();
        } else {
            Set<T> childrenNodes = sortEnable ? new TreeSet<>((Comparator<ITreeNode>) (o1, o2) -> Collator.getInstance(Locale.CHINA).compare(o1.getTreeNodeName(), o2.getTreeNodeName())) : new LinkedHashSet<>();
            for (T node : nodes) {
                if (!StringUtils.isEmpty(parentId) && parentId.equals(node.getTreeNodeParent())) {
                    node.setChildren(buildChildren(nodes, node.getTreeNodeId(), sortEnable, childrenEmptyToNull));
                    childrenNodes.add(node);
                }

            }
            if (childrenEmptyToNull && CollectionUtil.isEmpty(childrenNodes)) {
                return null;
            } else {
                return childrenNodes;
            }


        }
    }
}