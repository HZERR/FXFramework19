package ru.hzerr.fx.framework.core.context.resource;

import org.springframework.context.ApplicationContext;
import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.BaseController;

import java.util.Optional;

public abstract class AbstractControllerManager implements IRegistration {

    protected ApplicationContext context;

    public abstract Optional<IManagedStep> fetchByID(String id);
}
