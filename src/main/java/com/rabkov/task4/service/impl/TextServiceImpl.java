package com.rabkov.task4.service.impl;

import com.rabkov.task4.entity.TextComponent;
import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;
import com.rabkov.task4.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextServiceImpl implements TextService {
    static Logger logger = LogManager.getLogger();
    final String VOWEL_REGEX = "[AEIOUYАЕЁИОУЫЭЮЯaeiouyаеёиоуыэюя]";

    @Override
    public List<TextComponent> sortParagraphs(TextComposite composite) throws TextComponentException {
        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.PARAGRAPH)) {
            logger.error("Can't sort " + composite);
            throw new TextComponentException("Can't sort " + composite);
        }
        List<TextComponent> sortedParagraphs = composite.getList();
        sortedParagraphs.sort(new Comparator<TextComponent>() {
            @Override
            public int compare(TextComponent element1, TextComponent element2) {
                return element1.size() - element2.size();
            }
        });
        return sortedParagraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongWord(TextComposite composite) throws TextComponentException {
        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.PARAGRAPH)) {
            logger.error("Can't sort " + composite);
            throw new TextComponentException("Can't sort " + composite);
        }
        int maxLength = 0;
        List<TextComponent> sentencesWithWord = new ArrayList<>();
        List<TextComponent> paragraphs = composite.getList();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getList();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getList();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getList();
                    for (TextComponent word : words) {
                        if (word.getType().equals(TextComponentType.LETTER)) {
                            if (word.size() > maxLength) {
                                maxLength = word.size();
                                sentencesWithWord.clear();
                                sentencesWithWord.add(sentence);
                            } else if (word.size() == maxLength && !sentencesWithWord.contains(sentence)) {
                                sentencesWithWord.add(sentence);
                            }
                        }
                    }
                }
            }
        }
        return sentencesWithWord;
    }

    @Override
    public List<TextComponent> deleteSentencesWithShortWord(TextComposite composite, int minLength)
            throws TextComponentException {

        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.PARAGRAPH)) {
            logger.error("Can't sort " + composite);
            throw new TextComponentException("Can't sort " + composite);
        }

        List<TextComponent> sentencesWithoutWord = new ArrayList<>();
        List<TextComponent> paragraphs = composite.getList();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getList();
            sentencesWithoutWord.addAll(sentences);
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getList();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getList();
                    for (TextComponent word : words) {
                        if (word.getType().equals(TextComponentType.LETTER)) {
                            if (word.size() < minLength) {
                                sentencesWithoutWord.remove(sentence);
                            }
                        }
                    }
                }
            }
        }
        return sentencesWithoutWord;
    }

    @Override
    public List<String> countRepeatWords(TextComposite composite) throws TextComponentException {
        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.PARAGRAPH)) {
            logger.error("Can't sort " + composite);
            throw new TextComponentException("Can't sort " + composite);
        }
        Map<String, Integer> textWords = new HashMap<>();
        List<TextComponent> paragraphs = composite.getList();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getList();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getList();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getList();
                    for (TextComponent word : words) {
                        if (word.getType().equals(TextComponentType.LETTER)) {
                            int counter = 1;
                            String wordWithoutCase = word.toString().toLowerCase();
                            if (textWords.containsKey(wordWithoutCase)) {
                                counter = textWords.get(wordWithoutCase) + 1;
                            }
                            textWords.put(wordWithoutCase, counter);
                        }
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> word : textWords.entrySet()) {
            if (word.getValue() > 1) {
                result.add(word.getKey());
            }
        }
        return result;
    }

    @Override
    public int countVowels(TextComposite composite) throws TextComponentException {
        Pattern pattern = Pattern.compile(VOWEL_REGEX);
        Matcher matcher;
        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.LEXEME)) {
            logger.error("Can't count vowels in " + composite);
            throw new TextComponentException("Can't count vowels in " + composite);
        }
        int counter = 0;
        List<TextComponent> lexemes = composite.getList();
        for (TextComponent lexeme : lexemes) {
            List<TextComponent> words = lexeme.getList();
            for (TextComponent word : words) {
                if (word.getType().equals(TextComponentType.LETTER)) {
                    List<TextComponent> letters = word.getList();
                    for (TextComponent letter : letters) {
                        matcher = pattern.matcher(letter.toString().toLowerCase());
                        if (matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public int countConsonants(TextComposite composite) throws TextComponentException {

        Pattern pattern = Pattern.compile(VOWEL_REGEX);
        Matcher matcher;
        if (composite == null) {
            logger.error("Composite is null");
            throw new TextComponentException("Composite is null");
        } else if (!composite.getType().equals(TextComponentType.LEXEME)) {
            logger.error("Can't count vowels in " + composite);
            throw new TextComponentException("Can't count vowels in " + composite);
        }
        int counter = 0;
        List<TextComponent> lexemes = composite.getList();
        for (TextComponent lexeme : lexemes) {
            List<TextComponent> words = lexeme.getList();
            for (TextComponent word : words) {
                if (word.getType().equals(TextComponentType.LETTER)) {
                    List<TextComponent> letters = word.getList();
                    for (TextComponent letter : letters) {
                        matcher = pattern.matcher(letter.toString().toLowerCase());
                        if (!matcher.matches()) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }
}