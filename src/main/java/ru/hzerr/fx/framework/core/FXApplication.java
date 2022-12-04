package ru.hzerr.fx.framework.core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import ru.hzerr.fx.framework.core.a.configuration.*;
import ru.hzerr.fx.framework.core.a.finder.BaseConfigurationFinder;
import ru.hzerr.fx.framework.core.a.provider.AnnotationConfigApplicationContextProvider;
import ru.hzerr.fx.framework.core.a.provider.IApplicationContextProvider;
import ru.hzerr.fx.framework.core.a.initializer.PropertiesConfigurationInitializer;
import ru.hzerr.fx.framework.core.a.finder.StructureConfigurationFinder;
import ru.hzerr.fx.framework.core.a.resource.AbstractControllerManager;
import ru.hzerr.fx.framework.core.annotation.Inject;
import ru.hzerr.fx.framework.core.annotation.Tag;
import ru.hzerr.fx.framework.logging.factory.FXFrameworkLogProvider;
import ru.hzerr.fx.framework.logging.factory.ILogProvider;

/**
 * @author <a href="mailto:vadimvyazloy@yandex.ru">Vadim Devarov</a>
 * Отформатировать код, добавить все необходимые проверки в виде @NonNull и тп
 * Распараллелить
 */
public abstract class FXApplication extends Application {

    protected AnnotationConfigApplicationContext context;

    private IStructureConfiguration structureConfiguration;
    private IBaseConfiguration baseConfiguration;
    private AbstractControllerManager resourceManager;

    @Inject
    @Tag("frameworkLogProvider")
    private ILogProvider frameworkLogProvider;

    @Override
    public final void init() throws Exception {
        System.setProperty("logging.level.root", "OFF"); // step 1
        System.setProperty("CONSOLE_LOG_PATTERN", "OFF"); // step 2
        context = applicationContextProvider(configuration().rootPackages()).getApplicationContext();
        structureConfiguration = context.getBean(StructureConfigurationFinder.class).fetch();
        context.registerBean(PropertiesConfiguration.class, () -> new PropertiesConfigurationInitializer(structureConfiguration.getConfigurationFile()).getConfiguration());
        baseConfiguration = context.getBean(BaseConfigurationFinder.class).fetch();
        context.registerBean(IBaseConfiguration.class, () -> new ApplicationConfiguration(baseConfiguration));
        frameworkLogProvider = context.getBean(FXFrameworkLogProvider.class);
        frameworkLogProvider.configure();
        frameworkLogProvider.getLogger().debug("FxFramework was successfully initialized");
        onInit(FxApplicationContext.getBaseFXConfiguration());
    }

    @Override
    public final void start(Stage stage) throws Exception {
        FxApplicationContext.getBaseFXConfiguration().setStage(stage);
        onStart(stage);
    }

    @Override
    public final void stop() throws Exception {
        onExit();
    }

    protected IApplicationContextProvider<AnnotationConfigApplicationContext> applicationContextProvider(String[] basePackages) {
        return new AnnotationConfigApplicationContextProvider(basePackages, true);
    }
    protected abstract IScanConfiguration configuration() throws Exception;
    protected abstract void onInit(FXConfiguration configuration) throws Exception;
    protected abstract void onStart(Stage stage) throws Exception;
    protected abstract void onExit() throws Exception;

}
