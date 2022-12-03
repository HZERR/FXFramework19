package ru.hzerr.fx.framework.core.context.config.now;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import ru.hzerr.fx.framework.core.context.config.Initializable;

public abstract class ScanConfiguration implements Initializable {

    public abstract @NonNull String[] rootPackages();
    public abstract @NonNull String fxmlPackage();
    public abstract @Nullable String themePackage();
    public abstract @Nullable String internationalizationPackage();
}
