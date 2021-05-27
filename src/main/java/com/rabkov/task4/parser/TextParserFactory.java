package com.rabkov.task4.parser;

public class TextParserFactory {
    private TextParserFactory() {
    }


    public static AbstractTextParser createParser() {
        LetterParser letterParser = new LetterParser();
        LexemeParser lexemeParser = new LexemeParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(letterParser);

        return paragraphParser;
    }
}
