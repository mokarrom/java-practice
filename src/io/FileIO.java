/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mokarrom
 */
public class FileIO {    
    private final String inputFileName = "input.txt";
    private final String outputFileName = "output.txt";
    private final Charset CHAR_SET = Charset.defaultCharset();
    private final String PUNCTUATION = "\\W+";  //ASCII alphabetic characters
    
    private List<String> readInFile() {
        FileInputStream fileInStream = null;
        BufferedReader buffReader = null;
        List<String> words = new ArrayList<>();
        
        try {
            fileInStream = new FileInputStream(new File(inputFileName));
            buffReader = new BufferedReader(new InputStreamReader(fileInStream, CHAR_SET));
            
            String currLine = null;
                        
            while ((currLine = buffReader.readLine()) != null) {
                String[] currLineWords = currLine.split(PUNCTUATION);
                 
                // skip invalid lines
                if (currLineWords.length < 1 || (currLineWords.length == 1 && currLineWords[0].equalsIgnoreCase(""))) {
                    continue;
                }
 
                words.addAll(new ArrayList<>(Arrays.asList(currLineWords))); 
            }     
        }  
        catch (FileNotFoundException ex) {
            System.out.println("Exception: "+ex.toString());
        } 
        catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (buffReader != null) {
                    buffReader.close();
                }
                
                if (fileInStream != null) {
                    fileInStream.close();
                }
            } 
            catch (Exception ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }
        
        return words;
    }
    
    public void writeToOutFile(List<String> words) throws IOException{
        BufferedWriter buffWriter = null;
        FileOutputStream fileOutStream = null;
        
        try {
            fileOutStream = new FileOutputStream(outputFileName);
            buffWriter = new BufferedWriter(new OutputStreamWriter(fileOutStream));
            
            String lastWord = null;
            
            for (String word : words) {     
                if (word.length() < 1 || word.equalsIgnoreCase(lastWord)) {
                    continue;
                }
                
                buffWriter.write(word);
                buffWriter.newLine();
                
                lastWord = word;
            }
            
            buffWriter.flush();
        }        
        finally {
            if (buffWriter != null){
                buffWriter.close();
            }
            
            if (fileOutStream != null) {
                fileOutStream.close();
            }
        }
    }
    
    public static void main(String[] args) {
        FileIO testIO = new FileIO();
        
        try {
            List<String> words = testIO.readInFile();
            
            Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
            
            testIO.writeToOutFile(words);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
}
