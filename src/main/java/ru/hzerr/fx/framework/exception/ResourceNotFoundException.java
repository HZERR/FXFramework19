package ru.hzerr.fx.framework.exception;

public class ResourceNotFoundException extends ResourceLoadException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
