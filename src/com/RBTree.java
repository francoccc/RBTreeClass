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
            if(isRed(node.right)){
                if(isRed(node.right.left)){
                    node.right = rightRotation(node.right);
                }
                if(isRed(node.right.right)){
                    node = leftRotation(node);
                }
            }
            else if(isRed(node.left)){
                if(isRed(node.left.right)){
                    node = leftRotation(node.left);
                }
                if(isRed(node.left.right)){
                    node = rightRotation(node);
                }
            }
            if(isRed(node.left) && isRed(node.right) && !isRed(node)){
                if(isRed(node.left.left) || isRed(node.left.right) || isRed(node.right.left) || isRed(node.right.right)){
                    flipColors(node);
                }
            }
        }
        return node;
    }

    public String get(Key key){
        Node node = get(key, root);
        return node == null ? "Null" : node.key + ":" +node.value;
    }

    private Node get(Key key, Node node){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp == 0){
            return node;
        }
        else if(cmp > 0){
            return get(key, node.right);
        }
        else{
            return get(key, node.left);
        }
    }

    public void put(Key key, Value value){
        root = put(key,value,root);
        root.color = BLACK;
    }

    private Node put(Key key, Value value, Node node){
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
            return balance(node);
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

    public int getSize(){
        return size;
    }

    public boolean isRBTree() {
        return isRBTree(root);
    }

    public boolean isRBTree(Node node) {
        if(node == null){
            return true;
        }else if(node.color == RED){
            return false;
        }else{
            Node tnode;
            int count = 0;
            for(tnode = node;tnode != null;tnode = tnode.left){
                if(tnode.color == BLACK){
                    count++;
                }
            }
            return isRBTree(node, count, 0);
        }
    }

    private boolean isRBTree(Node node, int blacknum,int count){
        if(node == null){
            return blacknum == count ? true : false;
        }else{
            boolean flag = true;
            if(node.color == BLACK) count++;
            if(node.left != null)
                flag &= isRBTree(node.left, blacknum, count);
            if(node.right != null)
                flag &= isRBTree(node.right, blacknum, count);
            try {
                flag &= !(isRBTree(node.left) && isRBTree(node.left.left));
                flag &= !(isRBTree(node.left) && isRBTree(node.left.right));
                flag &= !(isRBTree(node.right) && isRBTree(node.right.left));
                flag &= !(isRBTree(node.right) && isRBTree(node.right.right));
            }catch(NullPointerException e){
                e.getStackTrace();
            }
            return flag;
        }
    }

    public void inTraversal(){
        inTraversal(root);
    }

    private void inTraversal(Node node){
        if(node.left != null) inTraversal(node.left);
        System.out.println(node.key + ":" + node.value);
        if(node.right != null) inTraversal(node.right);
    }
}
