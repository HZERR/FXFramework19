package ru.hzerr.fx.framework.exception.unchecked;

public class UncheckedConfigurationException extends FXUncheckedException {

    public UncheckedConfigurationException(Throwable cause) {
        super(cause);
    }

    public UncheckedConfigurationException(String message) {
        super(message);
    }
}
