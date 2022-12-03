package ru.hzerr.fx.framework.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.springframework.context.annotation.*;
import ru.hzerr.fx.framework.core.context.config.FXConfiguration;
import ru.hzerr.fx.framework.core.context.config.initializer.PropertiesConfigurationInitializer;
import ru.hzerr.fx.framework.core.context.config.initializer.StructureConfigurationFinder;
import ru.hzerr.fx.framework.core.context.config.now.*;
import ru.hzerr.fx.framework.core.context.resource.AbstractControllerManager;

import java.util.Arrays;

/**
 * @author <a href="mailto:vadimvyazloy@yandex.ru">Vadim Devarov</a>
 * Отформатировать код, добавить все необходимые проверки в виде @NonNull и тп
 * Распараллелить
 */
@Configuration
public abstract class FXApplication extends Application {

    private ScanConfiguration scanConfiguration;
    protected AnnotationConfigApplicationContext context;

    private IStructureConfiguration structureConfiguration;
    private BaseConfiguration baseConfiguration;
    private AbstractControllerManager resourceManager;

    @Override
    public final void init() throws Exception {
        scanConfiguration = scanConfiguration();
        context = applicationContextProvider(scanConfiguration.rootPackages()).getApplicationContext();
        structureConfiguration = new StructureConfigurationFinder().fetch();
        context.registerBean(PropertiesConfiguration.class, () -> new PropertiesConfigurationInitializer(structureConfiguration.getConfigurationFile()).getConfiguration());
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

    protected IApplicationContextProvider<AnnotationConfigApplicationContext> applicationContextProvider(String[] basePackages) {
        return new AnnotationConfigApplicationContextProvider(true, basePackages);
    }
    protected abstract ScanConfiguration scanConfiguration() throws Exception;
    protected abstract void onInit(FXConfiguration configuration) throws Exception;
    protected abstract void onStart(Stage stage) throws Exception;
    protected abstract void onExit() throws Exception;

}
