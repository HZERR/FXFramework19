package ru.hzerr.fx.framework.core.context.config.now;

import ru.hzerr.fx.framework.core.context.config.now.sc.BaseConfigAnnotationClassScanner;
import ru.hzerr.fx.framework.core.context.config.now.sc.Scanner;

public class Context {

    private static final Scanner<Class<?>> classScanner = new BaseConfigAnnotationClassScanner();

    public void scan(ScanConfiguration configuration) {
        configuration.rootPackage()
    }
}
