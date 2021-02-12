package com.hsf.learn.demo.datastructure;

public class LinkedList<E> {

    private int size;
    private Node<E> firstNode;
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
