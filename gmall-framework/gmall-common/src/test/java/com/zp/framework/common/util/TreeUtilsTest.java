package com.zp.framework.common.util;

import com.zp.framework.common.util.tree.TreeJson;
import com.zp.framework.common.util.tree.TreeUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(tree.toString());
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