package ru.hzerr.fx.framework;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.config.ConfigFactory;
import ru.hzerr.fx.framework.config.test.AbstractFXSettings;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;

/**
 * @author <a href="mailto:vadimvyazloy@yandex.ru">Vadim Devarov</a>
 */
public abstract class FXApplication extends Application {

    @Override
    public final void init() throws Exception {
        onInit(FxApplicationContext.getInitializationSettings());
        ConfigFactory.getConfigFactory().initialize();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FxApplicationContext.getInitializationSettings().setStage(stage);
        onStart(stage);
    }

    @Override
    public void stop() throws Exception {
        onExit();
    }

    protected abstract void onInit(AbstractFXSettings settings) throws Exception;
    protected abstract void onStart(Stage stage) throws Exception;
    protected abstract void onExit() throws Exception;

}
