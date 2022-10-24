package ru.hzerr.fx.framework.config;

import ru.hzerr.file.BaseDirectory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.file.HDirectory;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;

import java.io.File;

/**
 *   Structure:
 *   root -> modification -> assets -> backgrounds
 *         -              -> projects
 *         -
 *         -> config -> config.properties
 *         -> log
 */
public class StructureConfiguration extends Configuration {

    private final BaseDirectory ROOT_DIR = new HDirectory(System.getProperty("user.home").concat(File.separator).concat(FxApplicationContext.getInitializationSettings().getApplicationName()));
    private final BaseDirectory SERIALIZABLE_DIR = ROOT_DIR.getSubDirectory("data");
    private final BaseDirectory ASSETS_DIR = ROOT_DIR.getSubDirectory("assets");
    private final BaseDirectory BACKGROUNDS_DIR = ASSETS_DIR.getSubDirectory("backgrounds");
    private final BaseDirectory CONFIG_DIR = ROOT_DIR.getSubDirectory("config");
    private final BaseFile CONFIG_FILE = CONFIG_DIR.getSubFile("config.properties");
    private final BaseDirectory LOG_DIR = ROOT_DIR.getSubDirectory("log");

    public StructureConfiguration() {
    }

    public BaseDirectory getRootDirectory() {
        return ROOT_DIR;
    }
    public BaseDirectory getAssetsDirectory() {
        return ASSETS_DIR;
    }
    public BaseDirectory getBackgroundDirectory() {
        return BACKGROUNDS_DIR;
    }
    public BaseDirectory getConfigDirectory() {
        return CONFIG_DIR;
    }
    public BaseFile getConfigFile() {
        return CONFIG_FILE;
    }
    public BaseDirectory getLogDirectory() {
        return LOG_DIR;
    }
    public BaseDirectory getSerializableDirectory() {
        return SERIALIZABLE_DIR;
    }
}
