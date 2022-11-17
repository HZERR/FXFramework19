package ru.hzerr.fx.framework.exception.unchecked.now;

import ru.hzerr.fx.framework.core.context.config.Initializable;
import ru.hzerr.fx.framework.exception.FXException;

/**
 * Данная ошибка может возникать тогда и только тогда, когда происходит любая ошибка при инициализации в методе {@link Initializable#initialize()}.
 * Все ошибки возникающее в этом методе оборачиваются данной ошибкой.
 */
public class InitializationException extends FXException {

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Exception cause) {
        super(message, cause);
    }
}
