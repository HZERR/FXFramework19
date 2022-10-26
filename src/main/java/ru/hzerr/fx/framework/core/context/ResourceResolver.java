package ru.hzerr.fx.framework.core.context;

import com.google.common.base.Preconditions;
import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.AbstractController;
import ru.hzerr.fx.framework.core.controller.Controller;
import ru.hzerr.fx.framework.core.controller.Route;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.ResolveException;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class ResourceResolver implements IResolver<String, URL> {

    protected static final HList<Class<? extends AbstractController>> controllers = new SynchronizedHList<>();

    @Override
    public URL resolveResource(String key) {
        return null;
    }

    // TODO: 10/25/22 ADDING @FXController handling
    public FXML resolveFXML(String key) throws ResolveException {
        Preconditions.checkNotNull(key, "Name cannot be null");
        Preconditions.checkState(!key.isBlank(), "Name cannot be blank");
        for (Class<? extends AbstractController> clazz : controllers) {
            if (clazz.getSuperclass().isAssignableFrom(AbstractController.class)) {
                if (clazz.isAnnotationPresent(Controller.class)) {
                    final String identityName = clazz.getAnnotation(Controller.class).value();
                    Preconditions.checkState(!identityName.isBlank(),
                            "The name of the class \"%s\" controller cannot be empty", clazz.getName());
                    if (identityName.equals(key)) {
                        if (clazz.isAnnotationPresent(Route.class)) {
                            final String location = clazz.getAnnotation(Route.class).value();
                            Preconditions.checkState(!location.isBlank(),
                                    "The location of the fxml file of the \"%s\" class controller cannot be empty", clazz.getName());
                            try {
                                return new FXML(location, clazz.getConstructor().newInstance());
                            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                                     InvocationTargetException e) {
                                throw new ResolveException("Can't create instance of class " + clazz.getName());
                            }
                        } else
                            throw new ResolveException("The class " + clazz.getName() + " isn't annotated with " + Route.class.getName());
                    }
                } else
                    throw new ResolveException("The class " + clazz.getName() + " isn't annotated with " + Controller.class.getName());
            } else
                throw new ResolveException("The class " + clazz.getName() + " isn't extended from AbstractController");
        }

        throw new ResolveException("No fxml file was found for name " + name);
    }
}
