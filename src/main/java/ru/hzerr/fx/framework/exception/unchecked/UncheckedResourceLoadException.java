package ru.hzerr.fx.framework.exception.unchecked;

public class UncheckedResourceLoadException extends FXUncheckedException {

    public UncheckedResourceLoadException(String message) {
        super(message);
    }
    public UncheckedResourceLoadException(String message, Exception cause) { super(message, cause); }
}
