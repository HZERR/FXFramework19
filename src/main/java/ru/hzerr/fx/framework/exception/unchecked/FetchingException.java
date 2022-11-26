package ru.hzerr.fx.framework.exception.unchecked;

// Бросается при поиске ресурсов классов
public class FetchingException extends UncheckedResourceLoadException {

    public FetchingException(String message, Exception cause) {
        super(message, cause);
    }

    public FetchingException(String message) {
        super(message);
    }
}
