package ru.hzerr.fx.framework.core.a.configuration;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.core.a.Version;

public interface IFXConfiguration {

    Version getFrameworkVersion();
    Stage getStage();
    Scene getScene();
    String[] getArgs();
}
