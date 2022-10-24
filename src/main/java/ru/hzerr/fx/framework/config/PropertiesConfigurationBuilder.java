package ru.hzerr.fx.framework.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import ru.hzerr.file.BaseFile;
import ru.hzerr.fx.framework.exception.UncheckedConfigurationException;

public class PropertiesConfigurationBuilder implements ConfigurationBuilder<PropertiesConfiguration> {

    private BaseFile config;
    private FileBasedConfigurationBuilder<PropertiesConfiguration> builder;

    public PropertiesConfigurationBuilder(BaseFile config) {
        this.config = config;
        builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                .configure(new Parameters().properties()
                        .setFile(config.asIOFile())
                        .setThrowExceptionOnMissing(true)
                        .setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
                        .setIncludesAllowed(true));
    }

    @Override
    public PropertiesConfiguration getConfiguration() {
        builder.setAutoSave(true);
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException ce) {
            throw new UncheckedConfigurationException(ce);
        }
    }
}
