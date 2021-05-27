package com.rabkov.task4.exception;

public class TextComponentException extends Exception{
    public TextComponentException() {
        super();
    }

    public TextComponentException(String message) {
        super(message);
    }

    public TextComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextComponentException(Throwable cause) {
        super(cause);
    }
}
