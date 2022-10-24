package ru.hzerr.fx.framework.core.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SequentialHandler implements ISequentialHandler {

    private final Map<Class<? extends Throwable>, Consumer<? extends Throwable>> sequence = new ConcurrentHashMap<>();

    @Override
    public <TH extends Throwable> void addInSequence(Class<TH> exClass, Consumer<TH> handler) {
        sequence.put(exClass, handler);
    }

    @Override
    public <TH extends Throwable> void removeFromSequence(Class<TH> exClass) {
        sequence.remove(exClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(Throwable ex) {
        sequence.forEach((aClass, consumer) -> {
            if (aClass.getName().equals(ex.getClass().getName())) {
                ((Consumer<Throwable>) consumer).accept(ex);
            }
        });
    }
}
