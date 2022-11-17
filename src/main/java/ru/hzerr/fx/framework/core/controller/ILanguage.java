package ru.hzerr.fx.framework.core.controller;

import java.util.Locale;

public interface ILanguage {

    boolean contains(String key);
    String get(String key);
    Locale locale();
}
