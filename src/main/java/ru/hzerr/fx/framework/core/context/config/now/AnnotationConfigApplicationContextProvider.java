package ru.hzerr.fx.framework.core.context.config.now;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class AnnotationConfigApplicationContextProvider implements IApplicationContextProvider<AnnotationConfigApplicationContext> {
    
    private static final String FRAMEWORK_PKG = "ru.hzerr.fx.framework";

    private final String[] basePackages;

    public AnnotationConfigApplicationContextProvider(boolean useFrameworkClassPath, String... basePackages) {
        if (useFrameworkClassPath) {
            basePackages = Arrays.copyOf(basePackages, basePackages.length + 1);
            basePackages[basePackages.length - 1] = FRAMEWORK_PKG;
        }

        this.basePackages = basePackages;
    }

    @Override
    public AnnotationConfigApplicationContext getApplicationContext() {
        return new AnnotationConfigApplicationContext(basePackages);
    }
}
