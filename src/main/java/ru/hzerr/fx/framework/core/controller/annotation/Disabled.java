package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Применяется на контроллере, который не требуется загружать для конкретного fxml-файла
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Disabled {
}
