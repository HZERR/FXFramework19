package ru.hzerr.fx.framework.core.a.resource;

import ru.hzerr.fx.framework.core.controller.BaseController;

public interface IRegistration {

    void registerFromPackage(String pkg);
    void register(Class<? extends BaseController> clazz);
    void unregister(Class<? extends BaseController> clazz);
}
