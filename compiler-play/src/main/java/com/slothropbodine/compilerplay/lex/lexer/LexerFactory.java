package com.slothropbodine.compilerplay.lex.lexer;
import java.io.Reader;

public class LexerFactory {
    public static final String STDLEXER = "Standard Lexer";

    private Reader readerSource;

    public LexerFactory setReaderSource(Reader readerSource) {
        this.readerSource = readerSource;
        return this;
    }

    public Lexer makeLexer (String lexerType){
        Lexer createdLexer;
        switch (lexerType) {
            case STDLEXER:
                System.out.println("standardlexer picked");
                createdLexer = new LexerImpl()
                        .setReaderSource(this.readerSource);
                break;
            default:
                String er_msg = String
                        .format("No lexer: %s defined, check LexerFactory", lexerType);
                throw new java.lang.IllegalArgumentException(er_msg);
        }
        return createdLexer;
    }
}
