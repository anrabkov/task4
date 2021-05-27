package com.rabkov.task4.reader;

import com.rabkov.task4.exception.TextComponentException;
import com.rabkov.task4.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReader {
    static Logger logger = LogManager.getLogger();

    public String readFromFile(String filePath) throws TextComponentException {
        Util util = new Util();
        String correctFilePath = util.getFilePath(filePath);

        try {
            Path path = Paths.get(correctFilePath);
            String text = Files.readString(path);
            return text;
        } catch (InvalidPathException | IOException e) {
            throw new TextComponentException("Unable to open file" + filePath, e);
        }
    }
}
