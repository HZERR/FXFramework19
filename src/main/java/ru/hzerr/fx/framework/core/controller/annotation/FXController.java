package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, включающая в себя основные аннотации fx контроллера:
 * <pre>
 *     1. @Controller
 *     2. @Route
 *     3. @Theme
 *     4. @Internationalization
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FXController {
    String id();
    String route();
    String themePrefix();
    String themePostfix();
    String localeBasePath();
}
