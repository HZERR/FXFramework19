package ru.hzerr.fx.framework.core.context.config.now;

import org.springframework.context.ApplicationContext;

public interface IApplicationContextProvider<T extends ApplicationContext> {

    T getApplicationContext();
}
