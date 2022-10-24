package ru.hzerr.fx.framework.core.context;

import java.util.function.Consumer;

public interface ISequentialHandler {

    <TH extends Throwable> void addInSequence(Class<TH> exClass, Consumer<TH> handler);
    <TH extends Throwable> void removeFromSequence(Class<TH> exClass);

    <TH extends Throwable> void handle(TH ex);
}
