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

public class CMinusScanner implements Scanner {
    
    private BufferedReader inFile;
    private Token nextToken;
    
    public CMinusScanner (BufferedReader file){
        inFile = file;
        nextToken = scanToken();
    }
    
    public Token getNextToken () {
        Token returnToken = nextToken;
        if(nextToken.getType() != Token.TokenType.EOF_TOKEN){
            nextToken = scanToken();
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
        INSTART_COMMENT,
        INEND_COMMENT,
        INLESS,
        INGREATER,
        INEQUAL,
        INNOT_EQUAL,
    }

    public TokenType scanToken() {
        TokenType currentToken;
        StateType state = StateType.START;

        while(state != StateType.DONE) {
            // Get next character
            char c = (char)inFile.read();
            
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
                        state = StateType.INSTART_COMMENT;
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
                        // ADD: unget next char?
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
                case INSTART_COMMENT:
                    
                    break;
                case INEND_COMMENT:
                    break;
                case INLESS:
                    break;
                case INGREATER:
                    break;
                case INEQUAL:
                    break;
                case INNOT_EQUAL:
                    break;
                case DONE:
                default:
                    state = StateType.DONE;
                    currentToken = TokenType.ERROR_TOKEN;
                    break;
            }
        }
        return currentToken;
    }
}