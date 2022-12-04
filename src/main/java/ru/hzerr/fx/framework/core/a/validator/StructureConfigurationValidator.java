package ru.hzerr.fx.framework.core.a.validator;

import ru.hzerr.fx.framework.core.a.configuration.IStructureConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.now.ValidationException;

public class StructureConfigurationValidator implements Validator<IStructureConfiguration> {

    @Override
    public IStructureConfiguration validate(IStructureConfiguration instance) throws ValidationException {
        throwsIf(instance.getRootDirectory().notExists(), "rootDirectory does not exist");
        throwsIf(instance.getConfigurationDirectory().notExists(), "configurationDirectory does not exist");
        throwsIf(instance.getAssetsDirectory().notExists(), "assetsDirectory does not exist");
        throwsIf(instance.getConfigurationFile().notExists(), "configurationFile does not exist");
        throwsIf(instance.getLogDirectory().notExists(), "logDirectory does not exist");
        throwsIf(instance.getSerializableDirectory().notExists(), "serializableDirectory does not exist");

        return instance;
    }
}
