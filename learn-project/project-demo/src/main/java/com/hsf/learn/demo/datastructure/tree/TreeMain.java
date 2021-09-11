package com.hsf.learn.demo.datastructure.tree;


import com.hsf.learn.demo.datastructure.tree.printer.BinaryTrees;

import java.util.Comparator;

public class TreeMain {
    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(Comparator.comparingInt(o -> ((Person) o).getAge()));

        BinarySearchTree<Person> bst3 = new BinarySearchTree<>((o1, o2) ->
                ((Person) o2).getAge() - ((Person) o1).getAge());
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i], data[i]));
            bst3.add(new Person(data[i], data[i]));
        }
        BinaryTrees.println(bst);
//        bst.preorderTraversal();
//        System.out.println("=========");
//        bst.inorderTraversal();
//        System.out.println("=========");
//        bst.postOrderTraversal(new BinarySearchTree.MyVisitor<Integer>() {
//            @Override
//            boolean visit(Integer element) {
//                System.out.println("元素=="+element);
//                return element == 11;
//            }
//        });
        System.out.println("高度=="+bst.heightRoot());
        System.out.println("是否是完全二叉树=="+bst.isCompletedTree());
//        System.out.println("=========");
//        bst.levelOrderTraversal();
//        System.out.println("=========");
//        bst.levelOrder(new BinarySearchTree.MyVisitor<Integer>() {
//            @Override
//            boolean visit(Integer element) {
//                System.out.println("元素=="+element);
//                return element == 11;
//            }
//        });

    }

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {

        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }
}
