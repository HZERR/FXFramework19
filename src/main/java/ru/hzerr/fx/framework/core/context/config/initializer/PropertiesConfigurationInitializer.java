package ru.hzerr.fx.framework.core.context.config.initializer;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import ru.hzerr.file.BaseFile;
import ru.hzerr.fx.framework.exception.unchecked.UncheckedConfigurationException;

public class PropertiesConfigurationInitializer implements ConfigurationInitializer<PropertiesConfiguration> {
    private final FileBasedConfigurationBuilder<PropertiesConfiguration> builder;

    public PropertiesConfigurationInitializer(BaseFile config) {
        builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                .configure(new Parameters().properties()
                        .setFile(config.asIOFile())
                        .setThrowExceptionOnMissing(true)
                        .setListDelimiterHandler(new DefaultListDelimiterHandler(';'))
                        .setIncludesAllowed(true));
    }

    /**
     *
     * @throws UncheckedConfigurationException в случае возникновения ошибки при инициализации объекта конфигурации
     */
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
