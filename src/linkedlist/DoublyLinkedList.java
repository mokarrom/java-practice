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
public class DoublyLinkedList {
    
    class DNode {
        public int data;
        public DNode next;
        public DNode prev;
        
        public DNode(int data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }
    
    private DNode head;
    private DNode tail;
    private int size;
    
    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }
    
    public void insertFirst(int data) {
        DNode newNode = new DNode(data);
        
        if (size == 0) {
            head = tail = newNode;
        }
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        
        size++;
    }
    
    public void insertLast(int data) {
        DNode newNode = new DNode(data);
        
        if (size == 0) {
            head = tail = newNode;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
    }
    
    public int size() {
        return size;
    }
    
    public void delete(int value) {
        DNode currNode = head;
        
        while (currNode != null && currNode.data != value) {
            currNode = currNode.next;
        }
        
        if (currNode == null) { // list is empty or item not found
            System.out.println(value + " not found!");
            return;
        }
        
        DNode prevNode = currNode.prev;
        DNode nextNode = currNode.next;
        
        if (prevNode == null && nextNode == null) {
            head = tail = null;
        }
        else if (prevNode == null) { // deleting the first node
            head = nextNode;
            head.prev = null;
            currNode = null;
        }
        else if (nextNode == null) {    // deleting the last node
            tail = prevNode;
            tail.next = null;
            currNode = null;
        }
        else { // deleting the middle node
            prevNode.next = currNode.next;
            nextNode.prev = currNode.prev;
        }
        
        size--;
    }
    
    public void printNodes() {
        DNode currNode = head;
        
        while (currNode != null) {
            System.out.print(currNode.data + " --> ");
            currNode = currNode.next;
        }
        System.out.println();
    }
    
    public void printNodesReverse() {
        DNode currNode = tail;
        
        while (currNode != null) {
            System.out.print(currNode.data + " --> ");
            currNode = currNode.prev;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        
        dll.insertFirst(55);
        dll.insertFirst(66);
        dll.insertLast(33);
        
        dll.printNodes();
        
        dll.delete(66);
        
        dll.printNodes();
        
        dll.insertFirst(99);
        dll.insertFirst(88);
        
        dll.printNodesReverse();
        
        dll.delete(33);
        dll.delete(88);
        
        dll.printNodesReverse();
        
        dll.delete(55);
        dll.delete(99);
        dll.insertFirst(11);
        dll.printNodesReverse();
    }
}
