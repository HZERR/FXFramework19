package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Example:
 * locales -> controllers -> main_ru.properties
 *                        -> main_en.properties
 *
 * location = locales.controllers.main
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Internationalization {

    String location();
}
