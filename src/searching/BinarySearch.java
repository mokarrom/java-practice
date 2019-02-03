/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searching;

/**
 *
 * @author Mokarrom
 */
public class BinarySearch {
    
    public static int search(int[] items, int x) {
        if (items.length < 1) {
            return -1;
        }
        
        //return recurBinSearch(items, x, 0, items.length - 1);
        return iterativeBinSearch(items, x);
    }
    
    public static int recurBinSearch(int[] items, int x, int low, int high) {
        if (low > high) {
            return -1;
        }
        
        int mid = low + (high - low) / 2;
        
        if (x == items[mid]) {
            return mid;
        }
        else if (x < items[mid]) {
            return recurBinSearch(items, x, low, mid - 1);
        }
        else {
            return recurBinSearch(items, x, mid + 1, high);
        }
    }
    
    public static int iterativeBinSearch(int[] items, int x) {
        int low = 0;
        int high = items.length;
        
        while (low < high) {
            int mid = (low + high) / 2;     // low + (high - low) / 2;
            
            if (x < items[mid]) {
                high = mid - 1;
            }
            else if (x > items[mid]) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int a[]={-1, 5, 6, 18, 19, 25, 46, 78, 102, 114};        
        System.out.println("Item loc: " + search(a, 102));
    }
}
