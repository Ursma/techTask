package com.ursma.techtask.util.exception;

public class MyExceptionHandler extends RuntimeException {

    public MyExceptionHandler(String... message) {
        super(String.format(message[0], message[1]));
    }

    public MyExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }
}
