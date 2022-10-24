package ru.hzerr.fx.framework.logging;

import ch.qos.logback.classic.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.hzerr.fx.framework.exception.FactoryCloseableException;
import ru.hzerr.fx.framework.exception.UncheckedLoggingInitializationException;
import ru.hzerr.fx.framework.logging.factory.LogFactory;
import ru.hzerr.fx.framework.logging.factory.FXFrameworkSessionLogFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

// TODO: 18.10.2022 СДЕЛАТЬ ХУК, ЧТОБЫ НАСТРОЙКИ МЕНЯЛИСЬ В РАНТАЙМЕ
public class LogManager {

    private static final ObjectProperty<LogFactory> FX_FACTORY = new SimpleObjectProperty<>(new FXFrameworkSessionLogFactory());
    private static final ObjectProperty<LogFactory> USER_FACTORY = new SimpleObjectProperty<>(FX_FACTORY.getValue());
    private static final AtomicBoolean MISSING_CUSTOM_LOG_FACTORY = new AtomicBoolean(true);

    static {
        registerFactories();
    }

    private static void registerFactories() {
        try {
            ((FXFrameworkSessionLogFactory) FX_FACTORY.getValue()).createSession();
        } catch (IOException io) {
            throw new UncheckedLoggingInitializationException(io);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(LogManager::closeCurrentFrameworkFactoryIfPresent));
        Runtime.getRuntime().addShutdownHook(new Thread(LogManager::closeCurrentUserFactoryIfPresent));
        FX_FACTORY.addListener((observableValue, o, n) -> {
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    throw new FactoryCloseableException(e);
                }
            }
        });

        USER_FACTORY.addListener((observableValue, o, n) -> {
            if (!MISSING_CUSTOM_LOG_FACTORY.compareAndSet(true, false)) {
                if (o != null) {
                    try {
                        o.close();
                    } catch (IOException e) {
                        throw new FactoryCloseableException(e);
                    }
                }
            }
        });
    }

    private LogManager() {
        FX_FACTORY.getValue().getLogger().debug("LogManager has been successfully initialized");
    }

    public static void setFrameworkLogFactory(LogFactory factory) {
        FX_FACTORY.setValue(factory);
    }
    public static void setLogFactory(LogFactory factory) { USER_FACTORY.setValue(factory); }

    /**
     * @return FXFramework logger
     */
    public static Logger getFXLogger() {
        return FX_FACTORY.getValue().getLogger();
    }

    /**
     * @return Custom logger
     */
    public static Logger getLogger() { return FX_FACTORY.getValue().getLogger(); }

    private static void closeCurrentFrameworkFactoryIfPresent() {
        if (FX_FACTORY.isNotNull().get()) {
            try {
                FX_FACTORY.getValue().close();
            } catch (IOException e) {
                throw new FactoryCloseableException(e);
            }
        }
    }

    private static void closeCurrentUserFactoryIfPresent() {
        if (USER_FACTORY.isNotNull().get()) {
            try {
                USER_FACTORY.getValue().close();
            } catch (IOException e) {
                throw new FactoryCloseableException(e);
            }
        }
    }
}
