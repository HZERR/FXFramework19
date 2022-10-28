package ru.hzerr.fx.framework;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.config.test.AbstractFXSettings;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;
import ru.hzerr.fx.framework.core.loader.FXMLLoader;

public class TestClass extends FXApplication {

    @Override
    protected void onInit(AbstractFXSettings settings) {
        settings.setResourceClassLoaderOfBasicConfiguration(ClassLoader.getSystemClassLoader());
        settings.setApplicationName("FXFramework");
        FxApplicationContext.getResourceManager().registerFromPackage("ru.hzerr.fx.framework.core.controller");
    }

    @Override
    protected void onStart(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.loadParent("main"), 200, 250);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    protected void onExit() throws Exception {

    }
}
