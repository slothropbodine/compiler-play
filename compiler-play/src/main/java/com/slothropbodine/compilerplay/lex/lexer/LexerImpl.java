package com.slothropbodine.compilerplay.lex.lexer;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

import com.slothropbodine.compilerplay.lex.Token;
import com.slothropbodine.compilerplay.lex.Token.CLAZZ;
import com.slothropbodine.compilerplay.lex.TokenStream;


public class LexerImpl implements Lexer {

    private EmitTokenStrategy emitTokenStrategy;
    //private TokenizeStrategy tokenizeStrategy;

    private boolean hasReader = false;

    private Reader readerSource;

    public LexerImpl() {
        // This is all bad but meh for now
        this.emitTokenStrategy = new EmitTokenFromReader(this);
        //this.tokenizeStrategy = new TokenizeStrategyImpl(this);
    }

    public LexerImpl(Reader readableSource) {
        this.emitTokenStrategy = new EmitTokenFromReader(this);
        this.setReaderSource(readableSource);
    }

    public void setEmitTokenStrategy(EmitTokenStrategy emitTokenStrategy) {
        this.emitTokenStrategy = emitTokenStrategy;
    }

    //public void setTokenizeStrategy(TokenizeStrategy tokenizeStrategy) {
    //    this.tokenizeStrategy = tokenizeStrategy;
    //}

    public LexerImpl setReaderSource(Reader readerSource) {
        this.hasReader = true;
        this.readerSource = readerSource;
        return this;
    }

    public Reader getReaderSource() throws NoSuchFieldException{
        if(this.hasReader) {
            return this.readerSource;
        }
        throw new NoSuchFieldException("A readerSource has not been set for this Lexer.");
    }


    @Override
    public Token emitToken() {
        try {
            return emitTokenStrategy.emit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.exit(-1);
        return null;
    }

    @Override
    public Iterable<Token> getTokenStream() {
        return new TokenStream(this);
    }
    //@Override
    //public Iterable<Token> tokenize(Readable readable) {
    //    return tokenizeStrategy.getTokenStream(readable);
    //}

    //@Override
    //public Iterable<Token> tokenize(CharSequence charseq) {
    //    return tokenizeStrategy.getTokenStream(charseq);
    //}
}

interface EmitTokenStrategy {
    Token emit() throws NoSuchFieldException, IOException;
}

class EmitTokenFromReader implements EmitTokenStrategy {
    private LexerImpl lexer;

    public EmitTokenFromReader(LexerImpl lexer) {
        this.lexer = lexer;
    }

    @Override
    public Token emit() throws NoSuchFieldException, IOException {
        try {
            String word = this.readWord();
            return new Token().setClazz(CLAZZ.LITERAL).setValue(word);
        } catch (EOFException e) {
            return new Token().setClazz(CLAZZ.EOF).setValue("EOF");
        }
    }

    private String readWord() throws EOFException, NoSuchFieldException, IOException  {
        Reader reader = this.lexer.getReaderSource();

        StringBuilder sb = new StringBuilder();

        char nextChar;
        int rawChar;
        while (true) {
            rawChar = reader.read();
            nextChar = (char) rawChar;

            if (Character.isWhitespace(nextChar) && sb.length() > 0) {
                break;
            } else if (Character.isWhitespace(nextChar) && rawChar != 1) {
                while (Character.isWhitespace(nextChar)) {
                    rawChar = reader.read();
                    nextChar = (char) rawChar;
                }
            }

            if (rawChar == -1) {
                throw new EOFException();
            }

            sb.append(nextChar);
        }
        return sb.toString();
    }
}

//interface TokenizeStrategy {
//    Iterable<Token> getTokenStream();
//    //Iterable<Token> getTokenStream(Readable readable);
//    //Iterable<Token> getTokenStream(CharSequence charseq);
//}
//
//class TokenizeStrategyImpl implements TokenizeStrategy{
//    private Lexer lexer;
//
//    public TokenizeStrategyImpl(Lexer lexer) {this.lexer = lexer;}
//
//    //public Iterable<Token> getTokenStream(Readable readable) {
//    public Iterable<Token> getTokenStream() {
//        return new TokenStream(this.lexer);
//    }
//
//    public Iterable<Token> getTokenStream(CharSequence charseq) {
//        return null;
//    }
//}