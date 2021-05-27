package com.rabkov.task4.entity.impl;

import com.rabkov.task4.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class SymbolLeaf implements TextComponent {
    private static Logger logger = LogManager.getLogger();
    private char symbol;

    public SymbolLeaf(String symbol) {
        this.symbol = symbol.charAt(0);
    }

    @Override
    public void add(TextComponent component) {
        logger.warn("Can't add " + component);
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent component) {
        logger.warn("Can't remove " + component);
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public List<TextComponent> getList() {
        logger.warn("Hasn't got list");
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getChild(int index) {
        logger.warn("Hasn't got child by " + index);
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponentType getType() {
        return TextComponentType.LETTER;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}