/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author mokarromh
 */
public class Util {
    
    static void swap(char[] elements, int i, int j) {
        char temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
    
    static void swap(byte[] elements, int i, int j) {
        byte temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
    
    static void swap(int[] elements, int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
    
    /**
     * Swap by addition and subtraction operation
     * 
     * @param a
     * @param b 
     */
    static void swapAD(int a, int b) {
        System.out.println("Before: a="+a+" b="+b);
        
        a = a + b;
        b = a - b;
        a = a - b;
        
        System.out.println("Before: a="+a+" b="+b);
    }
    
    /**
     * Swap by XOR operation
     * 
     * @param a
     * @param b 
     */
    static void swapXOR(int a, int b) {
        System.out.println("Before: a="+a+" b="+b);
        
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        
        System.out.println("Before: a="+a+" b="+b);
    }
    
    /**
     * Swap by multiplication and division
     * 
     * @param a
     * @param b 
     */
    static void swapMD(int a, int b) {
        System.out.println("Before: a="+a+" b="+b);
        
        a = a * b;
        b = a / b;
        a = a / b;
        
        System.out.println("Before: a="+a+" b="+b);
    }
    
    public static void main(String[] args) {
        swapMD(3, 5);
    }
}
