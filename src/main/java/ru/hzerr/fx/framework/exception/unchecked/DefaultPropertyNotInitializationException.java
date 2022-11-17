package ru.hzerr.fx.framework.exception.unchecked;

/**
 * Возникает в случае отсутствия значения для какого-либо дефолтного свойства конфигурации
 */
public class DefaultPropertyNotInitializationException extends PropertyNotInitializationException {

    public DefaultPropertyNotInitializationException(String message) {
        super(message);
    }
}
