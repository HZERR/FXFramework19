package ru.hzerr.fx.framework.config.basic;

import ru.hzerr.fx.framework.config.ConfigurationBuilder;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;
import ru.hzerr.fx.framework.exception.unchecked.UncheckedConfigurationException;

public class BasicConfigurationBuilder implements ConfigurationBuilder<BasicConfiguration> {

    private static final BasicConfiguration basicConfiguration = new BasicConfiguration();

    public BasicConfigurationBuilder() {
    }

    @Override
    public BasicConfiguration getConfiguration() {
        try {
            basicConfiguration.getBasicConfiguration().load(FxApplicationContext.getInitializationSettings()
                    .getResourceClassLoaderOfBasicConfiguration()
                    .getResourceAsStream("fx.properties"));
        } catch (NullPointerException npe) { throw new UncheckedConfigurationException("Resource file fx.properties was not found. Please create a new resource file and try again.");
        } catch (Exception e) { throw new UncheckedConfigurationException(e); }

        return basicConfiguration;
    }
}
