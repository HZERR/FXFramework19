package ru.hzerr.fx.framework.core.context.config.now;

import java.io.IOException;
import java.net.URL;

public interface Resource extends InputStreamSource {

    URL getURL() throws IOException;

    String getDescription();
}
