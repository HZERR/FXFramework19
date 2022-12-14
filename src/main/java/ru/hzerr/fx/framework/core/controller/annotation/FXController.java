package ru.hzerr.fx.framework.core.controller.annotation;

import org.springframework.stereotype.Component;
import ru.hzerr.fx.framework.core.a.configuration.IScanConfiguration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, включающая в себя основные данные fx контроллера.</br>
 * Все контроллеры должны быть аннотированы данной аннотацией.</br>
 * Также необходимо создать наследника класса {@link IScanConfiguration} и передать вызывающей стороне,
 * чтобы система могла находить fxmls, bundles, themes относительно конкретных папок.</br>
 * <b>id</b> - уникальный идентификатор контроллера</br>
 * <b>fxmlRoute</b> - расположение fxml-файла относительно папки, где хранятся все fxml. Такую папку определяет {@link IScanConfiguration}</br>
 * <b>themeRoute</b> - расположение css-файла относительно папки, где хранятся все темы приложения. Такую папку определяет {@link IScanConfiguration}</br>
 * <b>localeRoute</b> - расположение properties-файла относительно папки, где хранятся все bundles приложения. Такую папку определяет {@link IScanConfiguration}</br>
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface FXController {
    String id();
    String fxmlRoute();
    String themeRoute();
    String localeRoute();
}
