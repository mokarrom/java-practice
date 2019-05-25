/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.HashMap;

/**
 * Node class
 * 
 * @author mokarromh
 */
class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }         
}

public class LRUCache {
    private final HashMap<Integer, Node> cache;
    
    private final int capacity;
    private Node head;  // most recently accessed node
    private Node tail;  // least recently accessed node
    
    public LRUCache() {       
        this(3);    // default cache size
    }
    
    public LRUCache (int capacity) {
        cache = new HashMap<>(capacity);
        this.capacity = capacity;
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        System.out.println("Accessed Key: " + key);
        int result = -1;
        
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            result = node.value;
            
            // move to the front
            deleteNode(node);
            addToFront(node);
        }
        
        return result;
    }
    
    public void put(int key, int value) {
        System.out.println("Adding Entry: " + key + ", " + value);
        
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            
            // move to the front
            deleteNode(node);
            addToFront(node);
        }
        else {
            if (cache.size() >= capacity) { 
                // delete tail node
                cache.remove(tail.key);
                deleteNode(tail);
            }
            
            // Add to the front
            Node node = new Node(key, value);
            addToFront(node);
            cache.put(key, node);
        }
    }
    
    public void printValues() {
        if (head == null) {
            return; // cache is empty
        }
        
        Node currNode = head;
        
        System.out.print("[");
        while (currNode != null) {
            System.out.print(currNode.value + ", ");
            currNode = currNode.next;
        }
        System.out.println("]");
    }
    
    private void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        
        if (prevNode == null && nextNode == null) { // deleting the only element.
            head = tail = null;
        }
        else if (prevNode == null) { // deleting the first node
            head = node.next;
            head.prev = null;
        }
        else if (nextNode == null) {    // deleting the last node
            tail = node.prev;
            tail.next = null;
        }
        else { // deleting the middle node
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }
    
    private void addToFront(Node node) {
        if (head == null) {
            head = tail = node;
        }
        else {
            node.next = head;
            head.prev = node;
            head = node;
        }  
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        
        cache.put(1, 101);
        cache.printValues();
        cache.put(2, 201);
        cache.printValues();
        cache.put(3, 301);
        cache.printValues();
        cache.put(4, 401);
        cache.printValues();
        cache.put(5, 501);
        cache.printValues();
        
        cache.get(3);
        cache.put(6, 601);
        cache.printValues();
        
        cache.get(15);
        cache.printValues();
    }
}
