package ru.hzerr.fx.framework.core.a.configuration;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.fx.framework.core.a.Version;
import ru.hzerr.fx.framework.core.annotation.Inject;
import ru.hzerr.fx.framework.core.controller.ILanguage;

import java.util.Locale;
import java.util.Objects;

public class ApplicationConfiguration implements IBaseConfiguration {

    @Inject
    private PropertiesConfiguration configuration;

    @Inject
    private IBaseConfiguration baseConfiguration;

    public ApplicationConfiguration(IBaseConfiguration baseConfiguration) {
        this.baseConfiguration = baseConfiguration;
    }

    public void setApplicationVersion(Version version) {
        configuration.setProperty("application.version", version);
    }

    public @NonNull Version getApplicationVersion() {
        final String v = configuration.getString("application.version");

        return v == null
                ? baseConfiguration.getApplicationVersion()
                : Version.of(v);
    }

    public void setLocale(ILanguage language) {
        setLocale(language.locale());
    }

    public void setLocale(Locale locale) {
        configuration.setProperty("application.locale", locale.getLanguage());
    }

    public @NonNull Locale getApplicationLocale() {
        final String l = configuration.getString("application.locale");

        return l == null
                ? baseConfiguration.getApplicationLocale()
                : Locale.of(l);
    }

    public void setApplicationName(String name) {
        configuration.setProperty("application.name", name);
    }

    public @NonNull String getApplicationName() {
        final String n = configuration.getString("application.name");

        return Objects.requireNonNullElse(n, baseConfiguration.getApplicationName());
    }
}
