/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Scanner;

/**
 *
 * @author mokarromh
 */
public class PalindromeChecker {
    
    static boolean isPalindrome(char[] word) {
        int begIdx = 0;
        int endIdx = word.length - 1;
        
        while (begIdx < endIdx) {
            if (word[begIdx] != word[endIdx]) {
                return false;
            }
            
            begIdx++;
            endIdx--;
        }
                
        return true;
    }
    
    static boolean isPalindrome(int n) {
        return n == reverse(n);
    }
    
    static int reverse(int num) {
        int reminder;
        int revNum = 0;
        
        do {
            reminder = num % 10;
            
            revNum = 10 * revNum + reminder;
            
            num /= 10;
        } while (num != 0);
        
        return revNum;
    }
    
    
    public static void main(String[] args) {
        
        int inputNum = 0;
        String inputStr;
        boolean isNumber;
        
        Scanner input = new Scanner(System.in);
        
        do {           
            System.out.println("\nPlease enter a number or a word");
            inputStr = input.next().trim();
            
            if (inputStr.equalsIgnoreCase("exit")) {
                break;
            }
            
            try {
                inputNum = Integer.parseInt(inputStr);
                isNumber = true;
            } catch (Exception e) {
                isNumber = false;
            }
            
            if (isNumber && isPalindrome(inputNum) || PalindromeChecker.isPalindrome(inputStr.toCharArray())) {
                System.out.println(inputStr + " is a Palindrome!");
            }
            else {
                System.out.println(inputStr + " is NOT a Palindrome!");
            }
        } while (true);

        input.close();
    }
    
}
