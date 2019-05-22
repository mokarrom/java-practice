/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.HashMap;

/**
 *
 * @author Mokarrom
 */
public class TrieBasic {
    /**
     * Inner class for holding the Trie node - value of current node, left and right child of current node.
     */
    class TrieNode {
        private final HashMap<Character, TrieNode> children = new HashMap<>();
        private boolean isWord;
        private char c;
        
        public TrieNode() {
            isWord = false;
        }
        
        public TrieNode(Character c) {
            this.c = c;
            isWord = false;
        }
        
        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }
        
        public char getContent() {
            return c;
        } 
        
        public void setWord(boolean b) {
            isWord = b;
        }
     
        public boolean isWord() {
            return isWord;
        }
    }
    
    private TrieNode root;
    
    public TrieBasic() {
        root = new TrieNode();
    }
    
    
    public void insert(String word) {
        if (word == null || word.length() < 1) {
            return;
        } 
        
        TrieNode curNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curNode.children.containsKey(c)) {
                curNode = curNode.children.get(c);
            }
            else {
                TrieNode newNode = new TrieNode(c);
                curNode.children.put(c, newNode);
                curNode = newNode;
            }
        }
        
        curNode.isWord = true;
    }
    
    public boolean search(String word) {
        if (word == null || word.length() < 1) {
            return false;
        }            
        
        TrieNode node = searchNode(word);
        
        if (node == null || node.isWord == false) {
            return false;
        }
        
        return true;
    }
   
    private TrieNode searchNode(String word) {
        TrieNode curNode = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            
            if (curNode.children.containsKey(c)) {
                curNode = curNode.children.get(c);
            }
            else {
                return null;
            }
        }
        
        return curNode;
    }
    
    public void delete(String word) {
        if (root == null || word == null) {
            System.out.println("Null key or Empty trie error");
        }
        
        deleteNode(root, word, 0);
    }
    
    private boolean deleteNode(TrieNode curNode, String word, int index) {
        if (curNode == null) {
            return false;
        }
        
        if (index == word.length()) { //base case: end of key
            if (curNode.isWord == false){
                return false;
            }
            
            curNode.isWord = false;
            return curNode.children.isEmpty();
        }
        
        // recursive case: 
        char ch = word.charAt(index);
        TrieNode childNode = curNode.children.get(ch);
        boolean childDeleted = deleteNode(childNode, word, index + 1);
        
        if (childDeleted) {
            curNode.children.remove(ch);
            
            if (curNode.isWord) {
                return false;
            }
            
            return curNode.children.isEmpty();
        }
        else {
            return false;
        }   
    }
    
    public static void main(String[] args) {
//        testCase1();
        testCase2();
    }
    
    public static void testCase1() {
        TrieBasic trieTest = new TrieBasic();
        String[] words = new String[]{"bear", "bell", "bid", "bull", "buy", "sell", "stock", "stop"};
        
        for (String word : words) {
            trieTest.insert(word);
        }
        
        trieTest.insert("star");
        trieTest.delete("stop");
        
        System.out.println("search: " + trieTest.search("star"));
    }
    
    public static void testCase2() {
        TrieBasic trieTest = new TrieBasic();
        String[] words = new String[]{"abc", "xy", "xyz", "abb", "xyzb", "word"};
        
        for (String word : words) {
            trieTest.insert(word);
        }
        
        System.out.println("search: " + trieTest.search("xyzb"));
        
        trieTest.insert("ab");
        trieTest.delete("ab");
        
        System.out.println("search: " + trieTest.search("abc"));
    }
}
