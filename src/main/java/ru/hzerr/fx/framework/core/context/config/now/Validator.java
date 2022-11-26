package ru.hzerr.fx.framework.core.context.config.now;

import ru.hzerr.fx.framework.exception.unchecked.now.ValidationException;

public interface Validator<T> {

    T validate(T instance) throws ValidationException;

    default void throwsIf(boolean shouldThrows, String cause) throws ValidationException {
        if (shouldThrows) {
            throw new ValidationException("Your setup failed to pass validation. Cause: " + cause + ". Please initialize your class correctly");
        }
    }
}
