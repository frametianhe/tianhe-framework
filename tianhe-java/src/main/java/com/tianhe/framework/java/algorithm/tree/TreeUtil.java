package com.tianhe.framework.java.algorithm.tree;

import java.util.LinkedList;

/**
 * @author finley tianhe
 * @date 2021/01/05
 * @description
 */
public class TreeUtil {

    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.left = createBinaryTree(list);
            node.right = createBinaryTree(list);
        }
        return node;
    }
}
