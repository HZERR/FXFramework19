package ru.hzerr.fx.framework.exception;

public class ConfigurableException extends FXException {

    public ConfigurableException(String message) {
        super(message);
    }

    public ConfigurableException(String message, Exception cause) {
        super(message, cause);
    }
}
