package ru.hzerr.fx.framework.core.context;

import java.util.function.Consumer;

public class Sequence<T extends Throwable> implements ISequence {

    private Class<T> exceptionClass;
    private Consumer<T> handler;
}
