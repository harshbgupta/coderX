package com.kritsn.temp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Scanner;

class Revisionjava {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, IOException {
//        Company company = new Company();
//        company.setName("Kritsn");
//        String  name = (String) company.getName();
//        System.out.println(name);
//        company.setName(1);
//        int  nameInt = (int) company.getName();
//        System.out.println(nameInt);


        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
//            String name = sc.nextLine();
//            System.out.println("Hello, " + name + "!");
        } finally {
            System.out.println("finally");
        }

    }

    private static void bfs(Node root) {
        if (root == null) return;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                if (queue.isEmpty()) break;
                System.out.println();
                queue.add(null);
            } else {
                System.out.println(node.getData());
                if (node.getLeft() != null) queue.add(node.getLeft());
                if (node.getRight() != null) queue.add(node.getRight());
            }
        }

    }

}

class Company {
    private Object name;

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }
}

class Node {
    private int data;
    private Node Left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node left) {
        Left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}