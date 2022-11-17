package ru.hzerr.fx.framework.core.context.config.now;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.core.context.config.Initializable;
import ru.hzerr.fx.framework.core.context.config.Version;
import ru.hzerr.fx.framework.exception.unchecked.DefaultPropertyNotInitializationException;

import java.util.Locale;

/**
 * Любое приложение должно содержать свою базовую конфигурацию.
 * Любой наследующий класс должен реализовывать методы таким образом:</br>
 * - Возвращаемое значение не может быть null</br>
 * - В ином случае реализующая сторона должна кидать исключение {@link DefaultPropertyNotInitializationException}.
 */
public abstract class DefaultConfiguration implements Initializable {

    public abstract @NonNull Version getApplicationVersion() throws DefaultPropertyNotInitializationException;
    public abstract @NonNull Locale getApplicationLocale() throws DefaultPropertyNotInitializationException;
    public abstract @NonNull String getApplicationName() throws DefaultPropertyNotInitializationException;
}
