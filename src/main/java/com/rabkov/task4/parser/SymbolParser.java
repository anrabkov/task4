package com.rabkov.task4.parser;

import com.rabkov.task4.entity.impl.SymbolLeaf;
import com.rabkov.task4.entity.impl.TextComponentType;
import com.rabkov.task4.entity.impl.TextComposite;

public class SymbolParser extends AbstractTextParser{
    @Override
    public TextComposite parse(String data) {
        TextComposite symbolComposite = new TextComposite(TextComponentType.LETTER);
        char[] symbols = data.toCharArray();
        for (char symbol : symbols) {
            SymbolLeaf leaf = new SymbolLeaf(Character.toString(symbol));
            symbolComposite.add(leaf);
        }
        return symbolComposite;
    }
}
