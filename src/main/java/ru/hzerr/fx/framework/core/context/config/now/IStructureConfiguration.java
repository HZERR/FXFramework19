package ru.hzerr.fx.framework.core.context.config.now;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.file.BaseDirectory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.fx.framework.core.context.config.Initializable;

public interface IStructureConfiguration {

    @NonNull BaseDirectory getRootDirectory();
    @NonNull BaseDirectory getSerializableDirectory();
    @NonNull BaseDirectory getAssetsDirectory();
    @NonNull BaseDirectory getConfigurationDirectory();
    @NonNull BaseFile getConfigurationFile();
    @NonNull BaseDirectory getLogDirectory();
}
