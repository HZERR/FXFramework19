package ru.hzerr.fx.framework.exception;

public class FactoryCloseableException extends FXUncheckedException {

    public FactoryCloseableException(Exception cause) {
        super("Exception closing the logging factory", cause);
    }
}
