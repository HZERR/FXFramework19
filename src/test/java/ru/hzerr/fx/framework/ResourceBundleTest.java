package ru.hzerr.fx.framework;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

class ResourceBundleTest {

    @Test
    public void testFetchingResourceBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.main-controller");
        System.out.println(Locale.ENGLISH.getLanguage());
        System.out.println(resourceBundle.getString("hello"));
    }
}
