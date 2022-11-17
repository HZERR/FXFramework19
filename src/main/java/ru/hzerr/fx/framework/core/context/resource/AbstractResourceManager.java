package ru.hzerr.fx.framework.core.context.resource;

import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.BaseController;

import java.util.Optional;

public abstract class AbstractResourceManager implements IRegistration {

    protected final HList<Class<? extends BaseController>> controllers = new SynchronizedHList<>();

    protected ClassLoader resourceClassLoader = ClassLoader.getSystemClassLoader();

    public final ClassLoader getResourceClassLoader() {
        return resourceClassLoader;
    }

    public final void setResourceClassLoader(ClassLoader resourceClassLoader) {
        this.resourceClassLoader = resourceClassLoader;
    }

    public abstract Optional<IManagedStep> fetchByID(String id);
}
