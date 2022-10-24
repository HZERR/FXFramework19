package ru.hzerr.fx.framework.core.fxml.resolver;

import ru.hzerr.collections.list.CopyOnWriteArrayHList;
import ru.hzerr.fx.framework.core.fxml.FXML;
import ru.hzerr.fx.framework.exception.ResolveException;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SyncFXMLResolver extends AbstractFXMLResolver {

    protected SyncFXMLResolver() {
        super(new CopyOnWriteArrayHList<>());
    }

    @Override
    public FXML resolve(String name) throws ResolveException {
        synchronized (data) {
            return super.resolve(name);
        }
    }
}
