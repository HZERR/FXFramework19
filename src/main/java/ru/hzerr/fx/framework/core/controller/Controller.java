package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.theme.ITheme;
import ru.hzerr.fx.framework.logging.LogManager;

public abstract class Controller {

    private static final HList<Controller> controllers = new SynchronizedHList<>();

    public Controller() {
        register();
    }

    @FXML
    public void initialize() {
        LogManager.getFXLogger().debug("Initializing class-controller " + this.getClass().getSimpleName());
        try {
            onInit();
        } catch (Exception e) {
            onInitializationFailure(e);
        }
    }

    protected abstract void onChangeLanguage(ILanguage language);
    protected abstract void onChangeUI(ITheme theme);

    protected void setApplicationTheme(ITheme theme) {
        controllers.forEach(controller -> controller.onChangeUI(theme));
    }
    protected void setApplicationLanguage(ILanguage language) {
        controllers.forEach(controller -> controller.onChangeLanguage(language));
    }

    protected abstract void onInit() throws Exception;

    protected void onInitializationFailure(Exception e) {
        LogManager.getFXLogger().error("Initialization failure in class-controller " + this.getClass().getSimpleName(), e);
    }

    private void register() { controllers.add(this); }
    public void unregister() { controllers.remove(this); }
}
