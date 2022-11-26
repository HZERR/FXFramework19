package ru.hzerr.fx.framework.exception.unchecked.now;

import ru.hzerr.fx.framework.exception.FXException;

public class ClassCreationException extends FXException {

    public ClassCreationException(Exception cause, String message) {
        super(message, cause);
    }
}
