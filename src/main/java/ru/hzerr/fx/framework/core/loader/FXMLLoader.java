package ru.hzerr.fx.framework.core.loader;

import javafx.scene.Parent;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;
import ru.hzerr.fx.framework.core.fxml.ControlledParent;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.ResolveException;
import ru.hzerr.fx.framework.exception.ResourceLoadException;
import ru.hzerr.fx.framework.exception.ResourceNotFoundException;
import ru.hzerr.fx.framework.logging.LogManager;

import java.io.IOException;
import java.net.URL;

@SuppressWarnings("unchecked")
public final class FXMLLoader {

    private FXMLLoader() {
    }

    public static Parent loadParent(String name) throws ResourceLoadException, ResolveException {
        return loadParent(name, Parent.class);
    }

    public static <PARENT extends Parent> PARENT loadParent(String name, Class<PARENT> parentClass) throws ResourceLoadException, ResolveException {
        LogManager.getFXLogger().debug("Resolving fxml file for \"" + name + "\"...");
        FXML fxml = FxApplicationContext.getFXMLSettings().getResolver().resolve(name);

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
        return parent;
    }

    public static ControlledParent<Object, Parent>
    loadControlledParent(String name) throws ResourceLoadException, ResolveException {
        return loadControlledParent(name, Object.class);
    }

    public static <CONTROLLER> ControlledParent<CONTROLLER, Parent>
    loadControlledParent(String name, Class<CONTROLLER> controllerClass) throws ResourceLoadException, ResolveException {
        return loadControlledParent(name, Parent.class, controllerClass);
    }

    public static <PARENT extends Parent, CONTROLLER> ControlledParent<CONTROLLER, PARENT>
    loadControlledParent(String name, Class<PARENT> parentClass, Class<CONTROLLER> controllerClass) throws ResourceLoadException, ResolveException {
        LogManager.getFXLogger().debug("Resolving fxml file for \"" + name + "\"...");
        FXML fxml = FxApplicationContext.getFXMLSettings().getResolver().resolve(name);

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
        return new ControlledParent<>((CONTROLLER) fxml.controller(), parent);
    }
}
