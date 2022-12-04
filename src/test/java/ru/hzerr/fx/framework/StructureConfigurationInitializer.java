package ru.hzerr.fx.framework;

import ru.hzerr.fx.framework.core.annotation.Initializer;
import ru.hzerr.fx.framework.core.a.initializer.ConfigurationInitializer;
import ru.hzerr.fx.framework.core.a.configuration.IStructureConfiguration;

@Initializer(StructureConfiguration.class)
public class StructureConfigurationInitializer implements ConfigurationInitializer<IStructureConfiguration> {

    @Override
    public IStructureConfiguration getConfiguration() {
        return new StructureConfiguration();
    }
}
