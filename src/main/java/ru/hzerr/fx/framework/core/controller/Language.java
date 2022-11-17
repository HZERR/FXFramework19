package ru.hzerr.fx.framework.core.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language implements ILanguage {

    private ResourceBundle bundle;

    public Language(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public boolean contains(String key) {
        return bundle.containsKey(key);
    }

    public String get(String key) {
        return (String) bundle.getObject(key);
    }

    @Override
    public Locale locale() {
        return bundle.getLocale();
    }
}
