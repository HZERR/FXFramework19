package ru.hzerr.fx.framework.core.context.resource;

import ru.hzerr.fx.framework.core.controller.Controller;

public interface IRegistration {

    void registerFromPackage(String pkg);
    void register(Class<? extends Controller> clazz);
    void unregister(Class<? extends Controller> clazz);
}
