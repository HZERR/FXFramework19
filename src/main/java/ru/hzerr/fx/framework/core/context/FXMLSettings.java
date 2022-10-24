package ru.hzerr.fx.framework.core.context;

import org.reflections.Reflections;
import ru.hzerr.fx.framework.core.controller.AbstractController;
import ru.hzerr.fx.framework.core.controller.Controller;
import ru.hzerr.fx.framework.core.controller.Route;
import ru.hzerr.fx.framework.core.fxml.resolver.AbstractFXMLResolver;
import ru.hzerr.fx.framework.core.fxml.resolver.FXMLResolver;
import ru.hzerr.fx.framework.logging.LogManager;

public class FXMLSettings implements Settings {

    private ClassLoader fxmlClassLoader = ClassLoader.getSystemClassLoader();
    private String controllerPackage;
    private AbstractFXMLResolver resolver = new FXMLResolver();

    public FXMLSettings() {
    }

    /**
     * Установите <b>ClassLoader</b>, который будет загружать FXML файлы.
     * По-умолчанию установлен системный classloader.
     * @param fxmlClassLoader classloader, который будет загружать FXML файлы
     */
    public void setFXMLClassLoader(ClassLoader fxmlClassLoader) {
        this.fxmlClassLoader = fxmlClassLoader;
    }

    /**
     * Получите <b>ClassLoader</b>, который будет загружать FXML файлы.
     * Если classloader раннее не был установлен, будет возвращен системный classloader.
     * Все контроллеры должны быть аннотированы аннотациями {@link ru.hzerr.fx.framework.core.controller.Route} и {@link ru.hzerr.fx.framework.core.controller.Controller}
     * @return fxmlClassLoader classloader, который будет загружать FXML файлы
     */
    public ClassLoader getFXMLClassLoader() {
        return fxmlClassLoader;
    }

    /**
     * Устанавливает FXML Resolver.
     * @param resolver {@link FXMLResolver} | {@link ru.hzerr.fx.framework.core.fxml.resolver.SyncFXMLResolver} | CustomResolver
     */
    public void setResolver(AbstractFXMLResolver resolver) {
        this.resolver = resolver;
    }

    public AbstractFXMLResolver getResolver() {
        return resolver;
    }

    /**
     * Установите базовый пакет, который содержит все ваши контроллеры.
     * Когда вы загружаете FXML, фреймворк будет искать контроллеры в этом пакете.
     * @param controllerPackage базовый пакет контроллеров
     */
    public void setBaseControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
        lookupControllers(controllerPackage);
    }

    /**
     * Получите базовый пакет, который содержит все ваши контроллеры.
     * Когда вы загружаете FXML, фреймворк будет искать контроллеры в этом пакете.
     * @return базовый пакет контроллеров или null, если пакет не установлен
     */
    public String getBaseControllerPackage() {
        return controllerPackage;
    }

    private void lookupControllers(String controllerPackage) {
        Reflections reflections = new Reflections(controllerPackage);
        reflections.getTypesAnnotatedWith(Controller.class).forEach(clazz -> {
            if (clazz.isAnnotationPresent(Route.class)) {
                //noinspection unchecked
                resolver.add((Class<? extends AbstractController>) clazz);
            }
        });
    }
}
