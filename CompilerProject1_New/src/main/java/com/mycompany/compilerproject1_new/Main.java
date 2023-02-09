package com.mycompany.compilerproject1_new;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jacksonadams
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File inputFile = new File("src/main/java/com/mycompany/compilerproject1_new/input.txt");
        
        FileReader file = new FileReader(inputFile);
        BufferedReader input = new BufferedReader(file);
        
        CMinusScanner myScanner = new CMinusScanner(input);
        
        Token next = myScanner.viewNextToken();
        next.printToken();
    }
}
