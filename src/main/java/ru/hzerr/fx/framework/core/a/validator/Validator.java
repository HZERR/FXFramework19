package ru.hzerr.fx.framework.core.a.validator;

import ru.hzerr.fx.framework.exception.unchecked.now.ValidationException;

public interface Validator<T> {

    T validate(T instance) throws ValidationException;

    default void throwsIf(boolean shouldThrows, String cause) throws ValidationException {
        if (shouldThrows) {
            throw new ValidationException("Your setup failed to pass validation. Cause: " + cause + ". Please initialize your class correctly");
        }
    }

    default void throwsIfThrown(Validation validation, String cause) throws ValidationException {
        try {
            validation.onTry();
        } catch (Exception e) {
            throw new ValidationException("Your setup failed to pass validation. Cause: " + cause + ". Please initialize your class correctly");
        }
    }

    @FunctionalInterface
    interface Validation {
        void onTry() throws Exception;
    }
}
