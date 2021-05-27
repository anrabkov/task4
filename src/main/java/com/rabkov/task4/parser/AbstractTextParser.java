package com.rabkov.task4.parser;


import com.rabkov.task4.entity.impl.TextComposite;
import com.rabkov.task4.exception.TextComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser = DefaultTextParser.getParser();

    public void setNextParser(AbstractTextParser textParser) {
        this.nextParser = textParser;
    }

    public abstract TextComposite parse(String data) throws TextComponentException;

    private static class DefaultTextParser extends AbstractTextParser {
        private static Logger logger = LogManager.getLogger();
        private static DefaultTextParser parser = new DefaultTextParser();

        public static DefaultTextParser getParser() {
            return parser;
        }

        @Override
        public TextComposite parse(String data) {
            return null;
        }
    }
}