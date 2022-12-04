package ru.hzerr.fx.framework.core.a.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import ru.hzerr.fx.framework.core.a.initializer.ConfigurationInitializer;
import ru.hzerr.fx.framework.core.annotation.Initializer;
import ru.hzerr.fx.framework.core.annotation.Structure;
import ru.hzerr.fx.framework.core.a.configuration.IStructureConfiguration;
import ru.hzerr.fx.framework.core.a.validator.StructureConfigurationValidator;
import ru.hzerr.fx.framework.exception.LoadingConfigurationException;

@Component
public class StructureConfigurationFinder implements IConfigurationFinder<IStructureConfiguration> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    private ApplicationContext context;

    @Override
    public IStructureConfiguration fetch() throws LoadingConfigurationException {
        try {
            Object configuration = ((ConfigurationInitializer<?>) context.getBean(context.getBeanNamesForType(
                    ResolvableType.forClassWithGenerics(ConfigurationInitializer.class, IStructureConfiguration.class)
            )[0])).getConfiguration();

            return new StructureConfigurationValidator().validate((IStructureConfiguration) configuration);
        } catch (Throwable ignored) {
            // fallthrough
        }

        try {
            IStructureConfiguration configuration = context.getBean(IStructureConfiguration.class);

            return new StructureConfigurationValidator().validate(configuration);
        } catch (Throwable ignored) {
            // fallthrough
        }


        throw new LoadingConfigurationException(IStructureConfiguration.class.getSimpleName() +
                " is not found. Please create an heir of this interface or an heir of " + ConfigurationInitializer.class.getSimpleName() +
                " interface. Also remember to set @" + Structure.class.getSimpleName() +
                " or @" + Initializer.class.getSimpleName() + "(" + IStructureConfiguration.class.getSimpleName() + ".class) annotations respectively");
    }
}
