package ru.hzerr.fx.framework.core.context;

public class LoggableHandlerSettings implements Settings {

    private SequentialHandler sharedHandler;
    private SequentialHandler actionEventHandler;
    private SequentialHandler changeHandler;
    private SequentialHandler processHandler;

    private boolean useSharedHandler = true;

    public void useSharedHandler() {
        this.useSharedHandler = true;
    }

    public void useCustomHandler() {
        this.useSharedHandler = false;
    }

    public SequentialHandler getLoggableActionEventHandler() {
        return actionEventHandler;
    }

    public SequentialHandler getLoggableChangeHandler() {
        return changeHandler;
    }

    public SequentialHandler getLoggableProcessHandler() {
        return processHandler;
    }

    public SequentialHandler getLoggableSharedHandler() {
        return sharedHandler;
    }
}
