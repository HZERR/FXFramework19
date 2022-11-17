package ru.hzerr.fx.framework.core.context.config.now;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.file.BaseDirectory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.fx.framework.core.context.config.Initializable;

public abstract class StructureConfiguration implements Initializable {

    public abstract @NonNull BaseDirectory getRootDirectory();
    public abstract @NonNull BaseDirectory getSerializableDirectory();
    public abstract @NonNull BaseDirectory getAssetsDirectory();
    public abstract @NonNull BaseDirectory getConfigurationDirectory();
    public abstract @NonNull BaseFile getConfigurationFile();
    public abstract @NonNull BaseDirectory getLogDirectory();
}
