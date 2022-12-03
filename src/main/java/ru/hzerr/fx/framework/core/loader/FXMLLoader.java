package ru.hzerr.fx.framework.core.loader;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javafx.scene.Parent;
import ru.hzerr.collections.pair.IUnmodifiablePair;
import ru.hzerr.collections.pair.UnmodifiablePair;
import ru.hzerr.fx.framework.core.FxApplicationContext;
import ru.hzerr.fx.framework.core.controller.BaseController;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.ResourceLoadException;
import ru.hzerr.fx.framework.exception.ResourceNotFoundException;
import ru.hzerr.fx.framework.exception.unchecked.FXControllerNotFoundException;
import ru.hzerr.fx.framework.logging.LogManager;

import java.io.IOException;
import java.net.URL;

/**
 * @version 1.0.0.1
 */
@SuppressWarnings("unchecked")
public final class FXMLLoader {

    private FXMLLoader() {
    }

    /**
     * Загружает FXML и возвращает загруженный узел.
     * Практически как и метод load, за исключением того, что контроллер не возвращается.
     *
     * @param id id контроллера
     * @return загруженный узел
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     * @since 1.0.0.1
     */
    public static Parent
    loadParent(String id) throws ResourceLoadException {
        return load0(id, Parent.class, Object.class).key();
    }

    /**
     * Загружает FXML и возвращает загруженный узел.
     * Практически как и метод load, за исключением того, что контроллер не возвращается.
     *
     * @param id          id контроллера
     * @param parentClass класс главной ноды
     * @param <P>         тип главной ноды
     * @return загруженный узел
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     * @since 1.0.0.1
     */
    public static <P extends Parent> P
    loadParent(String id, Class<P> parentClass) throws ResourceLoadException {
        return load0(id, parentClass, Object.class).key();
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @param id id контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     * @since 1.0.0.1
     */
    @CanIgnoreReturnValue
    public static IUnmodifiablePair<Parent, Object>
    load(String id) throws ResourceLoadException {
        return load0(id, Parent.class, Object.class);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @param id              id контроллера
     * @param controllerClass класс контроллера
     * @param <C>             тип контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(String)
     * @since 1.0.0.1
     */
    @CanIgnoreReturnValue
    public static <C extends BaseController> IUnmodifiablePair<Parent, C>
    load(Class<C> controllerClass, String id) throws ResourceLoadException {
        return load0(id, Parent.class, controllerClass);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @param id          id контроллера
     * @param parentClass класс главной ноды
     * @param <P>         тип главной ноды
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(Class, String)
     * @see #load(String)
     * @since 1.0.0.1
     */
    @CanIgnoreReturnValue
    public static <P extends Parent> IUnmodifiablePair<P, Object>
    load(String id, Class<P> parentClass) throws ResourceLoadException {
        return load0(id, parentClass, Object.class);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @param id              id контроллера
     * @param parentClass     класс главной ноды
     * @param controllerClass класс контроллера
     * @param <P>             тип главной ноды
     * @param <C>             тип контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @throws ResourceLoadException         если FXML не получается загрузить
     * @throws ResourceNotFoundException     если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     * @since 1.0.0.1
     */
    @CanIgnoreReturnValue
    public static <P extends Parent, C extends BaseController> IUnmodifiablePair<P, C>
    load(String id, Class<P> parentClass, Class<C> controllerClass) throws ResourceLoadException {
        return load0(id, parentClass, controllerClass);
    }

    private static <P extends Parent, C> IUnmodifiablePair<P, C>
    load0(String id, Class<P> parentClass, Class<C> controllerClass) throws ResourceLoadException {
        // TODO: 11/12/22 ПРОСМОТРЕТь ВЕРНЫЙ ЛИ ШАБЛОН
        LogManager.getFXLogger().debug("Fetching fxml file for \"{}\"...", id);
        FXML fxml = FxApplicationContext.getResourceManager()
                .fetchByID(id)
                .orElseThrow(() -> new FXControllerNotFoundException("The FXController " + id + " does not exist"))
                .fetchFXML();

        LogManager.getFXLogger().debug("Finding the {} file", fxml.location());
        URL url = FxApplicationContext.getResourceManager().getClass().getResource(fxml.location()); // TODO: 28.11.2022 SET CLASS LOADERS

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();

        loader.setController(fxml.controller());
        loader.setLocation(url);

        P parent;
        try {
            parent = loader.load();
        } catch (IOException io) {
            throw new ResourceLoadException("Failed to load " + fxml.location() + " file", io);
        } catch (IllegalStateException ise) {
            throw new ResourceNotFoundException("Resource file " + fxml.location() + " was not found");
        }

        LogManager.getFXLogger().debug("{} has been successfully loaded", fxml.location());

        return new UnmodifiablePair<>(parent, (C) fxml.controller());
    }
}
