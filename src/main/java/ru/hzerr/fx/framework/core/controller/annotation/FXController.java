package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, включающая в себя основные данные fx контроллера.</br>
 * Все контроллеры должны быть аннотированы данной аннотацией.</br>
 * Также необходимо создать наследника класса {@link ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration} и передать вызывающей стороне,
 * чтобы система могла находить fxmls, bundles, themes относительно конкретных папок.</br>
 * <b>id</b> - уникальный идентификатор контроллера</br>
 * <b>fxmlRoute</b> - расположение fxml-файла относительно папки, где хранятся все fxml. Такую папку определяет {@link ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration}</br>
 * <b>themeRoute</b> - расположение css-файла относительно папки, где хранятся все темы приложения. Такую папку определяет {@link ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration}</br>
 * <b>localeRoute</b> - расположение properties-файла относительно папки, где хранятся все bundles приложения. Такую папку определяет {@link ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration}</br>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FXController {
    String id();
    String fxmlRoute();
    String themeRoute();
    String localeRoute();
}
