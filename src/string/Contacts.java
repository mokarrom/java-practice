/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/**
 * https://www.hackerrank.com/challenges/contacts/problem
 * 
 * @author Mokarrom
 */
public class Contacts {
    
    static int[] contacts(String[][] queries) {
        int nInputs = queries.length;
        HashMap<String, Integer> contacts = new HashMap<>();
        ArrayList<Integer> countResults = new ArrayList<>(nInputs);       
        
        for (int i = 0; i < nInputs; i++) {
            if(queries[i][0].equals("add")) {
                String newContact = queries[i][1];
                
                for (int j = 1; j < newContact.length() + 1; j++) {
                    
                    String subStr = newContact.substring(0, j);
                    Integer count = contacts.get(subStr);
                    
                    if (count == null) {
                        contacts.put(subStr, 1);
                    }
                    else {
                        contacts.put(subStr, count + 1);
                    }
                }
            }
            else { // find
                String subStr = queries[i][1];
                Integer count = contacts.get(subStr);
                    
                if (count == null) {
                    countResults.add(0);
                }
                else {
                    countResults.add(count);
                }
            }
        }
        
        int[] ret = new int[countResults.size()];
        for (int i=0; i < ret.length; i++) {
            ret[i] = countResults.get(i);
        }
        
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        
        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            System.out.print(String.valueOf(result[resultItr]));;

            if (resultItr != result.length - 1) {
                System.out.println();
            }
        }
    }
}


/* 
4
add hack
add hackerrank
find hac
find hak
*/