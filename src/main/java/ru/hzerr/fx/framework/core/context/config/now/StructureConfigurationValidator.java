package ru.hzerr.fx.framework.core.context.config.now;

import ru.hzerr.fx.framework.exception.unchecked.now.ValidationException;

public class StructureConfigurationValidator implements Validator<StructureConfiguration> {

    @Override
    public StructureConfiguration validate(StructureConfiguration instance) throws ValidationException {
        throwsIf(instance.getRootDirectory().notExists(), "rootDirectory does not exist");
        throwsIf(instance.getConfigurationDirectory().notExists(), "configurationDirectory does not exist");
        throwsIf(instance.getAssetsDirectory().notExists(), "assetsDirectory does not exist");
        throwsIf(instance.getConfigurationFile().notExists(), "configurationFile does not exist");
        throwsIf(instance.getLogDirectory().notExists(), "logDirectory does not exist");
        throwsIf(instance.getSerializableDirectory().notExists(), "serializableDirectory does not exist");

        return instance;
    }
}
