package ru.hzerr.fx.framework.core.context.config.now;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.util.ClassTools;

public class BaseResourceLoader implements ResourceLoader {

    private ClassLoader resourceClassLoader;

    public BaseResourceLoader() {}
    public BaseResourceLoader(ClassLoader resourceClassLoader) {
        this.resourceClassLoader = resourceClassLoader;
    }

    public void setClassLoader(ClassLoader resourceClassLoader) {
        this.resourceClassLoader = resourceClassLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        return resourceClassLoader != null ?
                        resourceClassLoader :
                        ClassTools.getDefaultClassLoader();
    }

    @Override
    public Resource getResource(@NonNull String location) {
        return new ClassPathResource(location, getClassLoader());
    }
}
