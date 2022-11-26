package ru.hzerr.fx.framework.exception.unchecked.now;

import ru.hzerr.fx.framework.exception.unchecked.FXUncheckedException;

/**
 * Данная ошибка возникает только в случае ошибки валидации данных, в определенных пользователем классах.
 * Данная ошибка может генерироваться только наследниками класса {@link ru.hzerr.fx.framework.core.context.config.now.Validator}
 */
public class ValidationException extends FXUncheckedException {

    public ValidationException(String message) {
        super(message);
    }
}
