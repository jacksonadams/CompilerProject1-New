/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compilerproject1_new;

/**
 *
 * @author jacksonadams
 */
import com.mycompany.compilerproject1_new.Token.TokenType;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CMinusScanner implements Scanner {
    
    private BufferedReader inFile;
    private Token nextToken;
    
    public CMinusScanner (BufferedReader file) throws IOException {
        inFile = file;
        nextToken = scanToken();
    }
    
    public Token getNextToken () {
        Token returnToken = nextToken;
        if(nextToken.getType() != TokenType.EOF_TOKEN){
            try {
                nextToken = scanToken();
            } catch (IOException ex) {
                System.out.println("exception");
            }
        }
        return returnToken;
    }
    public Token viewNextToken(){
        return nextToken;
    }

    public enum StateType {
        START,
        DONE,
        INID,
        INNUM,
        INDIVIDE,
        INCOMMENT,
        INEND_COMMENT,
        INLESS,
        INGREATER,
        INEQUAL,
        INNOT_EQUAL,
    }

    public Token scanToken() throws IOException {
        TokenType currentToken = TokenType.ERROR_TOKEN;
        StateType state = StateType.START;

        while(state != StateType.DONE) {
            // Get next character
            char c = (char)(inFile.read());
            
            
            System.out.println("state: " + state);
            System.out.println("c: " + c);
            
            // Loop through all possible states
            switch(state){
                case START:
                    if(Character.isDigit(c)){
                        state = StateType.INNUM;
                    } else if (Character.isLetter(c)){
                        state = StateType.INID;
                    } else if (c == '!'){
                        state = StateType.INNOT_EQUAL;
                    } else if (c == '>'){
                        state = StateType.INGREATER;
                    } else if (c == '<'){
                        state = StateType.INLESS;
                    } else if (c == '='){
                        state = StateType.INEQUAL;
                    } else if (c == '/'){
                        state = StateType.INDIVIDE;
                    } else if (c == ' ' || c == '\t' || c == '\n'){
                        // nothing
                    } else {
                        state = StateType.DONE;
                        switch(c){
                            case '+':
                                currentToken = TokenType.PLUS_TOKEN;
                                break;
                            case '-':
                                currentToken = TokenType.MINUS_TOKEN;
                                break;
                            case '*':
                                currentToken = TokenType.MULT_TOKEN;
                                break;
                            case ';':
                                currentToken = TokenType.SEMI_TOKEN;
                                break;
                            case ',':
                                currentToken = TokenType.COMMA_TOKEN;
                                break;
                            case '(':
                                currentToken = TokenType.LEFT_PAREN_TOKEN;
                                break;
                            case ')':
                                currentToken = TokenType.RIGHT_PAREN_TOKEN;
                                break;
                            case '[':
                                currentToken = TokenType.LEFT_BRACKET_TOKEN;
                                break;
                            case ']':
                                currentToken = TokenType.RIGHT_BRACKET_TOKEN;
                                break;
                            case '{':
                                currentToken = TokenType.LEFT_BRACE_TOKEN;
                                break;
                            case '}':
                                currentToken = TokenType.LEFT_BRACE_TOKEN;
                                break;
                        }
                    }
                    break;
                case INID:
                    if(!Character.isLetter(c)){
                        state = StateType.DONE;
                        currentToken = TokenType.IDENT_TOKEN;
                    }
                    break;
                case INNUM:
                    if(!Character.isDigit(c)){
                        state = StateType.DONE;
                        currentToken = TokenType.NUM_TOKEN;
                    }
                    break;
                case INDIVIDE:
                    if(c == '*'){
                        state = StateType.INCOMMENT;
                    } else {
                        state = StateType.DONE;
                        currentToken = TokenType.DIVIDE_TOKEN;
                    }
                    break;
                case INCOMMENT:
                    if(c == '*'){
                        state = StateType.INEND_COMMENT;
                    }
                    break;
                case INEND_COMMENT:
                    if(c == '/'){
                        state = StateType.START;
                    } else {
                        state = StateType.INCOMMENT;
                    }
                    break;
                case INLESS:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.LESS_EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.LESS_TOKEN;
                        // ADD: Back up
                    }
                    break;
                case INGREATER:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.GREATER_EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.GREATER_TOKEN;
                        // ADD: Back up
                    }
                    break;
                case INEQUAL:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.ASSIGN_TOKEN;
                        // ADD: Back up
                    }
                    break;
                case INNOT_EQUAL:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.NOT_EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.ERROR_TOKEN;
                        // ADD: Back up
                    }
                    break;
                case DONE:
                default:
                    state = StateType.DONE;
                    currentToken = TokenType.ERROR_TOKEN;
                    break;
            }
        }
       
        Token returnToken = new Token(currentToken);
        
        //System.out.println("currentToken at end of function:" + currentToken + "\n\n");
        return returnToken;
    }
}