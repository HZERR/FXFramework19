package ru.hzerr.fx.framework.core.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, включающая в себя основные аннотации fx контроллера:
 * <pre>
 *     1. @Controller
 *     2. @Route
 *     3. @Theme
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FXController {
    String id();
    String route();
    String themePrefix();
    String themePostfix();
}
