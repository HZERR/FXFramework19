package ru.hzerr.fx.framework.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import ru.hzerr.fx.framework.config.args.ArgsConfiguration;
import ru.hzerr.fx.framework.config.args.ArgsConfigurationBuilder;
import ru.hzerr.fx.framework.config.basic.BasicConfigurationBuilder;

public class ConfigFactory {

    private Configuration argsConfiguration;
    private Configuration basicConfiguration;
    private Configuration structureConfiguration;
    private Configuration themeConfiguration;
    private PropertiesConfiguration propertiesConfiguration;

    private static final ConfigFactory INSTANCE = new ConfigFactory();

    public ConfigFactory() {
    }

    public void initialize() {
        argsConfiguration = new ArgsConfigurationBuilder().getConfiguration();
        basicConfiguration = new BasicConfigurationBuilder().getConfiguration();
        structureConfiguration = new StructureConfigurationBuilder().getConfiguration();
        propertiesConfiguration = new PropertiesConfigurationBuilder(((StructureConfiguration) structureConfiguration).getConfigFile()).getConfiguration();
    }

    public StructureConfiguration getStructureConfiguration() {
        return (StructureConfiguration) structureConfiguration;
    }
    public ArgsConfiguration getArgsConfiguration() {
        return (ArgsConfiguration) argsConfiguration;
    }

    public static ConfigFactory getConfigFactory() {
        return INSTANCE;
    }
}
