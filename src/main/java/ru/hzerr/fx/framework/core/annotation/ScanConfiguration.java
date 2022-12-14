package ru.hzerr.fx.framework.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, определяющая наследника класса {@link ru.hzerr.fx.framework.core.a.configuration.IScanConfiguration}
 */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScanConfiguration {
}
