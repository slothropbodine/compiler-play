package com.slothropbodine.compilerplay.lex;

import java.util.Iterator;

import com.slothropbodine.compilerplay.lex.lexer.Lexer;
import com.slothropbodine.compilerplay.lex.Token;

public class TokenStream implements Iterable<Token> {
    Lexer lexer;

    public TokenStream(Lexer lexer) {
        this.lexer = lexer;
    }

    public Iterator<Token> iterator() {
        return new TokenIterator(this);
    }
}

class TokenIterator implements Iterator<Token> {
    TokenStream ts;

    Token bufferedToken;

    TokenIterator(TokenStream ts) {
        this.ts = ts;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        this.bufferToken();
        if (this.bufferedToken.getClazz() == Token.CLAZZ.EOF) {
            return false;
        }
        return true;
    }

    // moves the cursor/iterator to next element
    public Token next() {
        this.bufferToken();
        Token retToken = this.bufferedToken;
        this.bufferedToken = null;
        return retToken;
    }

    public void remove() {
    }

    private void bufferToken() {
        if (this.bufferedToken == null) {
            this.bufferedToken = this.ts.lexer.emitToken();
        }
    }

}
