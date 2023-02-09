package com.mycompany.compilerproject1_new;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jacksonadams
 */

import com.mycompany.compilerproject1_new.Token.TokenType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Get the input file that has the C- code
        File inputFile = new File("src/main/java/com/mycompany/compilerproject1_new/input.txt");
        FileReader codeFile = new FileReader(inputFile);
        BufferedReader inputReader = new BufferedReader(codeFile);
        
        // Scan through the file for tokens
        CMinusScanner myScanner = new CMinusScanner(inputReader);
       
        // Get the output file to print into
        FileWriter outputFile = new FileWriter("src/main/java/com/mycompany/compilerproject1_new/output.txt"); 
        
        Token next = myScanner.getNextToken();
        while(next.getType() != TokenType.EOF_TOKEN){
            outputFile.write(next.toString());
            next = myScanner.getNextToken();
        }
        
        outputFile.close();
    }
}
