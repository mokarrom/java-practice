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
public class StringReversal {
    
    static String reverseByBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    static String reverse(String str) {
        char[] strArr = str.toCharArray();
        
        for (int i = 0, j = strArr.length - 1; i < strArr.length / 2; i++, j--) {
            Util.swap(strArr, i, j);
        }
        
        return new String(strArr);
    }
    
    static String reverseByBytes(String str) {
        byte[] strBytes = str.getBytes();
        
        int begIdx = 0;
        int endIdx = strBytes.length - 1;
        
        while (begIdx < endIdx) {
            Util.swap(strBytes, begIdx, endIdx);
            begIdx++;
            endIdx--;
        }
        
        return new String(strBytes);
    }
    
    public static void main(String[] args) {
        Scanner intput  = new Scanner(System.in);
        String inputStr  = null;
        
        do {
            inputStr = intput.nextLine();
            
            if (inputStr.equalsIgnoreCase("exit")) {
                break;
            }
            
            System.out.println("Input: " + inputStr);
            System.out.println("Reverse: " + reverseByBytes(inputStr));     
            System.out.println("");
        } while (true);
    }
}
