package ru.hzerr.fx.framework.core.a.configuration;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.core.a.Version;
import ru.hzerr.fx.framework.exception.unchecked.DefaultPropertyNotInitializationException;

import java.util.Locale;

/**
 * Любое приложение должно содержать свою базовую конфигурацию.
 * Любой имплементирующий класс должен реализовывать методы таким образом:</br>
 * - Возвращаемое значение не может быть null</br>
 * - В ином случае реализующая сторона должна кидать исключение {@link DefaultPropertyNotInitializationException}.
 */
public interface IBaseConfiguration {

    @NonNull Version getApplicationVersion() throws DefaultPropertyNotInitializationException;
    @NonNull Locale getApplicationLocale() throws DefaultPropertyNotInitializationException;
    @NonNull String getApplicationName() throws DefaultPropertyNotInitializationException;
}
