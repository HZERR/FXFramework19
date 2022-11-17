package ru.hzerr.fx.framework.exception.unchecked;

/**
 * Возникает в случае отсутствия значения для какого-либо свойства конфигурации
 */
public class PropertyNotInitializationException extends FXUncheckedException {

    public PropertyNotInitializationException(String message) {
        super(message);
    }
}
