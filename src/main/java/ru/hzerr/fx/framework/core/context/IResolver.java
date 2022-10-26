package ru.hzerr.fx.framework.core.context;

/**
 * Controller + Additional Context -> Resource [URL] -> FXML, Image, Css
 *
 *
 *
 * @param <K>
 * @param <V>
 */

public interface IResolver<K, V> {

    V resolveResource(K key);
}
