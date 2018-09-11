package com;

public class Node {
    private int key;
    private int value;
    private Node left;
    private Node right;
    private boolean color;

    Node(int key,int value,Node left,Node right,boolean color){
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = color;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
