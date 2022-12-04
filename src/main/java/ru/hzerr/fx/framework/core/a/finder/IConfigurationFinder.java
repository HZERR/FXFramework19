package ru.hzerr.fx.framework.core.a.finder;

import ru.hzerr.fx.framework.exception.LoadingConfigurationException;

public interface IConfigurationFinder<T> {

    T fetch() throws LoadingConfigurationException;
}
