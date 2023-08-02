package com.zp.common.utils;

import com.zp.common.entity.ITreeNode;
import com.zp.common.entity.TreeJson;
import com.zp.common.entity.TreeNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeUtils {


    public static List<TreeJson> makeTree(List<TreeJson> treeList) {
        return toTree(treeList);
    }


    public static Collection<TreeJson> getChildren(List<TreeJson> list, String id) {
        return buildChildren(list, id, false);
    }


    public static <T extends ITreeNode> List<T> toTree(Collection<T> source) {
        return toTree(source, false);
    }


    public static <T extends ITreeNode> List<T> sortTree(List<T> source) {
        source.sort(new Comparator<ITreeNode>() {
            public int compare(ITreeNode o1, ITreeNode o2) {
                int o1sort = o1.getSort();
                int o2sort = o2.getSort();
                return o1sort >= o2sort ? 1 : 0;
            }
        });
        Iterator var1 = source.iterator();

        while (var1.hasNext()) {
            ITreeNode treeNode = (ITreeNode) var1.next();
            if (CollectionUtils.isNotEmpty(treeNode.getChildren())) {
                treeNode.setChildren(sortChildren(new LinkedHashSet(treeNode.getChildren())));
            }
        }

        return source;
    }

    public static <T extends ITreeNode> Collection<T> sortChildren(Collection<T> childrenNodes) {
        List<T> result = new ArrayList();


        for (Iterator<T> var2 = childrenNodes.iterator(); var2.hasNext(); ) {
            T treeNode = var2.next();
            if (CollectionUtils.isNotEmpty(treeNode.getChildren())) {
                treeNode.setChildren(sortChildren(treeNode.getChildren()));
            }
            result.add(treeNode);
        }


        if (CollectionUtils.isEmpty(result)) {
            return null;
        } else {
            result.sort(new Comparator<ITreeNode>() {
                public int compare(ITreeNode o1, ITreeNode o2) {
                    int o1sort = o1.getSort();
                    int o2sort = o2.getSort();
                    return o1sort - o2sort;
                }
            });
            return new LinkedHashSet(result);
        }
    }


    public static <T extends ITreeNode> List<T> toTree(Collection<T> source, boolean sortEnable) {
        return toTree(source, sortEnable, false);
    }


    public static <T extends ITreeNode> List<T> toTree(Collection<T> source, boolean sortEnable, boolean childrenEmptyToNull) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        } else {
            List<T> resultTree = new ArrayList();
            List<T> roots = findRootNode(source);
            Iterator<T> var5 = roots.iterator();

            while (var5.hasNext()) {
                T root = var5.next();
                root.setChildren(buildChildren(source, root.getTreeNodeId(), sortEnable, childrenEmptyToNull));
                resultTree.add(root);
            }

            if (sortEnable) {
                resultTree.sort(new Comparator<ITreeNode>() {
                    public int compare(ITreeNode o1, ITreeNode o2) {
                        return Collator.getInstance(Locale.CHINA).compare(o1.getTreeNodeName(), o2.getTreeNodeName());
                    }
                });
            }

            return resultTree;
        }
    }

    public static <T extends ITreeNode> List<T> findRootNode(Collection<T> nodes) {
        List<T> roots = new ArrayList();
        Iterator<T> var2 = nodes.iterator();

        while (true) {
            while (var2.hasNext()) {
                T node = var2.next();
                boolean hasParent = false;
                if (!StringUtils.isEmpty(node.getTreeNodeParent()) && !"0".equals(node.getTreeNodeParent())) {
                    Iterator<T> var5 = nodes.iterator();

                    while (var5.hasNext()) {
                        T parent = var5.next();
                        if (node.getTreeNodeParent().equalsIgnoreCase(parent.getTreeNodeId())) {
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
    }


    public static <T extends ITreeNode> Collection<T> buildChildren(Collection<T> nodes, String parentId, boolean childrenEmptyToNull) {
        return buildChildren(nodes, parentId, true, childrenEmptyToNull);
    }


    public static <T extends ITreeNode> Collection<T> buildChildren(Collection<T> nodes, String parentId, boolean sortEnable, boolean childrenEmptyToNull) {
        if (CollectionUtils.isEmpty(nodes)) {
            return Collections.EMPTY_SET;
        } else {
            Set<T> childrenNodes = sortEnable ? new TreeSet(new Comparator<ITreeNode>() {
                public int compare(ITreeNode o1, ITreeNode o2) {
                    return Collator.getInstance(Locale.CHINA).compare(o1.getTreeNodeName(), o2.getTreeNodeName());
                }
            }) : new LinkedHashSet();
            Iterator<T> var5 = nodes.iterator();

            while (var5.hasNext()) {
                T node = var5.next();
                if (!StringUtils.isEmpty(parentId) && parentId.equals(node.getTreeNodeParent())) {
                    node.setChildren(buildChildren(nodes, node.getTreeNodeId(), sortEnable, childrenEmptyToNull));
                    ((Set) childrenNodes).add(node);
                }
            }

            if (childrenEmptyToNull && CollectionUtils.isEmpty((Collection) childrenNodes)) {
                return null;
            } else {
                return (Collection) childrenNodes;
            }
        }
    }

    public static <T extends ITreeNode> List<T> searchLeaf(List<T> tree) {
        List<T> leafs = new ArrayList();
        if (CollectionUtils.isEmpty(tree)) {
            return leafs;
        } else {
            Iterator<T> var2 = tree.iterator();

            while (var2.hasNext()) {
                T node = var2.next();
                if (CollectionUtils.isEmpty(node.getChildren())) {
                    leafs.add(node);
                } else {
                    leafs.addAll(searchLeaf(new ArrayList(node.getChildren())));
                }
            }

            return leafs;
        }
    }


    public static <T extends TreeNode> T getDirectNode(List<T> objs) {
        List<T> nodes = searchLeaf(toTree(objs));
        return CollectionUtils.isEmpty(nodes) ? null : nodes.get(0);
    }

    public static <T extends TreeNode> T getDirectNodeByType(List<T> objs, String type) {
        List<T> trees = toTree(objs);
        return CollectionUtils.isEmpty(trees) ? null : searchByType(trees.get(0), type);
    }

    private static <T extends TreeNode> T searchByType(T source, String type) {
        T result = null;
        if (source.getTreeNodeType().equals(type)) {
            result = source;
        }

        Set<T> children = (Set) source.getChildren();
        if (children != null) {
            Iterator<T> var4 = children.iterator();

            while (var4.hasNext()) {
                T node = var4.next();
                T n = searchByType(node, type);
                if (n != null) {
                    result = n;
                }
            }
        }

        return result;
    }


    public static <T extends TreeNode> List<T> findParentNodes(List<T> sources, T node) {
        return sources.stream().filter(item -> node.getTreeNodeParent().equals(item.getTreeNodeId())).collect(Collectors.toList());
    }


    private static <T extends TreeNode> T findParentNode(List<T> sources, T sonNode) {
        List<T> collect = sources.stream().filter(item -> sonNode.getTreeNodeParent().equals(item.getTreeNodeId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return null;
        }
        return collect.get(0);

    }

}


