package ru.hzerr.fx.framework.logging.factory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import ru.hzerr.fx.framework.core.a.configuration.ILoggingConfiguration;
import ru.hzerr.fx.framework.core.annotation.Inject;
import ru.hzerr.fx.framework.core.annotation.LogProvider;
import ru.hzerr.fx.framework.core.annotation.Tag;
import ru.hzerr.fx.framework.exception.ConfigurableException;

@LogProvider
@Tag("frameworkLogProvider")
public class FXFrameworkLogProvider implements ILogProvider {

    @Inject
    @Tag("applicationLogProvider")
    private ILogProvider applicationLogProvider;

    private Logger nopLogger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("NOPLogger");

    @Override
    public Logger getLogger() {
        if (applicationLogProvider.getConfiguration().isFrameworkLoggingEnabled()) return applicationLogProvider.getLogger();
        else
            return nopLogger;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void configure() throws ConfigurableException {
        applicationLogProvider.configure();
        nopLogger.setLevel(Level.OFF);
    }

    @Override
    public ILoggingConfiguration getConfiguration() {
        return applicationLogProvider.getConfiguration();
    }
}
