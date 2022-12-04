package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.theme.AbstractTheme;

public abstract class BaseController {

    private static final HList<BaseController> controllers = new SynchronizedHList<>();

    protected BaseController() {
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
    protected abstract void onChangeUI(AbstractTheme theme);

    protected void setApplicationTheme(AbstractTheme theme) {
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
