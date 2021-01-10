package com.tianhe.writtentest.sort.tree2;

import java.util.*;

/**
 * @author finley tianhe
 * @date 2021/01/05
 * @description
 */
public class ListTree {

    public static void main(String[] args) {

        /**
         * 遍历二叉树
         *
         * 深度优先
         * 前序遍历，先访问根节点，然后前序遍历左子树，再前序遍历右子树
         * 中序遍历，中序遍历根节点的左子树，然后是访问根节点，最后遍历右子树
         * 后序遍历，从左到右先叶子后节点的方式访问左右子树，最后访问根节点
         *
         * 广度优先
         * 层序遍历，从根节点从上往下逐层遍历，在同一层，按从左到右的顺序对节点逐个访问
         */
    }

//    前序遍历，复杂度分析，时间复杂度，o(n) n是二叉树的节点数，每个节点被遍历一次，空间复杂度 o(n) 迭代过程中显示栈的开销，平均情况下 o(log n)，最坏情况呈现链状 o(n)
    public static List<Integer> preOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

//   中序遍历， 迭代 栈，时间复杂度 o(n) n为二叉树节点的个数，空间复杂度 o(n) 空间复杂度取决于栈深度，栈深度在为一条链表的情况下达到o(n)
    public static List<Integer> inOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        while (root != null || !stk.isEmpty()){
            while (root != null){
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

//    后序遍历，迭代、栈
    public static List<Integer> postOrder(TreeNode root){
        LinkedList<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                result.addFirst(root.val);
                root = root.right;
            }else{
                root = stack.pop();
                root = root.left;
            }
        }
        return result;
    }

//    层序遍历，时间复杂度、空间复杂度 o(n)
    public static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> ret = new ArrayList<>();
        if(root != null){
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int currLevelSize = queue.size();
            for (int i = 1;i < currLevelSize; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return ret;
    }
}
