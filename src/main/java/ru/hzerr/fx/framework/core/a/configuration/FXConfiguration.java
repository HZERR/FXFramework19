package ru.hzerr.fx.framework.core.a.configuration;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.core.a.Version;

public class FXConfiguration implements IFXConfiguration {

    private final Version frameworkVersion;
    private Stage stage;
    private Scene scene;
    private String[] args;

    public FXConfiguration() {
        frameworkVersion = new Version(1, 0, 0, 1);
    }

    public Version getFrameworkVersion() {
        return frameworkVersion;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
