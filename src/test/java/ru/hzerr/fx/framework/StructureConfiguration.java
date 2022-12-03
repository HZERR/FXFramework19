package ru.hzerr.fx.framework;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.hzerr.file.BaseDirectory;
import ru.hzerr.file.BaseFile;
import ru.hzerr.file.HDirectory;
import ru.hzerr.file.HFile;
import ru.hzerr.fx.framework.core.annotation.Structure;
import ru.hzerr.fx.framework.core.context.config.now.IStructureConfiguration;

@Structure
public class StructureConfiguration implements IStructureConfiguration {

    private boolean flag = false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    @Override
    public @NonNull BaseDirectory getRootDirectory() {
        return new HDirectory("");
    }

    @Override
    public @NonNull BaseDirectory getSerializableDirectory() {
        return new HDirectory("");
    }

    @Override
    public @NonNull BaseDirectory getAssetsDirectory() {
        return new HDirectory("");
    }

    @Override
    public @NonNull BaseDirectory getConfigurationDirectory() {
        return new HDirectory("");
    }

    @Override
    public @NonNull BaseFile getConfigurationFile() {
        return new HFile("") {
        };
    }

    @Override
    public @NonNull BaseDirectory getLogDirectory() {
        return new HDirectory("");
    }
}
