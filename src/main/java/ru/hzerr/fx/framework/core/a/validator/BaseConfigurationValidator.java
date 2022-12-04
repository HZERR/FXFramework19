package ru.hzerr.fx.framework.core.a.validator;

import ru.hzerr.fx.framework.core.a.configuration.IBaseConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.now.ValidationException;

public class BaseConfigurationValidator implements Validator<IBaseConfiguration> {

    @Override
    public IBaseConfiguration validate(IBaseConfiguration instance) throws ValidationException {
        throwsIfThrown(instance::getApplicationLocale, "locale cannot be null");
        throwsIfThrown(instance::getApplicationVersion, "version cannot be null");
        throwsIfThrown(instance::getApplicationName, "name cannot be null");

        return instance;
    }
}
