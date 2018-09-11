package com;

public class Main {
    public static void main(String[] args){
        RBTree<String,String> rbTree = new RBTree<>();
        rbTree.put("0707", "FrancoChen");
        rbTree.put("0010", "Automan");
        rbTree.inTraversal();
        System.out.println("Get:" + rbTree.get("0010"));
        System.out.println("Get:" + rbTree.get("0001"));
        System.out.println("Size:" + rbTree.getSize());
        System.out.print("Min:"+ rbTree.min());
    }
}
