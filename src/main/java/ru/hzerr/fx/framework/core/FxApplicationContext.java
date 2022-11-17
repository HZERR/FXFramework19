package ru.hzerr.fx.framework.core;

import org.apache.commons.configuration2.PropertiesConfiguration;
import ru.hzerr.fx.framework.core.context.config.LoggingConfiguration;
import ru.hzerr.fx.framework.core.context.config.FileConfiguration;
import ru.hzerr.fx.framework.core.context.config.MemoryConfiguration;
import ru.hzerr.fx.framework.core.context.config.initializer.PropertiesConfigurationInitializer;
import ru.hzerr.fx.framework.core.context.config.initializer.StructureConfigurationInitializer;
import ru.hzerr.fx.framework.core.context.config.ApplicationConfiguration;
import ru.hzerr.fx.framework.core.context.config.DefaultConfiguration;
import ru.hzerr.fx.framework.core.context.config.FXConfiguration;
import ru.hzerr.fx.framework.core.context.config.StructureConfiguration;
import ru.hzerr.fx.framework.core.context.resource.AbstractResourceManager;
import ru.hzerr.fx.framework.core.context.resource.ResourceManager;

public class FxApplicationContext {

    private FxApplicationContext() {
        throw new IllegalStateException("FxApplicationContext can't be instantiated");
    }

    private static MemoryConfiguration fxConfiguration;
    private static MemoryConfiguration loggingSettings;
    private static MemoryConfiguration structureConfiguration;
    private static FileConfiguration applicationConfiguration;
    private static PropertiesConfiguration fileConfiguration;
    private static AbstractResourceManager resourceManager;

    public static LoggingConfiguration getLoggingSettings() {
        return (LoggingConfiguration) loggingSettings;
    }
    public static AbstractResourceManager getResourceManager() { return resourceManager; }
    public static FXConfiguration getBaseFXConfiguration() {
        return (FXConfiguration) fxConfiguration;
    }
    public static StructureConfiguration getStructureConfiguration() {
        return (StructureConfiguration) structureConfiguration;
    }
    public static ApplicationConfiguration getApplicationConfiguration() {
        return (ApplicationConfiguration) applicationConfiguration;
    }

    static void initialize(DefaultConfiguration defaultConfiguration) {
        fxConfiguration = new FXConfiguration();
        resourceManager = new ResourceManager();
        loggingSettings = new LoggingConfiguration();
        structureConfiguration = new StructureConfigurationInitializer().getConfiguration();
        fileConfiguration = new PropertiesConfigurationInitializer(((StructureConfiguration) structureConfiguration).getConfigFile()).getConfiguration();
        applicationConfiguration = new ApplicationConfiguration(fileConfiguration, defaultConfiguration);
    }
}
