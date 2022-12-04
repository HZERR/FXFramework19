package ru.hzerr.fx.framework.core.a.finder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import ru.hzerr.fx.framework.core.a.configuration.IBaseConfiguration;
import ru.hzerr.fx.framework.core.a.initializer.ConfigurationInitializer;
import ru.hzerr.fx.framework.core.a.validator.BaseConfigurationValidator;
import ru.hzerr.fx.framework.core.annotation.Initializer;
import ru.hzerr.fx.framework.core.annotation.Structure;
import ru.hzerr.fx.framework.exception.LoadingConfigurationException;

@Component
public class BaseConfigurationFinder implements IConfigurationFinder<IBaseConfiguration> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    private ApplicationContext context;

    @Override
    public IBaseConfiguration fetch() throws LoadingConfigurationException {
        try {
            Object configuration = ((ConfigurationInitializer<?>) context.getBean(context.getBeanNamesForType(
                    ResolvableType.forClassWithGenerics(ConfigurationInitializer.class, IBaseConfiguration.class)
            )[0])).getConfiguration();

            return new BaseConfigurationValidator().validate((IBaseConfiguration) configuration);
        } catch (Throwable ignored) {
            // fallthrough
        }

        try {
            IBaseConfiguration configuration = context.getBean(IBaseConfiguration.class);
            return new BaseConfigurationValidator().validate(configuration);
        } catch (Throwable ignored) {
            // fallthrough
        }


        throw new LoadingConfigurationException(IBaseConfiguration.class.getSimpleName() +
                " is not found. Please create an heir of this interface or an heir of " + ConfigurationInitializer.class.getSimpleName() +
                " interface. Also remember to set @" + Structure.class.getSimpleName() +
                " or @" + Initializer.class.getSimpleName() + "(" + IBaseConfiguration.class.getSimpleName() + ".class) annotations respectively");
    }
}
