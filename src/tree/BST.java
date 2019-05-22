/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author mokarromh
 */
public class BST {
    /**
     * Inner class for holding BST node - value of current node, left and right child of current node.
     */
    class BstNode {
        int data;
        BstNode left;
        BstNode right;
        
        public BstNode(int data) {
            this.data = data;
            left = null;
            right = null;
        } 
    }
    
    public enum Order {
        IN_ORDER, // Depth-first traversal: Left -> Root -> Right   sorted order
        PRE_ORDER, // Depth-first traversal: Root -> Lef -> Right
        POST_ORDER, // Depth-first traversal: Left -> Right -> Root
        LVL_ORDER // Breadth-first traversal: level-order traversal
    };
    
    private BstNode root;

    public BST() {
        root = null;
    }
    
    public void insert(int value) {
        if (root == null) {
            this.root = new BstNode(value);
        }
        else {
            insert(this.root, value);
        }
    }
    
    private void insert(BstNode curNode, int value) {
        if (value < curNode.data) {
            if (curNode.left == null) {
                curNode.left = new BstNode(value);
            }
            else {
                insert(curNode.left, value);;
            }
        }
        else {
            if (curNode.right == null) {
                curNode.right = new BstNode(value);
            }
            else {
                insert(curNode.right, value);
            }
        }
    }
    
    public void delete(int value) {
        findAndDelete(root, value);
    }
    
    private BstNode findAndDelete(BstNode curNode, int value) {
        if (curNode == null) {
            return null;
        }
        
        if (value < curNode.data) {
            curNode.left = findAndDelete(curNode.left, value);
        }
        else if (value > curNode.data) {
            curNode.right = findAndDelete(curNode.right, value);
        }
        else {
            curNode = deleteNode(curNode);
        }
        
        return curNode;
    }
    
    /**
     * This function actually deletes the provided node.
     * Case 1 & 2 can be merged together.
     * 
     * @param delNode
     * @return 
     */
    private BstNode deleteNode(BstNode delNode) {
        // Case-1: Node to be removed has no children.
        if (delNode.left == null && delNode.right == null) {
            return null;
        }
        // Case-2: Node to be removed has exactly one child
        else if (delNode.left == null) {
            return delNode.right;
        }
        else if (delNode.right == null) {
            return delNode.left;
        }
        // Case-3: Node to be removed has both left and right child
        else {
            int minValRightSubTree = findMinValue(delNode.right);
            delNode.data = minValRightSubTree;
            delNode.right = findAndDelete(delNode.right, minValRightSubTree);
        }
        
        return delNode;
    }
    
    public boolean contains(int value) {
        return contains(this.root, value);
    }
    
    /**
     * Check whether the tree contains a specific node or not.
     * 
     * @param curNode
     * @param value
     * @return 
     */
    private boolean contains(BstNode curNode, int value) {
        if (curNode == null) {
            return false;
        }
        if (value == curNode.data) {
            return true;
        }
        
        return value < curNode.data
                ? contains(curNode.left, value)
                : contains(curNode.right, value);
    }
    
    public int findMinValue() {
        if (root != null) {
            return findMinValue(root);
        }
        else {
            return -1;
        }
    }
    
    private int findMinValue(BstNode curNode) {
        if (curNode.left == null) {
            return curNode.data;
        }
        else {
            return findMinValue(curNode.left);
        }
    }
    
    /*
    * Find out what's wrong in this method
    * 
    private int findMinValue(BstNode curNode) {
        if (curNode.left != null) {
            findMinValue(curNode.left);
        }
        
        return curNode.data;
    } */
    
    /**
     * Find the maximum value (node key) among all the nodes.
     * 
     * @return 
     */
    public int findMaxValue() {
        if (root == null) {
            return -1;
        }
        
        BstNode curNode = root;
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        
        return curNode.data;
    }
    
    public int findHeight() {
        return findHeight(root);
    }
    
    /**
     * Returns the height of the tree. Height is the number of edges between tree's root and it's furthest leaf.
     * 
     * @param curNode
     * @return 
     */
    private int findHeight(BstNode curNode) {
        if (curNode == null) {
            return -1;  // we have already counted one null child. therefore deduct 1;
        }
        
        return Math.max(findHeight(curNode.left), findHeight(curNode.right)) + 1;
    }
    
    public boolean isValidBst() {
        return isValidBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //return isValidBst(root, null);
    }
    
    /**
     * Recursively check whether a binary tree is binary search tree.
     * 
     * @param curNode
     * @param minVal
     * @param maxVal
     * @return 
     */
    private boolean isValidBst(BstNode curNode, int minVal, int maxVal) {
        if (curNode == null) {
            return true; // an empty tree is BST
        }
        
        if (curNode.data < minVal || curNode.data > maxVal) {
            return false; // current node violates the min/max constraints
        }
        
        return isValidBst(curNode.left, minVal, curNode.data)
                && isValidBst(curNode.right, curNode.data, maxVal);
    }
    
    /**
     * Check whether a binary tree is binary search tree or not. Traverse the tree in-order fashion 
     * and check every node is greater than or equal to the previous node.
     * 
     * @param currNode
     * @param prevNode
     * @return 
     */
    private boolean isValidBst(BstNode currNode, BstNode prevNode) {
        if (currNode == null) {
            return true;
        }
        
        if (!isValidBst(currNode.left, prevNode)) {
            return false;
        }
        
        if (prevNode != null && currNode.data <= prevNode.data) {
            return false;
        }
        
        prevNode = currNode;
        
        return isValidBst(currNode.right, prevNode);
    }
    
    public boolean isCompleteTree() {
        return isCompleteTree(root);
    }
    
