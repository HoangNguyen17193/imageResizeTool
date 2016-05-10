package com.image.resize.tool.exception;

public class GeneralException extends RuntimeException {

    private static final long serialVersionUID = -4239244909638576638L;

    public GeneralException(String message, Throwable e) {
        super(message, e);
    }
}
