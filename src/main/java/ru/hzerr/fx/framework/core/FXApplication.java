package ru.hzerr.fx.framework.core;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.hzerr.fx.framework.core.context.config.FXConfiguration;
import ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration;

/**
 * @author <a href="mailto:vadimvyazloy@yandex.ru">Vadim Devarov</a>
 * Отформатировать код, добавить все необходимые проверки в виде @NonNull и тп
 * Распараллелить
 */
public abstract class FXApplication extends Application {

    private ScanConfiguration scanConfiguration;

    @Override
    public final void init() throws Exception {
        preInit();
        onInit(FxApplicationContext.getBaseFXConfiguration());
    }

    @Override
    public void start(Stage stage) throws Exception {
        FxApplicationContext.getBaseFXConfiguration().setStage(stage);
        onStart(stage);
    }

    @Override
    public void stop() throws Exception {
        onExit();
    }

    protected void setScanConfiguration(ScanConfiguration configuration) {
        this.scanConfiguration = configuration;
    }

    protected abstract void preInit() throws Exception;
    protected abstract void onInit(FXConfiguration configuration) throws Exception;
    protected abstract void onStart(Stage stage) throws Exception;
    protected abstract void onExit() throws Exception;

}
