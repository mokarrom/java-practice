/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 * Largest Sum Contiguous Subarray
 * Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
 * 
 * @author mokarromh
 */
public class MaxSubArrSum {
    public static enum AlgoType {BRUTE_FORCE, DIVIDE_CONQUER, KADANES_ALGO};
    
    static int maxSubArraySum(int[] elements, AlgoType algo) {
        if (elements == null || elements.length < 1) {
            throw new IllegalArgumentException("Aarray cannot be empty!");
        }
        
        if (algo == AlgoType.BRUTE_FORCE) {
            return maxSubArrSumBF(elements);
        }
        else if (algo == AlgoType.DIVIDE_CONQUER) {
            return maxSubArrSumDnC(elements, 0, elements.length - 1);
        }
        else if (algo == AlgoType.KADANES_ALGO) {
            return maxSubArrSumKad(elements, elements.length);
        }
        else {
            throw new IllegalArgumentException("Invalid method name.");
        }
    }
    
    static int maxSubArrSumKad(int[] elements, int size) {
        int curMax = elements[0];
        int maxSum = elements[0];
        
        for (int i = 1; i < size; i++) {
            curMax = Math.max(elements[i], curMax + elements[i]);
            
            if (curMax > maxSum) {
                maxSum = curMax;
            }
        }
        
        return maxSum;
    } 
    
    static int maxSubArrSumDnC(int[] elements, int low, int high) {
        if (low == high) {
            return elements[low];
        }
        
        int mid = (low + high) / 2;
        return max(maxSubArrSumDnC(elements, low, mid),
                    maxSubArrSumDnC(elements, mid + 1, high),
                    maxSubArrCrsngSum(elements, low, mid, high));
    }
    
    static int maxSubArrCrsngSum(int[] elements, int low, int mid, int high) {
        
        int sum = 0;
        int maxLeftSum = Integer.MIN_VALUE;
        
        for (int i = mid; i >= low; i--) {
            sum += elements[i];
            
            if (sum > maxLeftSum) {
                maxLeftSum = sum;
            }
        }
        
        sum = 0;
        int maxRightSum = Integer.MIN_VALUE;
        
        for (int i = mid + 1; i <= high; i++) {
            sum += elements[i];
            
            if (sum > maxRightSum) {
                maxRightSum = sum;
            }
        }
        
        return maxLeftSum + maxRightSum;
    }
    
    static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    
    static int maxSubArrSumBF(int elements[]) {
        int numOfElms = elements.length;
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < numOfElms; i++) {
            int sum = 0;
            for (int j = i; j < numOfElms; j++) {
                sum += elements[j];
                
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        AlgoType Algorithm = AlgoType.KADANES_ALGO;
        
        int arr1[] = {2, 3, 4, 5, 7}; 
        int maxSum = maxSubArraySum(arr1, Algorithm); 
        System.out.println("Maximum contiguous sum is "+ maxSum);
        
        int arr2[] = {-2, -5, 6, -2, -3, 1, 5, -6}; 
        maxSum = maxSubArraySum(arr2, Algorithm); 
        System.out.println("Maximum contiguous sum is "+ maxSum);
        
        int arr3[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; 
        maxSum = maxSubArraySum(arr3, Algorithm); 
        System.out.println("Maximum contiguous sum is "+ maxSum);
        
        int arr4[] = {-2, -3, 4, -1, -2, 1, 5, -3}; 
        maxSum = maxSubArraySum(arr4, Algorithm); 
        System.out.println("Maximum contiguous sum is "+ maxSum);
        
        int arr5[] = {-2, -3, -1, -2, -5, -3}; 
        maxSum = maxSubArraySum(arr5, Algorithm); 
        System.out.println("Maximum contiguous sum is "+ maxSum);
    }
}
