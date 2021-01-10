package com.tianhe.writtentest.sort.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * Created by tianhe on 2020/2/19.
 */
public class MidSort {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        //    左子树-根-右子树
        public List < Integer > inorderTraversal(TreeNode root) {
            List< Integer > res = new ArrayList< >();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List < Integer > res) {
            if (root != null) {
                if (root.left != null) {
                    helper(root.left, res);
                }
                res.add(root.val);
                if (root.right != null) {
                    helper(root.right, res);
                }
            }
        }
    }
}


