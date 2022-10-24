package ru.hzerr.fx.framework.exception;

public class FXUncheckedException extends RuntimeException {

    public FXUncheckedException(String message) {
        super(message);
    }

    public FXUncheckedException(Throwable cause) { super(cause); }

    public FXUncheckedException(String message, Exception cause) {
        super(message, cause);
    }
}
