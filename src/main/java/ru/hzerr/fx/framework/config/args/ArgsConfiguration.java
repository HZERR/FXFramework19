package ru.hzerr.fx.framework.config.args;

import ru.hzerr.fx.framework.config.Configuration;

public class ArgsConfiguration extends Configuration {

    private InternalFrameworkOptions options;

    public ArgsConfiguration() {
    }

    public boolean isDebugEnabled() {
        return options.isDebugEnabled();
    }

    public InternalFrameworkOptions getOptions() {
        return options;
    }

    void setOptions(InternalFrameworkOptions options) {
        this.options = options;
    }
}
