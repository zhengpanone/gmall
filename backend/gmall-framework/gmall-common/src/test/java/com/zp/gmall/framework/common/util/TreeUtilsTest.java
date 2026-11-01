package com.zp.gmall.framework.common.util;

import com.zp.gmall.framework.common.util.tree.ITreeNode;
import com.zp.gmall.framework.common.util.tree.TreeJson;
import com.zp.gmall.framework.common.util.tree.TreeUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 11:28
 * Version : v1.0.0
 
 */
class TreeUtilsTest {

    @Test
    void toTree() {
        List<TreeJson> treeJsonList = new ArrayList<>();
        TreeJson treeJson1 = new TreeJson();
        treeJson1.setId("1");
        treeJson1.setTreeNodeName("root");
        treeJsonList.add(treeJson1);
        TreeJson treeJson2 = new TreeJson();
        treeJson2.setId("2");
        treeJson2.setName("children1");
        treeJson2.setTreeNodeParent("1");
        treeJsonList.add(treeJson2);

        List<TreeJson> tree = TreeUtils.toTree(treeJsonList);

        assertThat(tree).hasSize(1);
        assertThat(tree.get(0).getName()).isEqualTo("root");

        List<ITreeNode> children = new ArrayList<>(tree.get(0).getChildren());
        assertThat(children).hasSize(1);
        assertThat((TreeJson) children.get(0))
                .extracting(TreeJson::getParentId, TreeJson::getName)
                .containsExactly("1", "children1");
    }

    @Test
    void toTreeKeepsSameNameChildrenWhenSortEnabled() {
        List<TreeJson> treeJsonList = new ArrayList<>();
        treeJsonList.add(new TreeJson("1", "root", "root", "1", "0"));
        treeJsonList.add(new TreeJson("2", "child-a", "same", "2", "1"));
        treeJsonList.add(new TreeJson("3", "child-b", "same", "2", "1"));

        List<TreeJson> tree = TreeUtils.toTree(treeJsonList, true);

        assertThat(tree).hasSize(1);
        assertThat(tree.get(0).getChildren()).hasSize(2);
        assertThat(tree.get(0).getChildren())
                .extracting(ITreeNode::getTreeNodeId)
                .containsExactly("2", "3");
    }

    @Test
    void testToTree() {
    }

    @Test
    void testToTree1() {
    }

    @Test
    void findRootNode() {
    }

    @Test
    void buildChildren() {
    }

    @Test
    void testBuildChildren() {
    }
}
