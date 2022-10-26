package ru.hzerr.fx.framework.exception.unchecked;

public class FactoryCloseableException extends FXUncheckedException {

    public FactoryCloseableException(Exception cause) {
        super("Exception closing the logging factory", cause);
    }
}
