package ru.hzerr.fx.framework.core.context.resource;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import ru.hzerr.fx.framework.core.controller.BaseController;
import ru.hzerr.fx.framework.core.controller.ILanguage;
import ru.hzerr.fx.framework.core.controller.Language;
import ru.hzerr.fx.framework.core.controller.annotation.*;
import ru.hzerr.fx.framework.core.controller.theme.AbstractTheme;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.unchecked.FetchingException;
import ru.hzerr.fx.framework.exception.unchecked.LoadingControllerException;
import ru.hzerr.fx.framework.logging.LogManager;
import ru.hzerr.fx.framework.predicate.PredicateUtil;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResourceManager extends AbstractResourceManager {

    @Override
    public Optional<IManagedStep> fetchByID(String id) {
        Optional<Class<? extends BaseController>> controllerClass = controllers.find(clazz -> {
            if (clazz.isAnnotationPresent(Controller.class)) return clazz.getAnnotation(Controller.class).value().equals(id);
            if (clazz.isAnnotationPresent(FXController.class)) return clazz.getAnnotation(FXController.class).id().equals(id);

            return false;
        });

        return controllerClass.map(controllerClass1 -> new ManagedStep(controllerClass1, resourceClassLoader));
    }

    @Override
    public void registerFromPackage(String pkg) {
        new Reflections(pkg).getSubTypesOf(BaseController.class).forEach(clazz -> {
            if (PredicateUtil.isAnnotationPresent(Disabled.class).negate().test(clazz)) {
                register(clazz);
            }
        });
    }

    @Override
    public void register(Class<? extends BaseController> controllerClass) {
        if (Predicates.subtypeOf(BaseController.class).negate().test(controllerClass)) {
            throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't extended from " + BaseController.class.getName());
        }

        if (PredicateUtil.isAnnotationPresent(Disabled.class).test(controllerClass)) {
            throw new LoadingControllerException("The " + controllerClass.getName() + " class with the annotation \"" + Disabled.class.getName() + "\" cannot be registered by the FXFramework");
        }

        if (PredicateUtil.isAnnotationPresent(FXController.class).negate().test(controllerClass)) {
            if (PredicateUtil.isAnnotationPresent(Controller.class).test(controllerClass)) {
                final String identityName = controllerClass.getAnnotation(Controller.class).value();
                Preconditions.checkArgument(!identityName.isBlank(),
                        "The name of the class \"%s\" controller cannot be empty", controllerClass.getName());
            } else
                throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't annotated with " + ru.hzerr.fx.framework.core.controller.annotation.Controller.class.getName());

            if (PredicateUtil.isAnnotationPresent(Route.class).test(controllerClass)) {
                final String location = controllerClass.getAnnotation(Route.class).value();
                Preconditions.checkArgument(!location.isBlank(),
                        "The location of the fxml file of the \"%s\" class controller cannot be empty", controllerClass.getName());
            } else
                throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't annotated with " + Route.class.getName());

            if (PredicateUtil.isAnnotationPresent(Theme.class).test(controllerClass)) {
                final String prefix = controllerClass.getAnnotation(Theme.class).prefix();
                final String postfix = controllerClass.getAnnotation(Theme.class).postfix();
                Preconditions.checkArgument(!prefix.isBlank(),
                        "The path prefix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
                Preconditions.checkArgument(!postfix.isBlank(),
                        "The path postfix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
            } else
                throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't annotated with " + Theme.class.getName());

            if (PredicateUtil.isAnnotationPresent(Internationalization.class).test(controllerClass)) {
                final String basePath = controllerClass.getAnnotation(Internationalization.class).location();
                Preconditions.checkArgument(!basePath.isBlank(),
                        "The path prefix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
                Preconditions.checkArgument(StringUtils.containsNone(basePath, '\\', '/'),
                        "Please use the delimiter . instead of \\ or / when setting the internationalization parameters in the \"%s\" class", controllerClass.getName());
            }
        } else {
            final String id = controllerClass.getAnnotation(FXController.class).id();
            final String location = controllerClass.getAnnotation(FXController.class).route();
            final String prefix = controllerClass.getAnnotation(FXController.class).themePrefix();
            final String postfix = controllerClass.getAnnotation(FXController.class).themePostfix();
            Preconditions.checkArgument(!id.isBlank(),
                    "The name of the class \"%s\" controller cannot be empty", controllerClass.getName());
            Preconditions.checkArgument(!location.isBlank(),
                    "The location of the fxml file of the \"%s\" class controller cannot be empty", controllerClass.getName());
            Preconditions.checkArgument(!prefix.isBlank(),
                    "The path prefix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
            Preconditions.checkArgument(!postfix.isBlank(),
                    "The path postfix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
        }

        controllers.add(controllerClass);
    }

    @Override
    public void unregister(Class<? extends BaseController> controllerClass) {
        controllers.remove(controllerClass);
    }

    public static class ManagedStep implements IManagedStep {

        private final Class<? extends BaseController> controllerClass;
        private final ClassLoader resourceClassLoader;

        public ManagedStep(Class<? extends BaseController> controllerClass, ClassLoader resourceClassLoader) {
            this.controllerClass = controllerClass;
            this.resourceClassLoader = resourceClassLoader;
        }

        public FXML fetchFXML() {
            String location;

            if (controllerClass.isAnnotationPresent(FXController.class)) {
                location = controllerClass.getAnnotation(FXController.class).route();
            } else
                location = controllerClass.getAnnotation(Route.class).value();

            try {
                return new FXML(location, controllerClass.getConstructor().newInstance());
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new FetchingException("Can't create instance of class " + controllerClass.getName(), e);
            }
        }

        @Override
        public ILanguage fetchLanguage(Locale locale) {
            String location;

            if (controllerClass.isAnnotationPresent(FXController.class)) {
                location = controllerClass.getAnnotation(FXController.class).localeBasePath();
            } else
                location = controllerClass.getAnnotation(Internationalization.class).location();

            try {
                return new Language(ResourceBundle.getBundle(location, locale, resourceClassLoader));
            } catch (MissingResourceException | NullPointerException e) {
                throw new FetchingException("The language pack on the " + location + '_' + locale.getLanguage() + " path is not found", e);
            }
        }

        public AbstractTheme fetchTheme(String themeName) {
            String themePrefix;
            String themePostfix;

            if (controllerClass.isAnnotationPresent(FXController.class)) {
                themePrefix = controllerClass.getAnnotation(FXController.class).themePrefix();
                themePostfix = controllerClass.getAnnotation(FXController.class).themePostfix();
            } else {
                themePrefix = controllerClass.getAnnotation(Theme.class).prefix();
                themePostfix = controllerClass.getAnnotation(Theme.class).postfix();
            }

            LogManager.getLogger().debug("Fetching css " + themePrefix + themeName + themePostfix);
            URL url = resourceClassLoader.getResource(themePrefix + themeName + themePostfix);

            if (url != null) {
                return new ru.hzerr.fx.framework.core.controller.theme.Theme(url.toExternalForm());
            }

            throw new FetchingException("The " + themeName + " theme on the " + themePrefix + themeName + themePostfix + " path was not found", new NullPointerException("Resource url can't be null"));
        }
    }
}
