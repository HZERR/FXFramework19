package ru.hzerr.fx.framework.core.loader;

import ru.hzerr.collections.map.HMap;
import ru.hzerr.collections.map.HashHMap;
import ru.hzerr.exception.StylesheetLoadException;
import ru.hzerr.logging.LogManager;
import ru.hzerr.runtime.controller.ThemeType;

import java.net.URL;

public class CssLoader {

    private static final HMap<String, String> CACHE = new HashHMap<>();
    private static final String CSS_PREFIX = "/theme";
    private static final String CSS_POSTFIX = ".css";

    private CssLoader() {
    }

    public static String load(ThemeType themeType, LoadType loadType, String name) throws StylesheetLoadException {
        final String key = newKey(themeType, loadType, name);
        LogManager.getLogger().debug("Fetching css " + key);
        if (CACHE.noContainsKey(key)) {
            final URL cssURL = CssLoader.class.getResource(key);
            if (cssURL != null) {
                LogManager.getLogger().debug("The css " + key + " has been successfully loaded");
                return CACHE.putAndGet(key, cssURL.toExternalForm());
            } else
                throw new StylesheetLoadException("Can't load css " + key + ". " + key + " url is null");
        } else {
            LogManager.getLogger().debug("The css " + key + " has been successfully retrieved from the cache");
            return CACHE.get(key);
        }
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private static String newKey(ThemeType themeType, LoadType loadType, String name) {
        return new StringBuilder(CSS_PREFIX)
                .append('/').append(themeType.getName())
                .append('/').append(loadType.getPrefix())
                .append('/').append(name)
                .append(CSS_POSTFIX).toString();
    }

    public enum LoadType {
        ROOT("root"),
        TAB("tab"),
        POPUP("popup"),
        ELEMENT("element");

        private final String prefix;

        LoadType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() { return this.prefix; }
    }
}
