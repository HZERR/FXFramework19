package ru.hzerr.fx.framework.core.context.config.now;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;
}
