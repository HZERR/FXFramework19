package ru.hzerr.fx.framework.core.context.config;

import ru.hzerr.file.BaseDirectory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.file.HDirectory;
import ru.hzerr.fx.framework.core.FxApplicationContext;
import ru.hzerr.fx.framework.core.context.config.MemoryConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.PropertyNotInitializationException;
import ru.hzerr.util.SystemInfo;

import java.io.File;

/**
 *   Structure:
 *   root -> modification -> assets -> backgrounds
 *         -              -> projects
 *         -
 *         -> config -> config.properties
 *         -> log
 */
public class StructureConfiguration implements MemoryConfiguration {

    private BaseDirectory rootDirectory;
    private BaseDirectory serializableDirectory;
    private BaseDirectory assetsDirectory;
    private BaseDirectory configDirectory;
    private BaseFile configFile;
    private BaseDirectory logDirectory;
    public StructureConfiguration() {
        final String name = FxApplicationContext.getApplicationConfiguration().g;
        if (name == null) {
            throw new PropertyNotInitializationException("The parameter \"Application Name\" has not been initialized. Please set the parameter by calling FxApplicationContext.getBaseFXConfiguration().setApplicationName(String name)");
        }

        rootDirectory = new HDirectory(SystemInfo.getUserHome().concat(File.separator).concat(name));
        serializableDirectory = rootDirectory.getSubDirectory("data");
        assetsDirectory = rootDirectory.getSubDirectory("assets");
        configDirectory = rootDirectory.getSubDirectory("config");
        configFile = configDirectory.getSubFile("config.properties");
        logDirectory = rootDirectory.getSubDirectory("log");
    }

    public BaseDirectory getRootDirectory() {
        return rootDirectory;
    }
    public BaseDirectory getSerializableDirectory() {
        return serializableDirectory;
    }
    public BaseDirectory getAssetsDirectory() {
        return assetsDirectory;
    }
    public BaseDirectory getConfigDirectory() {
        return configDirectory;
    }
    public BaseFile getConfigFile() {
        return configFile;
    }
    public BaseDirectory getLogDirectory() {
        return logDirectory;
    }

    public void setRootDirectory(BaseDirectory rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
    public void setSerializableDirectory(BaseDirectory serializableDirectory) {
        this.serializableDirectory = serializableDirectory;
    }
    public void setAssetsDirectory(BaseDirectory assetsDirectory) {
        this.assetsDirectory = assetsDirectory;
    }
    public void setConfigDirectory(BaseDirectory configDirectory) {
        this.configDirectory = configDirectory;
    }
    public void setConfigFile(BaseFile configFile) {
        this.configFile = configFile;
    }
    public void setLogDirectory(BaseDirectory logDirectory) {
        this.logDirectory = logDirectory;
    }
}
