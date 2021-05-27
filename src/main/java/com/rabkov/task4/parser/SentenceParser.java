package com.rabkov.task4.parser;

import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    private static final String SENTENCE_SPLIT_REGEX = "([А-ЯA-Z]((!=|.toString)|[^?!.(]|\\\\([^)]*\\\\))*[.?!]{1,3})";

    @Override
    public TextComposite parse(String data) throws TextComponentException {
        TextComposite sentenceComposite = new TextComposite(TextComponentType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_SPLIT_REGEX);
        Matcher matcher = pattern.matcher(data);
        List<String> sentences = new ArrayList<>();
        while (matcher.find()){
            sentences.add(matcher.group());
        }
        for (String sentence : sentences) {
            TextComposite nextComposite = nextParser.parse(sentence);
            sentenceComposite.add(nextComposite);
        }
        return sentenceComposite;
    }
}
