/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 * Fibonacci Sequence: 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ……..
 *
 * @author Mokarrom
 */
public class FibonacciNumbers {
    
    static int fibRecursive(int n) {
        if (n <= 1) {
            return 1;
        }
        
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
    
    static int fibIterative(int n) {
        int fib[] = new int[n];
        
        fib[0] = fib[1] = 1;
        
        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        
        return fib[n - 1];
    }
    
    public static void main(String[] args) {
        System.out.println(fibRecursive(0));
        System.out.println(fibRecursive(1));
        System.out.println(fibIterative(5));
        System.out.println(fibIterative(8));
    }
}
