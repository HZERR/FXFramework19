package ru.hzerr.fx.framework.config;

import org.apache.commons.configuration2.PropertiesConfiguration;

public class Configuration {

    private PropertiesConfiguration configuration;

    public Configuration() {}
    public Configuration(PropertiesConfiguration configuration) {
        this.configuration = configuration;
    }

    public PropertiesConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(PropertiesConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean isConfigurable() { return configuration != null; }
}
