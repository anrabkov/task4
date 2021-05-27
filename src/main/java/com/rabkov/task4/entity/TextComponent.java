package com.rabkov.task4.entity;

import com.rabkov.task4.entity.impl.TextComponentType;

import java.util.Collection;
import java.util.List;

public interface TextComponent {
    void add(TextComponent component);

    void remove(TextComponent component);

    int size();

    List<TextComponent> getList();

    Object getChild(int index);

    TextComponentType getType();

    String toString();
}