package com.slothropbodine.compilerplay.lex;

public class Token {
    private String value;

    public String getValue() {
        return value;
    }

    public Token setValue(String value) {
        this.value = value;
        return this;
    }

    public enum CLAZZ {
        LITERAL, VARIABLE, OPERATOR, EOF
    }

    private CLAZZ clazz;

    public CLAZZ getClazz() {
        return clazz;
    }

    public Token setClazz(CLAZZ clazz) {
        this.clazz = clazz;
        return this;
    }

    public String toString() {
        return String.format("<%s : %s>", this.getValue(), this.getClazz().toString());
    }
}
