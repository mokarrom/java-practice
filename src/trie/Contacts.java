/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Mokarrom
 */
public class Contacts {
    /**
     * Inner class for holding the Trie node - value of current node, left and right child of current node.
     */
    class TrieNode {
        private static final int ALPHABET_SIZE = 26;    // number of characters
        
        TrieNode[] children;
        boolean isWord;
        
        public TrieNode() {
            isWord = false;
            children = new TrieNode[ALPHABET_SIZE];
        }
        
        public boolean hasChildren() {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                if (children[i] != null) {
                    return true;
                }
            }
            
            return false;
        }
    }
    
    private final TrieNode root;
    
    public Contacts() {
        root = new TrieNode();
    }
    
    public void add(String word) {
        if (word == null || word.length() < 1) {
            return;
        }
        
        TrieNode curNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            int childIdx = getCharIdx(word.charAt(i));
            
            if (curNode.children[childIdx] != null) {
                curNode = curNode.children[childIdx];                
            }
            else {
                TrieNode newNode = new TrieNode();
                curNode.children[childIdx] = newNode;
                curNode = newNode;
            }
        }
        
        curNode.isWord = true;
    }
    
    public boolean find(String word) {
        if (word == null || word.length() < 1) {
            return false;
        }
        
        TrieNode node = findNode(word);
        
        return node != null && node.isWord == true;
    }
    
    public List findPrefixes(String wordPrefix) {
        if (wordPrefix == null || wordPrefix.length() < 1) {
            return null;
        }
        
        ArrayList<String> prefixes = new ArrayList<>();
        
        TrieNode node = findNode(wordPrefix);
        
        if (node != null) {
            return prefixes;
        }
        
        return null;
    }
    
    private TrieNode findNode(String word) {
        TrieNode curNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            int childIdx = getCharIdx(word.charAt(i));
            
            if (curNode.children[childIdx] != null) {
                curNode = curNode.children[childIdx];
            }
            else {
                return null;
            }
        }
        
        return curNode;
    }
    
    public void delete(String word) {
        if (word == null || root == null) {
            return;
        }
        
        delteNode(root, word, 0);
    }
    
    private boolean delteNode(TrieNode curNode, String word, int index) {
        if (curNode == null) {
            return false;
        }
        
        if (index == word.length()) { // base case:
            if (curNode.isWord == false) {
                return false;
            }
            
            curNode.isWord = false;
            return curNode.hasChildren() == false;
        }
        
        // recursive case
        int childIdx = getCharIdx(word.charAt(index));
        TrieNode childNode = curNode.children[childIdx];
        boolean childDeleted = delteNode(childNode, word, index + 1);

        if (childDeleted) {
            curNode.children[childIdx] = null;
            
            if (curNode.isWord) {
                return false;
            }
            
            return curNode.hasChildren() == false;
        }
        else {
            return false;
        }
    }
    
    private int getCharIdx(char c) {
        return c - 'a';
    }
    
    public static void main(String[] args) {
        Contacts contacts = new Contacts();
        String[] defaultContacts = new String[]{"john", "kim", "ben", "brock", "brian", "richard", "hong", "sami", "david", "bk", "james", "neb"};
        
        for (String contact : defaultContacts) {
            contacts.add(contact);
        }
        
        
        String inpurStr = null;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your command: {add, find} contact_name");
        
        do {
            System.out.print("--> ");
            inpurStr = input.nextLine().trim();
            
            if (inpurStr.equalsIgnoreCase("exit")) {
                break;
            }
            
            StringTokenizer toks = new StringTokenizer(inpurStr, " ");
            String command = toks.nextToken();
            String name = toks.nextToken();
            
            if (command.equalsIgnoreCase("add")) {
                contacts.add(name);
            }
            else if (command.equals("find")) {
                if (contacts.find(name)) {
                    System.out.println(name + " found!");
                }
                else {
                    System.out.println(name + " not found!");
                }
            }
            else if (command.equalsIgnoreCase("delete")) {
                contacts.delete(name);
            }
            else {
                System.out.println("Invalid command!");
            }       
        } while (true);
        
        System.out.println("End successfully!"); 
    }
}
