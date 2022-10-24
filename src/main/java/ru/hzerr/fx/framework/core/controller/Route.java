package ru.hzerr.fx.framework.core.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Все контроллеры должны быть аннотированы данной аннотацией, а также @{@link Controller}.
 * Также необходимо установить базовый пакет, в котором будет происходить поиск fxml-контроллеров - <code>FXApplicationContext.getFXMLSettings().setBaseControllerPackage()</code>.
 * Данная аннотация определяет к какому FXML относится данный контроллер.
 * В параметрах аннотации необходимо указать, где искать fxml-файл.
 * Необходимо указывать строку таким образом, как вы передавали бы ее в {@link Class#getResource(String)}.
 * Например:
 * <code>@Route("fxml/main.fxml")</code>
 * @author HZERR
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    String value() default "";
}
