/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Arrays;

/**
 * Odd-Even Segregation: 
 * Move all even numbers on the first half and odd numbers to the second half in an integer array
 * 
 * @author Mokarrom
 */
public class OddEvenSegregation {
    
    public static void segregateOddEven(int[] items) {
        int left = 0;
        int right = items.length - 1;
        
        while (left < right) {
            while (items[left] % 2 == 0 && left < right) 
                left++;
            
            while (items[right] % 2 == 1 && left < right)
                right--;
            
            if (left < right) {
                swap(items, left, right);
                left++;
                right--;
            }
        }
    }
    
    private static void swap(int[] items, int idx1, int idx2) {
         int tmp = items[idx1];
         items[idx1] = items[idx2];
         items[idx2] = tmp;
     }
    
    public static void main(String[] args) {
        int arr[] = {12, 34, 45, 9, 8, 90, 3}; 
        segregateOddEven(arr);
        System.out.println(Arrays.toString(arr));
        
        int arr2[] = {1, 3, 5, 7, 9};       
        segregateOddEven(arr2);       
        System.out.println(Arrays.toString(arr2));
        
        int arr3[] = {2, 4, 6, 8};       
        segregateOddEven(arr3);       
        System.out.println(Arrays.toString(arr3));
        
        int arr4[] = {101, 102};       
        segregateOddEven(arr4);       
        System.out.println(Arrays.toString(arr4));
    }
}
