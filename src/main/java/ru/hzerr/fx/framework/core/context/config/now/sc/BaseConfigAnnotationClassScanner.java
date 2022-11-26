package ru.hzerr.fx.framework.core.context.config.now.sc;

import org.reflections.Reflections;
import ru.hzerr.fx.framework.core.context.config.now.annotation.BaseConfiguration;
import ru.hzerr.fx.framework.core.context.config.now.annotation.StructureConfiguration;
import ru.hzerr.fx.framework.core.controller.annotation.Disabled;
import ru.hzerr.fx.framework.core.controller.annotation.FXController;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseConfigAnnotationClassScanner implements ConfigAnnotationClassScanner {

    private Class<?> defaultConfiguration;
    private Class<?> structureConfiguration;

    private Set<Class<?>> controllers = new LinkedHashSet<>();
    // etc...

    @Override
    public void scan(String... basePackages) {
        for (String pkg: basePackages) {
            defaultConfiguration = scanOne(pkg, BaseConfiguration.class);
            structureConfiguration = scanOne(pkg, StructureConfiguration.class);
            controllers.addAll(scanAll(pkg, FXController.class));
        }
    }

    private Set<Class<?>> scanAll(String pkg, Class<? extends Annotation> annotation) {
        Set<Class<?>> classes = new Reflections(pkg).getTypesAnnotatedWith(annotation);
        classes.removeIf(aClass -> aClass.isAnnotationPresent(Disabled.class));
        return classes;
    }
    
    private Class<?> scanOne(String pkg, Class<? extends Annotation> annotation) {
        Set<Class<?>> classes = scanAll(pkg, annotation);
        if (classes.size() > 1) {
            throw new IllegalStateException("More than one active implementations with the annotation \"" + annotation.getName() + "\" were found in the project. Use the annotation \"" + Disabled.class.getName() + " to make the scanners skip one class");
        }

        return classes.iterator().next();
    }

    @Override
    public Class<?>[] getAnnotated(Class<?> configAnnotationClass) {
        if (configAnnotationClass.equals(BaseConfiguration.class)) {
            return new Class[]{defaultConfiguration};
        }

        if (configAnnotationClass.equals(StructureConfiguration.class)) {
            return new Class[]{structureConfiguration};
        }

        throw new IllegalArgumentException("The " + configAnnotationClass.getName() + " annotation isn't a configuration annotation");
    }
}
