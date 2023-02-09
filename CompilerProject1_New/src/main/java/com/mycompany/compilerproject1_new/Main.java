package com.mycompany.compilerproject1_new;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 *
 * @author Jackson Adams, Scott Gocon
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader fileReader = null;
        CMinusScanner customScanner  = null;
        
        // Try to open the input file then scan it with our scanner
        try {
            String curLine;

            fileReader = new BufferedReader(new FileReader("input.txt"));
            
            // Instantiate our CMinusScanner
            customScanner = new CMinusScanner(fileReader);

        } 
        
        // Catch any errors in opening the file
        catch (IOException e) {
            e.printStackTrace();
        } 
        
        // ;
    }
}
