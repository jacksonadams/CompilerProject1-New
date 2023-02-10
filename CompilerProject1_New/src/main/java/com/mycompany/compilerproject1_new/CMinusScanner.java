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
        INERROR
    }
    
    public Token scanToken() throws IOException {
        TokenType currentToken = TokenType.ERROR_TOKEN;
        StateType state = StateType.START;
        
        char c;
        String data = "";

        while(state != StateType.DONE) {
            // Mark place before moving
            inFile.mark(1);
            
            // Get next character, place as c
            int charValue = inFile.read();
            c = (char)charValue;
            
            // Loop through all possible states
            switch(state){
                case START:
                    if(Character.isDigit(c)){
                        data += c;
                        state = StateType.INNUM;
                    } else if (Character.isLetter(c)){
                        data += c;
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
                    } else if (charValue == -1){
                        currentToken = TokenType.EOF_TOKEN;
                        state = StateType.DONE;
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
                                currentToken = TokenType.RIGHT_BRACE_TOKEN;
                                break;
                        }
                    }
                    break;
                case INID:
                    if(Character.isLetter(c)){
                        data += c;
                    } else if (Character.isDigit(c)){
                        state = StateType.INERROR;
                    } else {
                        state = StateType.DONE;
                        currentToken = TokenType.IDENT_TOKEN;
                        inFile.reset();
                    }
                    break;
                case INNUM:
                    if(Character.isDigit(c)){
                        data += c;
                    } else if (Character.isLetter(c)){
                        state = StateType.INERROR;
                    } else {
                        state = StateType.DONE;
                        currentToken = TokenType.NUM_TOKEN;
                        inFile.reset();
                    }
                    break;
                case INDIVIDE:
                    if(c == '*'){
                        state = StateType.INCOMMENT;
                    } else {
                        state = StateType.DONE;
                        currentToken = TokenType.DIVIDE_TOKEN;
                        inFile.reset();
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
                        inFile.reset();
                    } 
                    break;
                case INGREATER:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.GREATER_EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.GREATER_TOKEN;
                        inFile.reset();
                    }
                    break;
                case INEQUAL:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.ASSIGN_TOKEN;
                        inFile.reset();
                    } 
                    break;
                case INNOT_EQUAL:
                    state = StateType.DONE;
                    if(c == '='){
                        currentToken = TokenType.NOT_EQUAL_TOKEN;
                    } else {
                        currentToken = TokenType.ERROR_TOKEN;
                        inFile.reset();
                    }
                    break;
                case INERROR:
                    if(c == ' ' || c == '\n' || c == '\t'){
                        state = StateType.DONE;
                        currentToken = TokenType.ERROR_TOKEN;
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
        
        // Check if the identifier is a keyword
        if(currentToken == TokenType.IDENT_TOKEN){
            switch(data){
                case "else":
                    returnToken = new Token(TokenType.ELSE_TOKEN);
                    break;
                case "if":
                    returnToken = new Token(TokenType.IF_TOKEN);
                    break;
                case "return":
                    returnToken = new Token(TokenType.RETURN_TOKEN);
                    break;
                case "void":
                    returnToken = new Token(TokenType.VOID_TOKEN);
                    break;
                case "while":
                    returnToken = new Token(TokenType.WHILE_TOKEN);
                    break;
                case "int":
                    returnToken = new Token(TokenType.INT_TOKEN);
                    break;
            }
        }
        
        // If our token is a num or identifier, it needs data
        if(returnToken.getType() == TokenType.NUM_TOKEN){
            returnToken.setData(Integer.parseInt(data));
        }
        if(returnToken.getType() == TokenType.IDENT_TOKEN){
            returnToken.setData(data);
        }
        
        return returnToken;
    }
}
