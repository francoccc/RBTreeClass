package com;

public class Main {
    public static void main(String[] args){
        RBTree<String,String> rbTree = new RBTree<>();
        rbTree.put("0707", "FrancoChen");
        rbTree.put("0010", "AutoMan");
        rbTree.put("0001", "StayReal");
        rbTree.put("0300", "TestIn");
        rbTree.put("0002", "Compute");
        rbTree.put("0200", "TestInLeft");
        rbTree.inTraversal();
        System.out.println("Get root:" + rbTree.getRoot());
        System.out.println("Get:" + rbTree.get("0010"));
        System.out.println("Get:" + rbTree.get("0001"));
        System.out.println("Size:" + rbTree.getSize());
        System.out.print("Min:"+ rbTree.min());
    }
}
