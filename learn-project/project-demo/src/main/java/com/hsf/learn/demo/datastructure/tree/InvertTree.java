package com.hsf.learn.demo.datastructure.tree;

public class InvertTree {

    /**
     * 前序遍历翻转
     *
     * 简单理解：先交换左右子树，再分别在左右子树中迭代交换
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    /**
     * 中序遍历翻转
     * 简单理解：左子树先交换，然后交换左右子树，因为这样，交换好的左子树成为了右子树，原来的右子树成为了左子树，
     * 因此，继续交换左子树（原来未交换的右子树）
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return null;
        invertTree2(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree2(root.left);
        return root;
    }
    /**
     * 后序遍历翻转
     * 简单理解：先分别在左右子树中迭代交换，然后再交换左右子树
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if(root == null) return null;
        invertTree2(root.left);
        invertTree2(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
