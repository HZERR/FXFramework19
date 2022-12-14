package ru.hzerr.fx.framework.core.a.initializer;

import ru.hzerr.fx.framework.core.a.configuration.impl.InternalFileBasicConfiguration;
import ru.hzerr.fx.framework.exception.unchecked.UncheckedConfigurationException;

import java.io.InputStream;
import java.util.Properties;

/**
 * Данный класс предоставляет инициализатор конфигурации приложения по-умолчанию с помощью встроенного в jar-файл properties-файла
 */
public class InternalFileBasicConfigurationInitializer implements ConfigurationInitializer<InternalFileBasicConfiguration>  {
    private final String path;

    private ClassLoader propertiesClassLoader = ClassLoader.getSystemClassLoader();

    public InternalFileBasicConfigurationInitializer(String path) {
        this.path = path;
    }

    /**
     * Установите собственный загрузчик properties-файла
     * @param classLoader загрузчик properties-файла
     */
    public void setClassLoader(ClassLoader classLoader) {
        this.propertiesClassLoader = classLoader;
    }

    /**
     * Загружает конфигурацию методом {@link Properties#load(InputStream)}.
     * Также проверяет наличие всех установленных параметров в данной конфигурации.
     *
     * @return загруженную конфигурацию по-умолчанию в виде {@link InternalFileBasicConfiguration}
     * @throws UncheckedConfigurationException при любой ошибке, будто это DefaultPropertyNotInitializationException или NullPointerException
     */
    @Override
    public InternalFileBasicConfiguration getConfiguration() {
        final Properties properties = new Properties();
        try {
            properties.load(propertiesClassLoader.getResourceAsStream(path));
            InternalFileBasicConfiguration configuration = new InternalFileBasicConfiguration(properties);
            configuration.getApplicationVersion();
            configuration.getApplicationLocale();
            return configuration;
        } catch (Exception e) {
            throw new UncheckedConfigurationException(e);
        }
    }
}
