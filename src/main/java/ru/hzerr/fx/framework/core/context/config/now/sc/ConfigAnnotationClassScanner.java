package ru.hzerr.fx.framework.core.context.config.now.sc;

public interface ConfigAnnotationClassScanner extends Scanner<Class<?>> {

    Class<?>[] getAnnotated(Class<?> configAnnotationClass);
}
