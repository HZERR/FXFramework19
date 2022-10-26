package ru.hzerr.fx.framework.exception.unchecked;

public class FetchingException extends UncheckedResourceLoadException {

    public FetchingException(String message, Exception cause) {
        super(message, cause);
    }
}
