package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String value() default "";
}
