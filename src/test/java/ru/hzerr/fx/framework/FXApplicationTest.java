package ru.hzerr.fx.framework;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import ru.hzerr.fx.framework.core.FXApplication;
import ru.hzerr.fx.framework.core.FxApplicationContext;
import ru.hzerr.fx.framework.core.context.config.DefaultConfiguration;
import ru.hzerr.fx.framework.core.context.config.FXConfiguration;
import ru.hzerr.fx.framework.core.context.config.basic.InternalFileBasicConfigurationInitializer;
import ru.hzerr.fx.framework.core.loader.FXMLLoader;

public class FXApplicationTest extends FXApplication {

    public FXApplicationTest() {
        super();
    }

    @Override
    protected DefaultConfiguration preInit() throws Exception {
        return new InternalFileBasicConfigurationInitializer("fx.properties").getConfiguration();
    }

    @Override
    protected void onInit(FXConfiguration configuration) {
        configuration.setApplicationName("FXFramework Test");
        FxApplicationContext.getResourceManager().setResourceClassLoader(ClassLoader.getSystemClassLoader());
        FxApplicationContext.getResourceManager().registerFromPackage("ru.hzerr.fx.framework");
    }

    @Override
    protected Scene onStart(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.loadParent("main"), 200, 250);
        stage.setScene(scene);
        return scene;
    }

    @Override
    protected void onExit() throws Exception {

    }

    @Test
    void testStartup() {
        launch();
    }
}
