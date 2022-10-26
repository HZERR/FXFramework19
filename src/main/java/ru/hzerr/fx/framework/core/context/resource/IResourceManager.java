package ru.hzerr.fx.framework.core.context.resource;

import java.util.Optional;

public interface IResourceManager extends IRegistration {

    Optional<IManagedStep> fetchByID(String id);
}
