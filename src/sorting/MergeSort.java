/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Arrays;

/**
 *
 * @author Mokarrom
 */
public final class MergeSort {
    
    public static void sort(int[] elements) {
        if (elements == null || elements.length < 2) {
            return;
        }
//        insertionsSort(elements, 0, elements.length - 1);
//        mergeSort(elements, 0, elements.length - 1);
        bubbleSort(elements, 0, elements.length - 1);
    }
    
    public static void mergeSort(int[] elements, int begIdx, int endIdx) {
        if (begIdx < endIdx) {
            // divide the current array in two parts
            int midIdx = (begIdx + endIdx) / 2;
            // sort the left half
            mergeSort(elements, begIdx, midIdx);
            // sort the right half
            mergeSort(elements, midIdx + 1, endIdx);
            //merge the two sorted array
            merge(elements, begIdx, midIdx, endIdx);
        }
    }
    
    public static void merge(int[] elements, int begIdx, int midIdx, int endIdx) {
        // create a temp array - for efficiency, create a global array with same size as original array and use it repeatedly.
        int[] temp = new int[endIdx - begIdx + 1];
        
        // crawlers for both intervals and for temp
        int i = begIdx;
        int j = midIdx + 1;
        int k = 0;
        
        // traverse both arrays and in each iteration add smaller of both elements in temp 
        while (i <= midIdx && j <= endIdx) {
            if (elements[i] < elements[j]) {
                temp[k++] = elements[i++];
            }
            else {
                temp[k++] = elements[j++];
            }          
        }
        
        // add elements left in the first interval 
        while (i <= midIdx) {
            temp[k++] = elements[i++];
        }
          
        // add elements left in the second interval 
        while (j <= endIdx) {
            temp[k++] = elements[j++];
        }
        
        // copy temp array to the original array
        for (i = begIdx, k = 0; i <= endIdx; i++, k++) {
            elements[i] = temp[k];
        }
    }
    
    public static void insertionsSort(int[] items, int begIdx, int endIdx) {
        for (int i = begIdx + 1; i <= endIdx; i++) {
            int j = i - 1;
            int key = items[i];
            
            // Move elements from 0 to i-1, that are greater than key, to one position ahead of their current position
            while (j >= begIdx && items[j] > key) {
                items[j + 1] = items[j];
                j--;
            }
            
            items[j + 1] = key;
        }
    }
    
    public static void bubbleSort(int[] items, int begIdx, int endIdx) {
        for (int i = begIdx; i < endIdx; i++) {
            for (int j = begIdx; j < endIdx - i; j++) {
                if (items[j] > items[j + 1]) {
                    swap(items, j, j + 1);
                }
            }
        }
    }
    
    private static void swap(int[] items, int idx1, int idx2) {
         int tmp = items[idx1];
         items[idx1] = items[idx2];
         items[idx2] = tmp;
     }
    
    public static void main(String[] args) {
        int arr[] = {3, 6, 0, 1, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        
        int[] arr1={22,21,19,18,15,14,9,7,5};
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
        
        int[] arr2={1,2,3,4,5,6,7,8,9,10};
        sort(arr2);
        System.out.println(Arrays.toString(arr2));
        
        int[] arr3={19, 9, 3, 7, 9, 0 , 4, 33, 2, 5, 1};
        sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
