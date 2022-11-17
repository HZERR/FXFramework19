package ru.hzerr.fx.framework.core.context.config.now;

public interface ResourceLoader {

    ClassLoader getClassLoader();

    Resource getResource(String path);
}
