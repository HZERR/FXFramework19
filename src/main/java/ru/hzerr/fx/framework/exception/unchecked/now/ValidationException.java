package ru.hzerr.fx.framework.exception.unchecked.now;

import ru.hzerr.fx.framework.core.a.validator.Validator;
import ru.hzerr.fx.framework.exception.unchecked.FXUncheckedException;

/**
 * Данная ошибка возникает только в случае ошибки валидации данных, в определенных пользователем классах.
 * Данная ошибка может генерироваться только наследниками класса {@link Validator}
 */
public class ValidationException extends FXUncheckedException {

    public ValidationException(String message) {
        super(message);
    }
}
