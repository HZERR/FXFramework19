package ru.hzerr.fx.framework.config.basic;

import ru.hzerr.fx.framework.config.Configuration;
import ru.hzerr.fx.framework.config.Version;

import java.util.Optional;
import java.util.Properties;

public class BasicConfiguration extends Configuration {

    private static final Properties basicConfiguration = new Properties();

    public Properties getBasicConfiguration() {
        return basicConfiguration;
    }

    public Optional<String> getTheme() {
        return Optional.ofNullable(basicConfiguration.getProperty("theme", null));
    }
    public Optional<Version> getVersion() {
        final String version = basicConfiguration.getProperty("version", null);

        return version == null ? Optional.empty() : Optional.of(Version.of(version));
    }
}
