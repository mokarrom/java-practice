/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorting;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=SLauY6PpjW4
 * 
 * @author Mokarrom
 */
public class QuickSort {
    
     public static void sort(int[] items) {
         if (items == null || items.length < 2) {
             return;
         }
         
         quickSort(items, 0, items.length - 1);
     }
     
     public static void quickSort(int[] items, int low, int high) {
        if (low < high) {
            int pivotIdx = partition(items, low, high);
            quickSort(items, low, pivotIdx - 1);
            quickSort(items, pivotIdx, high);
        }
     } 
     
     private static int partition(int[] items, int low, int high) {
        int pivItem = items[(low + high) / 2]; //pick first element as pivot
        
        while (low <= high) {
            while (items[low] < pivItem)
                low++;
		
            while (items[high] > pivItem)
                high--;
            
            if (low <= high) {
                    swap(items, low, high);
                    low++;
                    high--;
            }
        }
                    
        return low;
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
        
        int[] arr3={19, 9, 3, 7, 9, 0 , 4, 3, 33, 2, 5, 5, 1, 19};
        sort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
