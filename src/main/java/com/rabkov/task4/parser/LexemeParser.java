package com.rabkov.task4.parser;

import com.rabkov.task4.entity.TextComponent;
import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;


public class LexemeParser extends AbstractTextParser {
    private static final String LEXEME_SPLIT_REGEX = "\\s";


    @Override
    public TextComposite parse(String data) throws TextComponentException {
        TextComposite lexemeComposite = new TextComposite(TextComponentType.LEXEME);
        String[] lexemes = data.split(LEXEME_SPLIT_REGEX);

        for (String lexeme : lexemes) {
            TextComposite nextComposite = nextParser.parse(lexeme);
            lexemeComposite.add(nextComposite);
        }
        return lexemeComposite;
    }
}

