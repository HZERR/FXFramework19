package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import ru.hzerr.fx.framework.logging.LogManager;

public abstract class AbstractController {

    @FXML
    public void initialize() {
        LogManager.getFXLogger().debug("Initializing class-controller " + this.getClass().getSimpleName());
        try {
            onInit();
            updateLocale();
            updateUI();
        } catch (Exception e) {
            onInitializationFailure(e);
        }
    }

    protected abstract void onInit() throws Exception;
    protected abstract void updateLocale();
    protected abstract void updateUI();

    protected void onInitializationFailure(Exception e) {
        LogManager.getFXLogger().error("Initialization failure in class-controller " + this.getClass().getSimpleName(), e);
    }
}
