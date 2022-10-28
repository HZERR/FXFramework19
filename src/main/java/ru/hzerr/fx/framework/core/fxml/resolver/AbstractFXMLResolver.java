package ru.hzerr.fx.framework.core.fxml.resolver;

import com.google.common.base.Preconditions;
import ru.hzerr.collections.list.HList;
import ru.hzerr.fx.framework.core.controller.Controller;
import ru.hzerr.fx.framework.core.controller.annotation.Route;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.ResolveException;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractFXMLResolver {

    protected final HList<Class<? extends Controller>> data;

    protected AbstractFXMLResolver(HList<Class<? extends Controller>> data) {
        this.data = data;
    }

    // TODO: 10/25/22 ADDING @FXController handling
    public FXML resolve(String name) throws ResolveException {
        Preconditions.checkNotNull(name, "Name cannot be null");
        Preconditions.checkState(!name.isBlank(), "Name cannot be blank");
        for (Class<? extends Controller> clazz : data) {
            if (clazz.getSuperclass().isAssignableFrom(Controller.class)) {
                if (clazz.isAnnotationPresent(ru.hzerr.fx.framework.core.controller.annotation.Controller.class)) {
                    final String identityName = clazz.getAnnotation(ru.hzerr.fx.framework.core.controller.annotation.Controller.class).value();
                    Preconditions.checkState(!identityName.isBlank(),
                            "The name of the class \"%s\" controller cannot be empty", clazz.getName());
                    if (identityName.equals(name)) {
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
                    throw new ResolveException("The class " + clazz.getName() + " isn't annotated with " + ru.hzerr.fx.framework.core.controller.annotation.Controller.class.getName());
            } else
                throw new ResolveException("The class " + clazz.getName() + " isn't extended from AbstractController");
        }

        throw new ResolveException("No fxml file was found for name " + name);
    }

    public void add(Class<? extends Controller> controllerClass) {
        data.add(controllerClass);
    }
    public void remove(Class<? extends Controller> controllerClass) {
        data.remove(controllerClass);
    }
}
