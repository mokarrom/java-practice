/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mokarrom
 */
public class MinHeap {
    private int size;
    private int capacity;
    
    private int[] items;
    
    public MinHeap(int capacity) {
        this.capacity = capacity;
        items = new int[capacity];
    }
    
    private void ensureCapacity() {
        if (size == capacity) {
            capacity *= 2;
            items = Arrays.copyOf(items, capacity);
        }
    }
    
    private void swap(int idx1, int idx2) {
        int temp = items[idx1];
        items[idx1] = items[idx2];
        items[idx2] = temp;
    }
    
    private int getLeftChildIdx(int parentIdx) {
        return 2 * parentIdx + 1;
    }
    
    private int getRightChildIdx(int parentIdx) {
        return 2 * parentIdx + 2;
    }
    
    private int getParentIdx(int childIdx) {
        return (childIdx - 1) / 2;
    }
    
    private boolean hasLeftChild(int parentIdx) {
        return getLeftChildIdx(parentIdx) < size;
    }
    
    private boolean hasRightChild(int parentIdx) {
        return getRightChildIdx(parentIdx) < size;
    }
    
    private boolean hasParent(int childIdx) {
        return getParentIdx(childIdx) >= 0;
    }
    
    private int leftChild(int parentIdx) {
        return items[getLeftChildIdx(parentIdx)];
    }
    
    private int rightChild(int parentIdx) {
        return items[getRightChildIdx(parentIdx)];
    }
    
    private int parent(int childIdx) {
        return items[getParentIdx(childIdx)];
    }
    
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        
        return items[0];
    }
    
    public int pool() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        
        
        int root = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        
        return root;
    }
    
    public void add(int item) {
        ensureCapacity();
        
        items[size] = item;
        size++;
        
        heapifyUp();
    }
    
    private void heapifyUp() {
        int idx = size - 1;
        
        while (hasParent(idx) && parent(idx) > items[idx]) {
            swap(getParentIdx(idx), idx);
            idx = getParentIdx(idx);
        }
    }
    
    private void heapifyDown() {
        int idx = 0;
        
        while (hasLeftChild(idx)) {
            int minChildIdx = getLeftChildIdx(idx);
            if (hasRightChild(idx) && rightChild(idx) < leftChild(idx)) {
                minChildIdx = getRightChildIdx(idx);
            }
            
            if (items[idx] < items[minChildIdx]) {
                break;
            }
            
            swap(idx, minChildIdx);
            idx = minChildIdx;
        }
    }
}
