package ru.hzerr.fx.framework.core.controller.theme;

public abstract class AbstractTheme {
    private final String name;

    protected AbstractTheme(String name) { this.name = name; }

    public String getName() {
        return name;
    }
}
