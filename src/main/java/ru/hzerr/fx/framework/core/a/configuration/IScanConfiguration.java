package ru.hzerr.fx.framework.core.a.configuration;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface IScanConfiguration {

    @NonNull String[] rootPackages();
    @NonNull String fxmlPackage();
    @Nullable String themePackage();
    @Nullable String internationalizationPackage();
}
