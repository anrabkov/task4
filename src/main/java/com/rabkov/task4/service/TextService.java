package com.rabkov.task4.service;


import com.rabkov.task4.entity.TextComponent;
import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;

import java.util.List;


public interface TextService {
    List<TextComponent> sortParagraphs(TextComposite composite) throws TextComponentException;

    List<TextComponent> findSentencesWithLongWord(TextComposite composite) throws TextComponentException;

    List<TextComponent> deleteSentencesWithShortWord(TextComposite composite, int minLength) throws TextComponentException;

    List<String> countRepeatWords(TextComposite composite) throws TextComponentException;

    int countVowels(TextComposite composite) throws TextComponentException;

    int countConsonants(TextComposite composite) throws TextComponentException;
}