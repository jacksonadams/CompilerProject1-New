package com.mycompany.compilerproject1_new;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jackson Adams and Scott Gocon
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
        // The number of input files that we'll feed into the program to scan
        int inputCount = 5;
        System.out.println("Hello World");
        
        // Go through all the input files we have and scan through them
        for (int i = 1; i <= inputCount; i++){
            // Get the input file that has the C- code
            File inputFile = new File("src/main/java/com/mycompany/compilerproject1_new/input" + i + ".txt");
            FileReader codeFile = new FileReader(inputFile);
            BufferedReader inputReader = new BufferedReader(codeFile);

            // Scan through the file for tokens
            CMinusScanner myScanner = new CMinusScanner(inputReader);

            // Get the output file to print into
            FileWriter outputFile = new FileWriter("src/main/java/com/mycompany/compilerproject1_new/output" + i + ".txt"); 
            Token next = myScanner.getNextToken();
            while(next.getType() != TokenType.EOF_TOKEN){
                outputFile.write(next.toString());
                next = myScanner.getNextToken();
            }
            
            outputFile.close();
            
        }
    }
}
