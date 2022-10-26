package ru.hzerr.fx.framework.core.controller;

import ru.hzerr.collections.list.HList;
import ru.hzerr.collections.list.SynchronizedHList;
import ru.hzerr.fx.framework.core.controller.theme.BaseTheme;

public abstract class UIController {

    // TODO: 10/24/22 CHECK ONLY 1 INSTANCE AND CHECK DE-REGISTRATION METHOD
    private static final HList<UIController> controllers = new SynchronizedHList<>();

    public UIController() {
        controllers.add(this);
    }

    protected abstract void applyTheme(BaseTheme theme);
    protected void applyThemeToEveryone(BaseTheme theme) {
        controllers.forEach(uiController -> uiController.applyTheme(theme));
    }

    protected void deregister() {
        controllers.remove(this);
    }
}
