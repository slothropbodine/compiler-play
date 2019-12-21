package com.slothropbodine.compilerplay;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import com.slothropbodine.compilerplay.lex.lexer.Lexer;
import com.slothropbodine.compilerplay.lex.Token;
import com.slothropbodine.compilerplay.lex.lexer.LexerFactory;

import static com.slothropbodine.compilerplay.lib.Linux.STDIN;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        Reader stdinReader = new java.io.FileReader(new File(STDIN));
        Lexer l = new LexerFactory()
                .setReaderSource(stdinReader)
                .makeLexer("Standard Lexer");
        for (Token t : l.getTokenStream()) {
            System.out.println(t);
        }
    }
}
