package ru.hzerr.fx.framework.exception;

import ru.hzerr.fx.framework.core.a.finder.IConfigurationFinder;

/**
 * Бросается только в наследниках интерфейса {@link IConfigurationFinder}
 */
public class LoadingConfigurationException extends FXException {

    public LoadingConfigurationException(String message) {
        super(message);
    }
}
