package com.rabkov.task4.entity.impl;

public enum TextComponentType {
    PARAGRAPH("\n\t", "\t"),
    SENTENCE(" ", ""),
    LEXEME(" ", ""),
    WORD("", ""),
    LETTER("", ""),
    PUNCTUATION("", "");

    private final String delimiter;
    private final String prefix;

    TextComponentType(String delimiter, String prefix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getPrefix() {
        return prefix;
    }
}

