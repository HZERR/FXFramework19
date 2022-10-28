package ru.hzerr.fx.framework.core.controller.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Данная аннотация(над контроллером) указывает где искать необходимый css-файл.
 * Prefix - указывает в какой папке содержаться все темы.
 * Postfix - указывает в каком месте(относительно папки с определенной темой) искать css-файл.</br>
 * Например, допустим, структура имеет такой вид:</br>
 * <pre>
 * resources -> theme -> light -> tab -> main-tab.css
 *                    -> dark  -> tab -> main-tab.css
 * </pre>
 * В таком случае над контроллером необходимо повесить аннотацию таким образом:</br>
 * {@code Theme(prefix="/theme",  postfix="/tab/main-tab.css"}
 * @see FXController
 * @see Route
 * @see Controller
 * @author HZERR
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Theme {
    String prefix() default "";
    String postfix() default "";
}
