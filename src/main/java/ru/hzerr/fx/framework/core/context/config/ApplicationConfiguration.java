package ru.hzerr.fx.framework.core.context.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.core.controller.ILanguage;

import java.util.Locale;
import java.util.Objects;

public class ApplicationConfiguration extends FileConfiguration {

    private final DefaultConfiguration defaultConfiguration;

    public ApplicationConfiguration(@NonNull PropertiesConfiguration configuration, @NonNull DefaultConfiguration defaultConfiguration) {
        super(configuration);
        this.defaultConfiguration = defaultConfiguration;
    }

    public void setApplicationVersion(Version version) {
        configuration.setProperty("application.version", version);
    }

    public Version getApplicationVersion() {
        final String v = configuration.getString("application.version");

        return v == null
                ? defaultConfiguration.getApplicationVersion()
                : Version.of(v);
    }

    public void setLocale(ILanguage language) {
        setLocale(language.locale());
    }

    public void setLocale(Locale locale) {
        configuration.setProperty("application.locale", locale.getLanguage());
    }

    public Locale getLocale() {
        final String l = configuration.getString("application.locale");

        return l == null
                ? defaultConfiguration.getApplicationLocale()
                : Locale.of(l);
    }

    public void setApplicationName(String name) {
        configuration.setProperty("application.name", name);
    }

    public String getApplicationName() {
        final String n = configuration.getString("application.name");

        return Objects.requireNonNullElse(n, defaultConfiguration.getApplicationName());
    }
}
