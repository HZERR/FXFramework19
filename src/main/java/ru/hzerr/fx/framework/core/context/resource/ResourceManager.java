package ru.hzerr.fx.framework.core.context.resource;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import org.reflections.Reflections;
import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.Controller;
import ru.hzerr.fx.framework.core.controller.annotation.*;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.unchecked.LoadingControllerException;
import ru.hzerr.fx.framework.exception.unchecked.FetchingException;
import ru.hzerr.fx.framework.predicate.PredicateUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ResourceManager implements IResourceManager {

    private final HList<Class<? extends Controller>> controllers = new SynchronizedHList<>();

    @Override
    public Optional<IManagedStep> fetchByID(String id) {
        Optional<Class<? extends Controller>> controllerClass = controllers.find(clazz -> {
            if (clazz.isAnnotationPresent(ru.hzerr.fx.framework.core.controller.annotation.Controller.class)) return clazz.getAnnotation(ru.hzerr.fx.framework.core.controller.annotation.Controller.class).value().equals(id);
            if (clazz.isAnnotationPresent(FXController.class)) return clazz.getAnnotation(FXController.class).id().equals(id);

            return false;
        });

        return controllerClass.map(ManagedStep::new);
    }

    @Override
    public void registerFromPackage(String pkg) {
        new Reflections(pkg).getSubTypesOf(Controller.class).forEach(clazz -> {
            if (PredicateUtil.isAnnotationPresent(Disabled.class).negate().test(clazz)) {
                register(clazz);
            }
        });
    }

    @Override
    public void register(Class<? extends Controller> controllerClass) {
        if (Predicates.subtypeOf(Controller.class).negate().test(controllerClass)) {
            throw new LoadingControllerException("The class " + controllerClass.getName() + " isn't extended from " + Controller.class.getName());
        }

        if (PredicateUtil.isAnnotationPresent(Disabled.class).test(controllerClass)) {
            throw new LoadingControllerException("The " + controllerClass.getName() + " class with the annotation \"" + Disabled.class.getName() + "\" cannot be registered by the FXFramework");
        }

        if (PredicateUtil.isAnnotationPresent(FXController.class).negate().test(controllerClass)) {
            if (PredicateUtil.isAnnotationPresent(ru.hzerr.fx.framework.core.controller.annotation.Controller.class).test(controllerClass)) {
                final String identityName = controllerClass.getAnnotation(ru.hzerr.fx.framework.core.controller.annotation.Controller.class).value();
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
    public void unregister(Class<? extends Controller> controllerClass) {
        controllers.remove(controllerClass);
    }

    public static class ManagedStep implements IManagedStep {

        private final Class<? extends Controller> controllerClass;

        public ManagedStep(Class<? extends Controller> controllerClass) {
            this.controllerClass = controllerClass;
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
    }
}
