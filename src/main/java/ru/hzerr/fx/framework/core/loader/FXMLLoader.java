package ru.hzerr.fx.framework.core.loader;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import javafx.scene.Parent;
import ru.hzerr.collections.pair.IUnmodifiablePair;
import ru.hzerr.collections.pair.UnmodifiablePair;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;
import ru.hzerr.fx.framework.core.controller.Controller;
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
     * @since 1.0.0.1
     * @param id id контроллера
     * @return загруженный узел
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     */
    public static Parent
    loadParent(String id) throws ResourceLoadException {
        return load0(id, Parent.class, Object.class).key();
    }

    /**
     * Загружает FXML и возвращает загруженный узел.
     * Практически как и метод load, за исключением того, что контроллер не возвращается.
     *
     * @since 1.0.0.1
     * @param id id контроллера
     * @param parentClass класс главной ноды
     * @return загруженный узел
     * @param <PARENT> тип главной ноды
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     */
    public static <PARENT extends Parent> PARENT
    loadParent(String id, Class<PARENT> parentClass) throws ResourceLoadException {
        return load0(id, parentClass, Object.class).key();
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @since 1.0.0.1
     * @param id id контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     */
    @CanIgnoreReturnValue
    public static IUnmodifiablePair<Parent, Object>
    load(String id) throws ResourceLoadException {
        return load0(id, Parent.class, Object.class);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @since 1.0.0.1
     * @param id id контроллера
     * @param controllerClass класс контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @param <CONTROLLER> тип контроллера
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(String, Class)
     * @see #load(String)
     */
    @CanIgnoreReturnValue
    public static <CONTROLLER extends Controller> IUnmodifiablePair<Parent, CONTROLLER>
    load(Class<CONTROLLER> controllerClass, String id) throws ResourceLoadException {
        return load0(id, Parent.class, controllerClass);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @since 1.0.0.1
     * @param id id контроллера
     * @param parentClass класс главной ноды
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @param <PARENT> тип главной ноды
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class, Class)
     * @see #load(Class, String)
     * @see #load(String)
     */
    @CanIgnoreReturnValue
    public static <PARENT extends Parent> IUnmodifiablePair<PARENT, Object>
    load(String id, Class<PARENT> parentClass) throws ResourceLoadException {
        return load0(id, parentClass, Object.class);
    }

    /**
     * Загружает FXML и возвращает контроллер + узел, чтобы можно было изменять переменные контроллера в рантайме.
     *
     * @since 1.0.0.1
     * @param id id контроллера
     * @param parentClass класс главной ноды
     * @param controllerClass класс контроллера
     * @return контроллер + узел, с помощью которых можно контролировать поведение узла в рантайме
     * @param <PARENT> тип главной ноды
     * @param <CONTROLLER> тип контроллера
     * @throws ResourceLoadException если FXML не получается загрузить
     * @throws ResourceNotFoundException если FXML по указанному пути не найден
     * @throws FXControllerNotFoundException если контроллер с указанным имененем не найден
     * @see #loadParent(String, Class)
     * @see #loadParent(String)
     * @see #load(String, Class)
     * @see #load(Class, String)
     * @see #load(String)
     */
    @CanIgnoreReturnValue
    public static <PARENT extends Parent, CONTROLLER extends Controller> IUnmodifiablePair<PARENT, CONTROLLER>
    load(String id, Class<PARENT> parentClass, Class<CONTROLLER> controllerClass) throws ResourceLoadException {
        return load0(id, parentClass, controllerClass);
    }

    private static <PARENT extends Parent, CONTROLLER> IUnmodifiablePair<PARENT, CONTROLLER>
    load0(String id, Class<PARENT> parentClass, Class<CONTROLLER> controllerClass) throws ResourceLoadException {
        LogManager.getFXLogger().debug("Fetching fxml file for \"" + id + "\"...");
        FXML fxml = FxApplicationContext.getResourceManager()
                .fetchByID(id)
                .orElseThrow(() -> new FXControllerNotFoundException("The FXController " + id + " does not exist"))
                .fetchFXML();

        LogManager.getFXLogger().debug("Finding the " + fxml.location() + " file");
        URL url = FxApplicationContext.getFXMLSettings().getFXMLClassLoader().getResource(fxml.location());

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();

        loader.setController(fxml.controller());
        loader.setLocation(url);

        PARENT parent;
        try {
            parent = loader.load();
        } catch (IOException io) {
            throw new ResourceLoadException("Failed to load " + fxml.location() + " file", io);
        } catch (IllegalStateException ise) {
            throw new ResourceNotFoundException("Resource file " + fxml.location() + " was not found");
        }

        LogManager.getFXLogger().debug(fxml.location() + " has been successfully loaded");
        return new UnmodifiablePair<>(parent, (CONTROLLER) fxml.controller());
    }
}
