package com.zp.gmall.framework.common.util.tree;


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

    private static final String ROOT_PARENT_ID = "0";

    private TreeUtils() {
    }

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
        }
        List<T> resultTree = new ArrayList<>();
        List<T> roots = findRootNode(source);
        for (T root : roots) {
            root.setChildren(buildChildren(source, root.getTreeNodeId(), sortEnabled, childrenEmptyToNull));
            resultTree.add(root);
        }
        if (sortEnabled) {
            resultTree.sort(TreeUtils::compareTreeNode);
        }
        return resultTree;
    }

    public static <T extends ITreeNode> List<T> findRootNode(@NotNull Collection<T> nodes) {
        List<T> roots = new ArrayList<>();
        Set<String> nodeIds = new HashSet<>();
        for (T node : nodes) {
            if (StringUtils.isNotEmpty(node.getTreeNodeId())) {
                nodeIds.add(node.getTreeNodeId());
            }
        }
        for (T node : nodes) {
            String parentId = node.getTreeNodeParent();
            if (StringUtils.isEmpty(parentId) || ROOT_PARENT_ID.equals(parentId) || !nodeIds.contains(parentId)) {
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
        }
        List<T> childrenNodes = new ArrayList<>();
        for (T node : nodes) {
            if (StringUtils.isNotEmpty(parentId) && parentId.equals(node.getTreeNodeParent())) {
                node.setChildren(buildChildren(nodes, node.getTreeNodeId(), sortEnable, childrenEmptyToNull));
                childrenNodes.add(node);
            }
        }
        if (sortEnable) {
            childrenNodes.sort(TreeUtils::compareTreeNode);
        }
        if (childrenEmptyToNull && CollectionUtil.isEmpty(childrenNodes)) {
            return null;
        }
        return childrenNodes;
    }

    private static int compareTreeNode(ITreeNode left, ITreeNode right) {
        int result = compareNullableString(left.getTreeNodeName(), right.getTreeNodeName());
        if (result != 0) {
            return result;
        }
        return compareNullableString(left.getTreeNodeId(), right.getTreeNodeId());
    }

    private static int compareNullableString(String left, String right) {
        if (Objects.equals(left, right)) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        return Collator.getInstance(Locale.CHINA).compare(left, right);
    }
}
