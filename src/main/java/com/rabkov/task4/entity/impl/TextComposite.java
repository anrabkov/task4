package com.rabkov.task4.entity.impl;

import com.rabkov.task4.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public class TextComposite implements TextComponent {
    private TextComponentType textComponentType;
    private List<TextComponent> textComponents = new ArrayList<>();


    public TextComposite(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }
    @Override
    public void add(TextComponent component) {
        textComponents.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        textComponents.remove(component);
    }

    @Override
    public int size() {
        return textComponents.size();
    }

    @Override
    public List<TextComponent> getList() {
        return textComponents;
    }

    @Override
    public Object getChild(int index) {
        return textComponents.get(index);
    }

    @Override
    public TextComponentType getType() {
        return textComponentType;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(textComponentType.getDelimiter(),
                textComponentType.getPrefix(), "");
        for (TextComponent textComponent : textComponents) {
            stringJoiner.add(textComponent.toString());
        }
        return stringJoiner.toString();
    }
}
