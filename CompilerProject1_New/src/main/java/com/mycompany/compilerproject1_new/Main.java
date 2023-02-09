package com.mycompany.compilerproject1_new;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 *
 * @author Jackson Adams, Scott Gocon
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Main {
        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Get the input file that has the C- code
        File inputFile = new File("src/main/java/com/mycompany/compilerproject1_new/input.txt");
        FileReader codeFile = new FileReader(inputFile);
        BufferedReader inputReader = new BufferedReader(codeFile);
        
        CMinusScanner myScanner = new CMinusScanner(inputReader);
        
        // Print the C- code's tokens into an output file
        Token next = myScanner.viewNextToken();
        while(next.getType() != Token.TokenType.ERROR_TOKEN){
            
            PrintStream outputFile = new PrintStream(new File("output.txt"));
            PrintStream console = System.out;
            System.setOut(outputFile);
            
            next.printToken();
            
            next = myScanner.getNextToken();
        } 
    }
}
