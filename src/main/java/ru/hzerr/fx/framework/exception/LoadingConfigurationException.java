package ru.hzerr.fx.framework.exception;

/**
 * Бросается только в наследниках интерфейса {@link ru.hzerr.fx.framework.core.context.config.initializer.IConfigurationFinder}
 */
public class LoadingConfigurationException extends FXException {

    public LoadingConfigurationException(String message) {
        super(message);
    }
}
