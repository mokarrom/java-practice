/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 * Find Second largest element in an array
 * 
 * https://www.geeksforgeeks.org/find-second-largest-element-array/
 * 
 * @author Mokarrom
 */
public class SecondLargest {
    /**
     * Time complexity: O(n)
     * Auxiliary Space : O(1)
     * 
     * @param items array of item
     * @return 
     */
    static int secondLargerst(int[] items) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        
        if (items == null || items.length < 2) {
            return -1;
        }
        
        for (int item : items) {
            if (item > max1) {
                max2 = max1;
                max1 = item;
            }
            else if (item > max2 && item != max1) {
                max2 = item;
            }
        }
        
        return max2;
    }
    
    public static void main(String[] args) {
        int arr1[] = {12, 35, 1, 10, 34, 1}; 
        System.out.println(secondLargerst(arr1));
        
        int arr2[] = {50, 50, 40, 30, 20, 10}; 
        System.out.println(secondLargerst(arr2));
        
        int arr3[] = {4, 1, 2, 5, 9, 6, 3, 2, 8}; 
        System.out.println(secondLargerst(arr3));
        
        int arr4[] = {44, 66, 99, 77, 33, 22, 55}; 
        System.out.println(secondLargerst(arr4));
    }
}
