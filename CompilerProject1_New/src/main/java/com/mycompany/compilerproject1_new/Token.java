/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.compilerproject1_new;

/**
 *
 * @author jacksonadams
 */
public class Token {
    public enum TokenType {
        IDENT_TOKEN,
        ELSE_TOKEN,
        IF_TOKEN,
        INT_TOKEN,
        RETURN_TOKEN,
        VOID_TOKEN,
        WHILE_TOKEN,
        PLUS_TOKEN,
        MINUS_TOKEN,
        MULT_TOKEN,
        DIVIDE_TOKEN,
        LESS_TOKEN,
        LESS_EQUAL_TOKEN,
        GREATER_TOKEN,
        GREATER_EQUAL_TOKEN,
        EQUAL_TOKEN,
        NOT_EQUAL_TOKEN,
        ASSIGN_TOKEN,
        SEMI_TOKEN,
        COMMA_TOKEN,
        LEFT_PAREN_TOKEN,
        RIGHT_PAREN_TOKEN,
        LEFT_BRACE_TOKEN,
        RIGHT_BRACE_TOKEN,
        LEFT_BRACKET_TOKEN,
        RIGHT_BRACKET_TOKEN,
        START_COMMENT_TOKEN,
        END_COMMENT_TOKEN,
        EOF_TOKEN,
        ERROR_TOKEN,
        NUM_TOKEN
    }
    
    private TokenType tokenType;
    private Object tokenData;
    
    public Token (TokenType type){
        this(type, null);
    }
    
    public Token(TokenType type, Object data){
        tokenType = type;
        tokenData = data;
    }
    
    public TokenType getType (){
        return tokenType;
    }
    
    public void setData(Object data){
        tokenData = data;
    }
    
    public String toString (){
        String returnString = "";
        
        returnString += tokenType;
        if(tokenData != null){
            returnString += " " + tokenData;
        }
        returnString += "\n";
        
        return returnString;
    }
}
