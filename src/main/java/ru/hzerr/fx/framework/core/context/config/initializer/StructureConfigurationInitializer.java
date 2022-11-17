package ru.hzerr.fx.framework.core.context.config.initializer;

import ru.hzerr.fx.framework.core.context.config.MemoryConfiguration;
import ru.hzerr.fx.framework.core.context.config.StructureConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.StructureCreateImpossibleException;

import java.io.IOException;

public class StructureConfigurationInitializer implements ConfigurationInitializer<StructureConfiguration> {

    private static final MemoryConfiguration structureConfiguration = new StructureConfiguration();

    @Override
    public StructureConfiguration getConfiguration() {
        try {
            ((StructureConfiguration) structureConfiguration).getRootDirectory().create();
            ((StructureConfiguration) structureConfiguration).getSerializableDirectory().create();
            ((StructureConfiguration) structureConfiguration).getConfigDirectory().create();
            ((StructureConfiguration) structureConfiguration).getConfigFile().create();
            ((StructureConfiguration) structureConfiguration).getAssetsDirectory().create();
            ((StructureConfiguration) structureConfiguration).getLogDirectory().create();
        } catch (IOException io) { throw new StructureCreateImpossibleException(io); }

        return (StructureConfiguration) structureConfiguration;
    }
}
