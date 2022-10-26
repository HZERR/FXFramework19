package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import ru.hzerr.fx.framework.logging.LogManager;

public abstract class AbstractController extends UIController {

    @FXML
    public void initialize() {
        LogManager.getFXLogger().debug("Initializing class-controller " + this.getClass().getSimpleName());
        try {
            onInit();
            updateLocale();
            applyTheme();
        } catch (Exception e) {
            onInitializationFailure(e);
        }
    }

    protected abstract void onInit() throws Exception;
    protected abstract void updateLocale();

    protected void onInitializationFailure(Exception e) {
        LogManager.getFXLogger().error("Initialization failure in class-controller " + this.getClass().getSimpleName(), e);
    }
}
