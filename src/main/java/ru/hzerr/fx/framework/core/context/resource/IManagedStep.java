package ru.hzerr.fx.framework.core.context.resource;

import ru.hzerr.fx.framework.core.controller.ILanguage;
import ru.hzerr.fx.framework.core.fxml.FXML;

import java.util.Locale;

public interface IManagedStep {

    FXML fetchFXML();
    ILanguage fetchLanguage(Locale locale);
}
