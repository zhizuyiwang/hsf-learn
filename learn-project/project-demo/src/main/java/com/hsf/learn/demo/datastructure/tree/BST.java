package com.hsf.learn.demo.datastructure.tree;


import com.hsf.learn.demo.datastructure.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<E> implements BinaryTreeInfo {


    private int size;
    private Node<E> root;
    private Comparator<E> comparator;
    public BST(){
        this(null);
    }

    public BST(Comparator comparator){
        this.comparator = comparator;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        size = 0;
    }

    public void add(E element){
        elementNotNullCheck(element);
        //添加第一个节点
        if(root == null){
            root = new Node<>(element,null);
            size++;
            //新添加节点之后的调整
            afterAdd(root);
            return;
        }
        //添加不是第一个节点
        //找到父节点
        Node<E> parentNode = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null){
            cmp = compare(element, node.element);
            parentNode = node;
            if(cmp > 0) {
                node = node.rightNode;
            }else if(cmp < 0){
                node = node.leftNode;
            }else{//相等
                node.element = element;
                return;
            }
        }
        Node<E> childNode = new Node<>(element, parentNode);
        //看插入到父节点哪里
        if(cmp > 0){
            parentNode.rightNode = childNode;
        }else if(cmp < 0){
            parentNode.leftNode = childNode;
        }
        size++;
        //新添加节点之后的调整
        afterAdd(childNode);
    }
    /**
     * 添加node之后的调整
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) { }

    public Integer heightRoot(){
        if(root == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        int height = 0;
        int levelSize = 1;
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if(node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if(node.rightNode != null){
                queue.offer(node.rightNode);
            }
            if(levelSize == 0){
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
    public Integer height(){
        return height(root);
    }
    public Integer height(Node<E> node){
        return height(node);
    }

    /**
     * 判断是否是完全二叉树
     * @return
     */
    public boolean isCompletedTree(){
        if(root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()) return false;
            if(node.leftNode != null && node.rightNode != null){
                queue.offer(node.leftNode);
                queue.offer(node.rightNode);
            }else if(node.leftNode == null && node.rightNode != null){
                return false;
            }else{
                if(node.leftNode != null) queue.offer(node.leftNode);
                leaf = true;
            }

        }
        return true;
    }
    /**
     * 判断是否是完全二叉树
     * @return
     */
    public boolean isCompletedTree2(){
        if(root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()) return false;
            if(node.leftNode != null){
                queue.offer(node.leftNode);
            }else if(node.rightNode != null){
                return false;
            }
            if(node.rightNode != null){
                queue.offer(node.rightNode);
            }else{
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    public void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.leftNode);
        preorderTraversal(node.rightNode);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal(){
        inorderTraversal(root);
    }
    public void inorderTraversal(Node<E> node){
        if(node == null) return;
        inorderTraversal(node.leftNode);
        System.out.println(node.element);
        inorderTraversal(node.rightNode);
    }

    /**
     * 后序遍历
     * @param visitor
     */
    public void postOrderTraversal(MyVisitor<E> visitor){
        if(visitor == null) return;
        postOrderTraversal(root,visitor);
    }
    public void postOrderTraversal(Node<E> node, MyVisitor<E> visitor){
        if(node == null || visitor.stop) return;
        postOrderTraversal(node.leftNode, visitor);
        postOrderTraversal(node.rightNode, visitor);
        if(visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    public void kk(){
        if(root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if(node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if(node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }
    /**
     * 层序遍历
     */
    public void levelOrderTraversal(){
        if(root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if(node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if(node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }
    public void levelOrder(MyVisitor<E> visitor){
        if(root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.visit(node.element)) return;
            if(node.leftNode != null){
                queue.offer(node.leftNode);
            }
            if(node.rightNode != null){
                queue.offer(node.rightNode);
            }
        }
    }

    /**
     * 比较元素大小，0: 相等， 1：e1比e2大，-1：e1比e2小
     * @param e1
     * @param e2
     * @return
     */
    private int compare(E e1, E e2) {
        if(comparator == null){
            return ((Comparable<E>)e1).compareTo(e2);
        }else{
            return comparator.compare(e1,e2);
        }
    }

    public E remove(E element){
        return element;
    }
    public boolean contain(E element){
        return true;
    }

    private void elementNotNullCheck(E element){
        if(element == null){
            throw new IllegalArgumentException("element must not null");
        }
    }

    @Override
    public Object root() {
        return this.root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).rightNode;
    }

    @Override
    public Object string(Object node) {
       return ((Node<E>)node).element;
    }

    public static class Node<E>{
        E element;
        Node<E> parentNode;
        Node<E> leftNode;
        Node<E> rightNode;
        public Node(E element, Node<E> parentNode){
            this.element = element;
            this.parentNode = parentNode;
        }
        public boolean isLeaf(){
            return this.leftNode == null && this.rightNode == null;
        }
    }

    public abstract static class MyVisitor<E>{
        boolean stop;
        abstract boolean visit(E element);
    }
}
