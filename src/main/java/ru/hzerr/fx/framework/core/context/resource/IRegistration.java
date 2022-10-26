package ru.hzerr.fx.framework.core.context.resource;

import ru.hzerr.fx.framework.core.controller.AbstractController;

public interface IRegistration {

    void registerFromPackage(String pkg);
    void register(Class<? extends AbstractController> clazz);
    void unregister(Class<? extends AbstractController> clazz);
}
