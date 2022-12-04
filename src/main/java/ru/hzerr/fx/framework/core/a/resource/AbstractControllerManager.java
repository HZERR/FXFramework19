package ru.hzerr.fx.framework.core.a.resource;

import org.springframework.context.ApplicationContext;

import java.util.Optional;

public abstract class AbstractControllerManager implements IRegistration {

    protected ApplicationContext context;

    public abstract Optional<IManagedStep> fetchByID(String id);
}
