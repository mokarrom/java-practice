/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.util.Stack;

/**
 *
 * @author Mokarrom
 */
public class BracketBalanced {
    
    /**
     * Check for balanced parentheses in an expression.
     * 
     * Time Complexity: O(n)
     * Auxiliary Space: O(n) for stack.
     * 
     * @param str
     * @return 
     */
    static boolean isBalanced(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        
        char ch[] = str.toCharArray();
        Stack<Character> stack = new Stack();
        
        for (int i = 0; i < ch.length; i++) {
            switch (ch[i]) {
                case '[':
                case '{':
                case '(': 
                    stack.push(ch[i]);
                    break;
                    
                case ']':
                    if (stack.empty() || stack.pop().compareTo('[') != 0) {
                        return false;
                    }
                    break;
                    
                case '}':
                    if (stack.empty() || stack.pop().compareTo('{') != 0) {
                        return false;
                    }
                    break;
                    
                case ')':
                    if (stack.empty() || stack.pop().compareTo('(') != 0) {
                        return false;
                    }
                    break;
                    
                default: 
                    break;
            }
        }
        
        return stack.empty();
    }
    
    public static void main(String[] args) {
        System.out.println("" + isBalanced(null));  // Yes
        System.out.println("" + isBalanced(""));    // Yes
        System.out.println("" + isBalanced("{[()]}"));  // Yes
        System.out.println("" + isBalanced("{[(])}"));  // No
        System.out.println("" + isBalanced("{{[[((@#$%&*gf))]]}}")); // Yes
        System.out.println("" + isBalanced("}][}}(}][))]"));    // No
        System.out.println("" + isBalanced("[](){()}"));    // Yes
        System.out.println("" + isBalanced("({}([][]))[]()")); // Yes
        System.out.println("" + isBalanced("{)[](}]}]}))}(())(")); // No
        System.out.println("" + isBalanced("([[)")); //No
    }
}
