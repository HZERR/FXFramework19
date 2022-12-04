package ru.hzerr.fx.framework.core.a.provider;

import org.springframework.context.ApplicationContext;

public interface IApplicationContextProvider<T extends ApplicationContext> {

    T getApplicationContext();
}
