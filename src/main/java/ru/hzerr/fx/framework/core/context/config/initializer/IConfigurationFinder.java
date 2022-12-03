package ru.hzerr.fx.framework.core.context.config.initializer;

import ru.hzerr.fx.framework.exception.LoadingConfigurationException;

public interface IConfigurationFinder<T> {

    T fetch() throws LoadingConfigurationException;
}
