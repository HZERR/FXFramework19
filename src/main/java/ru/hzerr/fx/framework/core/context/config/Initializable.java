package ru.hzerr.fx.framework.core.context.config;

import ru.hzerr.fx.framework.exception.unchecked.now.InitializationException;

public interface Initializable {

    /**
     * Данный метод инициализирует полностью конкретный конфигурационный класс.
     * Любая ошибка, водзникающая в ходе инициализации класса, должна быть обернута в {@link InitializationException}.
     * Класс должен быть обязательно инициализирован этим методом перед использованием конкретных методов
     */
    void initialize() throws InitializationException;
}
