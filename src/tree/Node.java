/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

/**
 *
 * @author Mokarrom
 */
public class Node {
    Node left = null;
    Node right = null;
    int data;
    
    public Node(int data) {
        this.data = data;
    }
    
    public void insert(int value) {
        if (value <= data) {
            if (left == null) {
                left = new Node(value);
            }
            else {
                left.insert(value);
            }
        }
        else {
            if (right == null) {
                right = new Node(value);
            }
            else {
                right.insert(value);
            }
        }
    }
    
    public boolean contains(int value) {
        if (value == data) {
            return true;
        }
        else if (value < data) {
            if (left == null) {
                return false;
            }
            else {
                return left.contains(value);
            }          
        }
        else {
            if (right == null) {
                return false;
            }
            else {
                return right.contains(value);
            }
        }
    }
    
    public boolean contains(Node curNode, int value) {
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
    
    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        
        System.out.println(data);
        
        if (right != null) {
            right.printInOrder();
        }
    }
    
    public static void main(String[] args) {
        Node bst = new Node(8);
        bst.insert(3);
        bst.insert(10);
        bst.insert(1);
        bst.insert(6);
        bst.insert(14);
        bst.insert(4);
        bst.insert(7);
        bst.insert(13);
        
        bst.printInOrder();
    }
}
