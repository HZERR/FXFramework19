package ru.hzerr.fx.framework.core.a.configuration.impl;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.core.a.Version;
import ru.hzerr.fx.framework.core.a.configuration.IBaseConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.DefaultPropertyNotInitializationException;

import java.util.Locale;
import java.util.Properties;

public class InternalFileBasicConfiguration implements IBaseConfiguration {

    private final Properties properties;

    private static final String VERSION_KEY = "application.version";
    private static final String LOCALE_KEY = "application.locale";
    private static final String NAME_KEY = "application.name";

    InternalFileBasicConfiguration(Properties properties) {
        this.properties = properties;
    }

    @Override
    public @NonNull Version getApplicationVersion() {
        if (properties.containsKey(VERSION_KEY)) {
            return Version.of(properties.getProperty(VERSION_KEY));
        }

        throw new DefaultPropertyNotInitializationException("The default parameter \"%s\" has not been initialized".formatted(VERSION_KEY));
    }

    @Override
    public @NonNull Locale getApplicationLocale() {
        if (properties.containsKey(LOCALE_KEY)) {
            return Locale.of(properties.getProperty(LOCALE_KEY));
        }

        throw new DefaultPropertyNotInitializationException("The default parameter \"%s\" has not been initialized".formatted(LOCALE_KEY));
    }

    @Override
    public @NonNull String getApplicationName() throws DefaultPropertyNotInitializationException {
        if (properties.containsKey(NAME_KEY)) {
            return properties.getProperty(NAME_KEY);
        }

        throw new DefaultPropertyNotInitializationException("The default parameter \"%s\" has not been initialized".formatted(NAME_KEY));
    }
}
