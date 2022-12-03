package ru.hzerr.fx.framework;

import ru.hzerr.fx.framework.core.annotation.Initializer;
import ru.hzerr.fx.framework.core.context.config.initializer.ConfigurationInitializer;
import ru.hzerr.fx.framework.core.context.config.now.IStructureConfiguration;

@Initializer(value = StructureConfiguration.class)
public class StructureConfigurationInitializer implements ConfigurationInitializer<IStructureConfiguration> {

    @Override
    public IStructureConfiguration getConfiguration() {
        return new StructureConfiguration();
    }
}
