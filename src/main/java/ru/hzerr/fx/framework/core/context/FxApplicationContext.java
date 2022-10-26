package ru.hzerr.fx.framework.core.context;

import ru.hzerr.fx.framework.config.Version;
import ru.hzerr.fx.framework.config.test.AbstractFXSettings;
import ru.hzerr.fx.framework.config.test.FXSettings;
import ru.hzerr.fx.framework.core.context.resource.IResourceManager;
import ru.hzerr.fx.framework.core.context.resource.ResourceManager;

public class FxApplicationContext {

    private static final IResourceManager RESOURCE_MANAGER = new ResourceManager();
    private static final Settings FXML_SETTINGS = new FXMLSettings();
    private static final Settings LOGGING_SETTINGS = new LoggingSettings();
    private static final Version FX_FRAMEWORK_VERSION = new Version(1, 0, 0, 1);
    private static final AbstractFXSettings settings = new FXSettings();

    public static FXMLSettings getFXMLSettings() {
        return (FXMLSettings) FXML_SETTINGS;
    }
    public static LoggingSettings getLoggingSettings() {
        return (LoggingSettings) LOGGING_SETTINGS;
    }
    public static AbstractFXSettings getInitializationSettings() {
        return settings;
    }
    public static Version getFrameworkVersion() {
        return FX_FRAMEWORK_VERSION;
    }
    public static IResourceManager getResourceManager() { return RESOURCE_MANAGER; }
}
