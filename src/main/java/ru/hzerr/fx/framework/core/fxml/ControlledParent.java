package ru.hzerr.fx.framework.core.fxml;

public class ControlledParent<CONTROLLER, PARENT> {

    private final CONTROLLER controller;
    private final PARENT parent;

    public ControlledParent(CONTROLLER controller, PARENT parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public CONTROLLER getController() {
        return controller;
    }

    public PARENT getParent() {
        return parent;
    }
}
