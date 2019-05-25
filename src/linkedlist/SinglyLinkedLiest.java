/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

/**
 *
 * @author mokarromh
 */
public class SinglyLinkedLiest {
    
    class Node {
        public int data;
        public Node next;
        
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }
    
    
    private Node head;
    private int numOfNodes;
    
    public SinglyLinkedLiest() {
        head = null;
        numOfNodes = 0;
    }
    
    /**
     * Insert an item at the beginning of the list.
     * 
     * @param data 
     */
    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        
        numOfNodes++;
    }
    
    /**
     * Insert an item at the end of the list.
     * 
     * @param data 
     */
    public void insertLast(int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
        }
        else {
            Node currNode = head;

            while (currNode.next != null) {
                currNode = currNode.next;
            }

            currNode.next = newNode; 
        }
             
        numOfNodes++;
    }
    
    /**
     * Insert an item in a particular position of the list.
     * If index is greater than the size, then the item will added at the end of list.
     * 
     * @param index position of the new item.
     * @param data data for the new item
     */
    public void insertAtIndex(int index, int data) {
        if (index < 0) {
            throw new IllegalArgumentException("Index must be greater than 0");
        }
        else if (index == 0) {
            insertFirst(data);
            return;
        }
        
        Node newNode = new Node(data);
        
        int count = 0;
        Node currNode = head;

        while (currNode.next != null && count < index - 1) {
            currNode = currNode.next;
            count++;
        }
        
        if (currNode.next == null) {
            currNode.next = newNode; 
        }
        else {
            Node temp = currNode.next;
            currNode.next = newNode;
            newNode.next = temp;
        }
        
        numOfNodes++;
    }
    
    public void delete(int value) {
        Node currNode = head;
        Node prevNode = head;
        
        while (currNode != null && currNode.data != value) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        
        if (currNode == null) { // list is empty or item not found
            System.out.println(value + " not found!");
            return;
        }
        
        if (currNode == head) { // deleting the first item.
            head = currNode.next;
            currNode.next = null;
        }
        else {  // deleting the last or middle item
            prevNode.next = currNode.next;
            currNode.next = null;
        }
        
        numOfNodes--;
                
        System.out.println(value + " has been deleted.");
    }
    
    public boolean find(int value) {
        Node currNode = head;
        
        while (currNode != null && currNode.data != value) {
            currNode = currNode.next;
        }
        
        return currNode != null;
    }
    
    public int size() {
        return numOfNodes;
    }
    
    public void printNodes() {
        Node currNode = head;
        
        while (currNode != null) {
            System.out.print(currNode.data + " --> ");
            currNode = currNode.next;
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        SinglyLinkedLiest list = new SinglyLinkedLiest();
        
        list.insertFirst(11);
        list.insertFirst(7);
        list.insertFirst(9);
        list.insertFirst(21);
        
        list.printNodes();
        
        list.insertLast(17);
        
        list.printNodes();
        
        System.out.println(list.find(22));
        System.out.println(list.find(10));
        System.out.println(list.find(7));
        
        list.delete(21);
        list.printNodes();
        
        list.delete(7);
        list.printNodes();
        
        list.delete(17);
        list.printNodes();
        
        list.delete(11);
        list.printNodes();
        
        list.delete(9);
        list.printNodes();
        
        list.insertLast(101);
        list.insertLast(205);
        list.insertFirst(302);
        
        list.printNodes();
        
        list.insertAtIndex(1, 111);
        list.printNodes();
        list.insertAtIndex(3, 215);
        list.printNodes();
    }
}
