package ru.hzerr.fx.framework.config.test;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.config.Version;

public abstract class AbstractFXSettings {

    private ClassLoader resourceClassLoaderOfBasicConfiguration = ClassLoader.getSystemClassLoader();
    private String applicationName;
    private Version applicationVersion;
    private Stage stage;
    private Scene scene;
    private String[] args;

    public AbstractFXSettings() {
    }

    public void setResourceClassLoaderOfBasicConfiguration(ClassLoader resourceClassLoaderOfBasicConfiguration) {
        this.resourceClassLoaderOfBasicConfiguration = resourceClassLoaderOfBasicConfiguration;
    }
    public ClassLoader getResourceClassLoaderOfBasicConfiguration() {
        return resourceClassLoaderOfBasicConfiguration;
    }
    public void setArgs(String[] args) {
        this.args = args;
    }
    public String[] getArgs() {
        return args;
    }
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    public String getApplicationName() {
        return applicationName;
    }
    public void setApplicationVersion(Version applicationVersion) {
        this.applicationVersion = applicationVersion;
    }
    public Version getApplicationVersion() {
        return applicationVersion;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public Scene getScene() {
        return scene;
    }
}
