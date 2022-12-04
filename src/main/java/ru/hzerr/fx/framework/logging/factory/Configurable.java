package ru.hzerr.fx.framework.logging.factory;

import ru.hzerr.fx.framework.core.a.configuration.ILoggingConfiguration;
import ru.hzerr.fx.framework.exception.ConfigurableException;

public interface Configurable {

    void configure() throws ConfigurableException;

      ILoggingConfiguration getConfiguration();
}
