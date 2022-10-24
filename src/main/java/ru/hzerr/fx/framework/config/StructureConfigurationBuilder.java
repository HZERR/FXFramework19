package ru.hzerr.fx.framework.config;

import ru.hzerr.fx.framework.exception.StructureCreateImpossibleException;

import java.io.IOException;

public class StructureConfigurationBuilder implements ConfigurationBuilder<StructureConfiguration> {

    private static final Configuration structureConfiguration = new StructureConfiguration();

    @Override
    public StructureConfiguration getConfiguration() {
        try {
            ((StructureConfiguration) structureConfiguration).getRootDirectory().create();
            ((StructureConfiguration) structureConfiguration).getSerializableDirectory().create();
            ((StructureConfiguration) structureConfiguration).getConfigDirectory().create();
            ((StructureConfiguration) structureConfiguration).getConfigFile().create();
            ((StructureConfiguration) structureConfiguration).getAssetsDirectory().create();
            ((StructureConfiguration) structureConfiguration).getBackgroundDirectory().create();
            ((StructureConfiguration) structureConfiguration).getLogDirectory().create();
        } catch (IOException io) { throw new StructureCreateImpossibleException(io); }
        return (StructureConfiguration) structureConfiguration;
    }
}
