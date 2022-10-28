package ru.hzerr.fx.framework.core.controller.annotation;

/**
 * locales -> controllers -> main-ru.properties
 *                        -> main-en.properties
 */
public @interface Locale {

    String prefix();
    String postfix();
}