    /**
     * Check whether the binary tree is complete or not. A binary tree is complete if its every node has zero or exactly two children.
     * 
     * @param curNode
     * @return 
     */
    private boolean isCompleteTree(BstNode curNode) {
        if (curNode == null) {
            return true;    // empty tree
        }
        
        if (curNode.left == null && curNode.right == null) {
            return true;    // leaf node
        }
        
        if (curNode.left != null && curNode.right != null) {
            return isCompleteTree(curNode.left) && isCompleteTree(curNode.right);
        }
        
        return false;
    }
    
    public void printNodes(Order tvslType) {
        switch (tvslType) {
            case PRE_ORDER:
                System.out.print("Pre-Order:  ");
                printPreOrder(root);
                System.out.println();
                break;
                
            case IN_ORDER:
                System.out.print("In-Order:   ");
                printInOrder(root);
                System.out.println();
                break;
                
            case POST_ORDER:
                System.out.print("Post-Order: ");
                printPostOrder(root);
                System.out.println();
                break;
                
            case LVL_ORDER:
                System.out.print("Level-Order: ");
                printLevelOrder(root);
                System.out.println();
                break;
                
            default:
                    throw new IllegalArgumentException("Invalid Argument!");
        }
    }
    
    private void printInOrder(BstNode curNode) {
        if (curNode != null) {
            printInOrder(curNode.left); 
            System.out.print(curNode.data + " ");
            printInOrder(curNode.right);
        }      
    }
    
    private void printPreOrder(BstNode rootNode) {
        if (rootNode != null) {
            System.out.print(rootNode.data + " ");
            printPreOrder(rootNode.left); 
            printPreOrder(rootNode.right);
        }      
    }
    
    private void printPostOrder(BstNode rootNode) {
        if (rootNode != null) {
            printPostOrder(rootNode.left); 
            printPostOrder(rootNode.right);
            System.out.print(rootNode.data + " ");
        }      
    }
    
    private void printLevelOrder(BstNode rootNode) {
        if (rootNode == null) {
            return;
        }
        
        Queue<BstNode> queue = new LinkedList<>();
        queue.add(rootNode);
        
        while (queue.size() > 0) {
            BstNode node = queue.remove();
            
            if (node.left != null) {
                queue.add(node.left);
            }
           
            if (node.right != null) {
                queue.add(node.right);
            }
            
            System.out.print(node.data + " ");
        }            
    }
    
    public static void main(String[] args) {
        bstTestCase1();
        bstTestCase2();
        bstTestCase3();
    }
    
    static void bstTestCase1() {
        System.out.println("\nTest Case 1: ");
        System.out.println("----------------------");
        /* Test Case-1: Let us create following BST 
                            50 
                          /    \ 
                        30      70 
                       /  \    /  \ 
                     20   40  60   80 
        */
        int[] nodes = {50, 30, 20, 40, 70, 60, 80};
        
        BST bst = new BST();
        
        for (int n : nodes) 
            bst.insert(n);
        
        System.out.println("Contains ? " + bst.contains(60));
        System.out.println("Contains ? " + bst.contains(100));
        
        System.out.println("Is valid BST ? " + bst.isValidBst());
        System.out.println("Is Complete Binary Tree ? " + bst.isCompleteTree());
        System.out.println("Height = " + bst.findHeight());
        
        bst.printNodes(Order.LVL_ORDER);
        
        System.out.println("Min value: " + bst.findMinValue());
        System.out.println("Min value: " + bst.findMaxValue());
        
        System.out.println("\nDelete Test");
        bst.delete(50);
        bst.printNodes(Order.IN_ORDER);
    }
    
    static void bstTestCase2() {
        System.out.println("\nTest Case 2: ");
        System.out.println("----------------------");
        /* Test Case-2: Consider the following BST
                                8
                              /   \
                            /       \
                           3         10
                         /   \         \
                        1     6        14
                            /   \      /
                           4     7   13          
        Ref. http://www.java2novice.com/java-interview-programs/delete-node-binary-search-tree-bst/
        */
        int[] nodes = {8, 10, 14, 3, 6, 7, 1, 4, 13};
        
        BST bst = new BST();
        
        for (int n : nodes) 
            bst.insert(n);

        System.out.println("Is valid BST ? " + bst.isValidBst());
        System.out.println("Is Complete Binary Tree ? " + bst.isCompleteTree());
        
        bst.printNodes(Order.IN_ORDER);
        
        bst.delete(3);
        bst.printNodes(Order.IN_ORDER);
        
        bst.delete(8);
        bst.printNodes(Order.IN_ORDER);
    }
    
    static void bstTestCase3() {
        System.out.println("\nTest Case 3: ");
        System.out.println("----------------------");
        /* Consider the following BST
                            10
                           /  \
                         /      \
                        5        20
                      /   \     /  \
                     3     8  18    25
                          /        /  \
                         7        23   30
                                /  \
                               21   24
        Ref. http://javabypatel.blogspot.com/2015/08/delete-a-node-from-binary-search-tree-in-java.html
        */
        int[] nodes = {8, 10, 14, 3, 6, 7, 1, 4, 13};
        
        BST bst = new BST();
        
        for (int n : nodes) 
            bst.insert(n);

        System.out.println("Is valid BST ? " + bst.isValidBst());
        System.out.println("Is Complete Binary Tree ? " + bst.isCompleteTree());
        bst.printNodes(Order.IN_ORDER);
        
        bst.delete(3);
        bst.printNodes(Order.IN_ORDER);
        
        bst.delete(8);
        bst.printNodes(Order.IN_ORDER);
    }
}
