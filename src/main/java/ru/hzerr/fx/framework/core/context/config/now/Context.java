package ru.hzerr.fx.framework.core.context.config.now;

import ru.hzerr.fx.framework.core.context.config.now.sc.BaseConfigAnnotationClassScanner;
import ru.hzerr.fx.framework.core.context.config.now.sc.ConfigAnnotationClassScanner;
import ru.hzerr.fx.framework.core.context.resource.AbstractControllerManager;
import ru.hzerr.fx.framework.core.context.resource.FXControllerManager;
import ru.hzerr.fx.framework.exception.unchecked.now.ClassCreationException;

import java.lang.reflect.InvocationTargetException;

public class Context {

    private final ConfigAnnotationClassScanner classScanner = new BaseConfigAnnotationClassScanner();
    private ScanConfiguration scanConfiguration;
    private StructureConfiguration structureConfiguration;
    private BaseConfiguration baseConfiguration;
    private AbstractControllerManager resourceManager;

    public ScanConfiguration getScanConfiguration() {
        return scanConfiguration;
    }

    void initialize(ScanConfiguration configuration) throws ClassCreationException {
        scanConfiguration = configuration;
        classScanner.scan(configuration.rootPackage());
        Class<?> baseClass = classScanner.getAnnotated(ru.hzerr.fx.framework.core.context.config.now.annotation.BaseConfiguration.class)[0];

        if (baseClass != null) {
            try {
                this.baseConfiguration = (BaseConfiguration) baseClass.getConstructor().newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new ClassCreationException(e, "DefaultConfiguration can't be instantiated");
            }
        }

        Class<?> structureClass = classScanner.getAnnotated(StructureConfiguration.class)[0];

        if (structureClass != null) {
            try {
                this.structureConfiguration = (StructureConfiguration) structureClass.getConstructor().newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                throw new ClassCreationException(e, "DefaultConfiguration can't be instantiated");
            }
        }

        resourceManager = new FXControllerManager(this);
    }
}
