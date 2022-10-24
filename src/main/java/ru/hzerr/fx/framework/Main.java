package ru.hzerr.fx.framework;

import javafx.application.Application;
import ru.hzerr.fx.framework.core.context.FxApplicationContext;

public class Main {

    public static void main(String[] args) {
        FxApplicationContext.getInitializationSettings().setArgs(args);
        Application.launch(TestClass.class, args);
    }
}