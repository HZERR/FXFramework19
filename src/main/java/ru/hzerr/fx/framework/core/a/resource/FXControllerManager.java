package ru.hzerr.fx.framework.core.a.resource;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import ru.hzerr.fx.framework.core.controller.BaseController;
import ru.hzerr.fx.framework.core.controller.annotation.Disabled;
import ru.hzerr.fx.framework.core.controller.annotation.FXController;
import ru.hzerr.fx.framework.exception.unchecked.LoadingControllerException;
import ru.hzerr.fx.framework.predicate.PredicateUtil;

import java.util.Optional;

public class FXControllerManager extends AbstractControllerManager {

    public FXControllerManager(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Optional<IManagedStep> fetchByID(String id) {
//        Optional<Class<? extends BaseController>> controllerClass = controllers.find(clazz -> {
//            return clazz.isAnnotationPresent(FXController.class) && clazz.getAnnotation(FXController.class).id().equals(id);
//        });
//
//        return controllerClass.map(controllerClass1 -> new ManagedStep(controllerClass1, resourceClassLoader, context));
        return Optional.empty();
    }

    @Override
    public void registerFromPackage(String pkg) {
        new Reflections(pkg).getSubTypesOf(BaseController.class).forEach(clazz -> {
            if (PredicateUtil.isAnnotationPresent(Disabled.class).negate().test(clazz)) {
                if (clazz.isAnnotationPresent(FXController.class)) {
                    register(clazz);
                }
            }
        });
    }

    @Override
    public void register(Class<? extends BaseController> controllerClass) {
        //noinspection UnstableApiUsage
        if (Predicates.subtypeOf(BaseController.class).negate().test(controllerClass)) {
            throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't extended from " + BaseController.class.getName());
        }

        if (PredicateUtil.isAnnotationPresent(Disabled.class).test(controllerClass)) {
            throw new LoadingControllerException("The " + controllerClass.getName() + " class with the annotation \"" + Disabled.class.getName() + "\" cannot be registered by the FXFramework");
        }

        if (PredicateUtil.isAnnotationPresent(FXController.class).negate().test(controllerClass)) {
            throw new LoadingControllerException("The " + controllerClass.getName() + " class should be annotated with the class " + FXController.class.getName());
        }

        final String id = controllerClass.getAnnotation(FXController.class).id();
        final String fxmlRoute = controllerClass.getAnnotation(FXController.class).fxmlRoute();
//        final String themeRoute = controllerClass.getAnnotation(FXController.class).themeRoute();
//        final String localeRoute = controllerClass.getAnnotation(FXController.class).localeRoute();

        Preconditions.checkArgument(!id.isBlank(),
                "The name of the class \"%s\" controller cannot be empty", controllerClass.getName());
        Preconditions.checkArgument(!fxmlRoute.isBlank(),
                "The location of the fxml file of the \"%s\" class controller cannot be empty", controllerClass.getName());
//        Preconditions.checkArgument(!themeRoute.isBlank(),
//                "The path prefix set in class \"%s\" class controller cannot be empty", controllerClass.getName());
//        Preconditions.checkArgument(!localeRoute.isBlank(),
//                "The path postfix set in class \"%s\" class controller cannot be empty", controllerClass.getName());

//        controllers.add(controllerClass);
    }

    @Override
    public void unregister(Class<? extends BaseController> controllerClass) {
//        controllers.remove(controllerClass);
    }

//    public static class ManagedStep implements IManagedStep {
//
//        private final Class<? extends BaseController> controllerClass;
//        private final ClassLoader resourceClassLoader;
//        private final ApplicationContext context;
//
//        public ManagedStep(Class<? extends BaseController> controllerClass, ClassLoader resourceClassLoader, ApplicationContext context) {
//            this.controllerClass = controllerClass;
//            this.resourceClassLoader = resourceClassLoader;
//            this.context = context;
//        }
//
//        public FXML fetchFXML() {
//            String location = controllerClass.getAnnotation(FXController.class).fxmlRoute();
//
//            if (location.isBlank()) throw new FetchingException("The path to the fxml file cannot be blank");
//
//            try {
//                return new FXML(context.getScanConfiguration().fxmlPackage() + location, controllerClass.getConstructor().newInstance());
//            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                throw new FetchingException("Can't create instance of class " + controllerClass.getName(), e);
//            }
//        }
//
//        @Override
//        public ILanguage fetchLanguage(Locale locale) {
//            String location = controllerClass.getAnnotation(FXController.class).localeRoute();
//
//            if (location.isBlank()) throw new FetchingException("The path to the language pack cannot be blank");
//
//            try {
//                return new Language(ResourceBundle.getBundle(context.getScanConfiguration().internationalizationPackage() + location, locale, resourceClassLoader));
//            } catch (MissingResourceException | NullPointerException e) {
//                throw new FetchingException("The language pack on the " + location + '_' + locale.getLanguage() + " path is not found", e);
//            }
//        }
//
//        public AbstractTheme fetchTheme(String themeName) {
//            if (controllerClass.isAnnotationPresent(FXController.class)) {
//                String themeRoute = controllerClass.getAnnotation(FXController.class).themeRoute();
//
//                LogManager.getLogger().debug("Fetching css " + context.getScanConfiguration().themePackage() + themeName + themeRoute);
//                URL url = resourceClassLoader.getResource(context.getScanConfiguration().themePackage() + themeName + themeRoute);
//
//                if (url != null) {
//                    return new ru.hzerr.fx.framework.core.controller.theme.Theme(url.toExternalForm());
//                }
//
//                throw new FetchingException("The " + themeName + " theme on the " + context.getScanConfiguration().themePackage() + themeName + themeRoute + " path was not found", new NullPointerException("Resource url can't be null"));
//            }
//
//            throw new FetchingException("The " + controllerClass.getName() + " class should be annotated with the class " + FXController.class.getName());
//        }
//    }
}
