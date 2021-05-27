package com.rabkov.task4.util;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Util {

    public String getFilePath(String filePath) {
        URL url = getClass().getClassLoader().getResource(filePath);
        String correctFilePath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8).substring(1);
        return correctFilePath;
    }

}
