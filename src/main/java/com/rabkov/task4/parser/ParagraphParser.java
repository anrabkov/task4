package com.rabkov.task4.parser;

import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;

public class ParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_SPLIT_REGEX = "[\\t\\n]+";

    @Override
    public TextComposite parse(String data) throws TextComponentException {
        TextComposite paragraphComposite = new TextComposite(TextComponentType.PARAGRAPH);
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_REGEX);

        for (String paragraph : paragraphs) {
            TextComposite nextComposite = nextParser.parse(paragraph);
            paragraphComposite.add(nextComposite);

        }
        return paragraphComposite;
    }
}

