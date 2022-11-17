package ru.hzerr.fx.framework.core.context.config.now.sc;

import org.reflections.Reflections;
import ru.hzerr.fx.framework.core.context.config.now.annotation.StructureConfiguration;
import ru.hzerr.fx.framework.core.context.config.now.annotation.DefaultConfiguration;
import ru.hzerr.fx.framework.util.ClassTools;

import java.util.Set;

public class BaseConfigAnnotationClassScanner implements ConfigAnnotationClassScanner {

    private Set<Class<?>> defaultConfigurations;
    private Set<StructureConfiguration> structureConfigurations;
    // etc...

    private static final Class<?>[] configAnnotations = {
            DefaultConfiguration.class,
            StructureConfiguration.class
            // etc...
    };

    @Override
    public void scan(String... basePackages) {
        defaultConfigurations = Reflections.collect().getTypesAnnotatedWith(DefaultConfiguration.class);
    }

    @Override
    public Class<?>[] getAnnotated(Class<?> configAnnotationClass) {
        if (ClassTools.equalAny(configAnnotationClass, configAnnotations)) {
            // TODO: 11/17/22 instanceOf all annotation. if true -> return set to array
        }

        throw new IllegalArgumentException("The " + configAnnotationClass.getName() + " annotation isn't a configuration annotation");
    }
}
