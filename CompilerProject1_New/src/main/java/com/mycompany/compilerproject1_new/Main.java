package com.mycompany.compilerproject1_new;

/**
 *
 * @author Jackson Adams, Scott Gocon
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
        
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Get the input file that has the C- code
        File inputFile = new File("src/main/java/com/mycompany/compilerproject1_new/input.txt");
        FileReader codeFile = new FileReader(inputFile);
        BufferedReader inputReader = new BufferedReader(codeFile);
        
        // Scan through the file for tokens
        CMinusScanner myScanner = new CMinusScanner(inputReader);
        Token nextToken = myScanner.viewNextToken();
        nextToken = myScanner.getNextToken();
        
        // Get the output file to print into
        FileWriter outputFile = new FileWriter("src/main/java/com/mycompany/compilerproject1_new/output.txt");
        
        // Print the tokens into the output file
        int debugLoopCount = 5;             // Until we can test for the EOF, it will try printing the first 5 tokens
        while(debugLoopCount > 0){
            outputFile.write(nextToken.toString());
            debugLoopCount--;
        }
        
        outputFile.close();
    }
}
