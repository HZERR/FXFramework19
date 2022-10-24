package ru.hzerr.fx.framework.exception;

public class FXException extends Exception {
    public FXException(String message) { super(message); }
    public FXException(String message, Exception cause) { super(message, cause); }
}
