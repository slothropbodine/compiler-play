package com.slothropbodine.compilerplay.lex.lexer;

import com.slothropbodine.compilerplay.lex.Token;
import com.slothropbodine.compilerplay.lex.TokenStream;

public interface Lexer {
    Token emitToken();
    Iterable<Token> getTokenStream();
    //Iterable<Token> tokenize(Readable readable);
    //Iterable<Token> tokenize(CharSequence charseq);
}
