package com;

public class RBTree<Key extends Comparable<Key>,Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, Node left, Node right, boolean color){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    private final boolean RED = true;
    private final boolean BLACK = false;
    private int size;
    private Node root;

    private boolean isRed(Node node){
        return node !=null && node.color;
    }

    public boolean isEmpty() {
         if(root == null) return true;
         else return false;
    }

    /**
     * flip parent, left, right color
     * @param node
     */
    private void flipColors(Node node){
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node leftRotation(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node rightRotation(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private Node balance(Node node){
        if(isRed(node.left) && isRed(node.right) && !isRed(node)){
            if(isRed(node.left.left) || isRed(node.left.right) || isRed(node.right.left) || isRed(node.right.right)){
                flipColors(node);
            }
        }
        else{

            if(isRed(node.left) && isRed(node.right) && !isRed(node)){
                if(isRed(node.left.left) || isRed(node.left.right) || isRed(node.right.left) || isRed(node.right.right)){
                    flipColors(node);
                }
            }
        }
        return node;
    }

    public void put(Key key,Value value){
        root = put(key,value,root);
        root.color = BLACK;
    }

    private Node put(Key key,Value value,Node node){
        if(node == null){
            size++;
            return new Node(key,value,null,null,RED);
        }
        else{
            int cmp = key.compareTo(node.key);
            if(cmp > 0){
                node.right = put(key,value,node.right);
            }
            else if(cmp < 0){
                node.left = put(key,value,node.left);
            }
            else {
                node.value = value;
            }
            return node;
        }
    }

    private Node max(Node node){
        if(node == null){
            return null;
        }else{
            while(node.right != null){
                node = node.right;
            }
            return node;
        }
    }

    public Node max(){
        return root == null ? null : max(root);
    }

    private Node min(Node node){
        if(node == null){
            return null;
        }else{
            while(node.left != null){
                node = node.left;
            }
            return node;
        }
    }

    public Node min(){
        return root == null ? null : min(root);
    }
}
