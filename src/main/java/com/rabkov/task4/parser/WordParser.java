package com.rabkov.task4.parser;

import com.rabkov.task4.entity.impl.PunctuationLeaf;
import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser extends AbstractTextParser{
    private final String PUNCTUATION_REGEX = "[.=,!?:;)(\\t\\n]";

    @Override
    public TextComposite parse(String data) throws TextComponentException {
        TextComposite wordComposite = new TextComposite(TextComponentType.WORD);
        char[] lexeme = data.toCharArray();
        Pattern pattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher matcher;
        StringBuilder word = new StringBuilder();
        for (char ch : lexeme) {
            matcher = pattern.matcher(Character.toString(ch));
            if (matcher.matches()) {
                if (word.length() > 0) {
                    TextComposite nextComposite = nextParser.parse(word.toString());
                    wordComposite.add(nextComposite);
                    word = new StringBuilder();
                }
                PunctuationLeaf leaf = new PunctuationLeaf(ch);
                wordComposite.add(leaf);
            } else {
                word.append(ch);
            }
        }
        if (word.length() > 0) {
            TextComposite nextComposite = nextParser.parse(word.toString());
            wordComposite.add(nextComposite);
        }
        return wordComposite;
    }
}
