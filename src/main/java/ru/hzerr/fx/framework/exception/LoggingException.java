package ru.hzerr.fx.framework.exception;

public class LoggingException extends FXException {

    public LoggingException(String message) {
        super(message);
    }

    public LoggingException(String message, Exception cause) {
        super(message, cause);
    }
}
