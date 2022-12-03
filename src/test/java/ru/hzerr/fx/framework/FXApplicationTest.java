package ru.hzerr.fx.framework;

import javafx.stage.Stage;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;
import ru.hzerr.fx.framework.core.FXApplication;
import ru.hzerr.fx.framework.core.context.config.FXConfiguration;
import ru.hzerr.fx.framework.core.context.config.now.ScanConfiguration;

public class FXApplicationTest extends FXApplication {

    @Test
    void testStartup() {
        launch();
    }

    @Override
    protected ScanConfiguration scanConfiguration() throws Exception {
        return new ScanConfiguration() {
            @Override
            public @NonNull String[] rootPackages() {
                return new String[] {"ru.hzerr.fx.framework"};
            }

            @Override
            public @NonNull String fxmlPackage() {
                return "null";
            }

            @Override
            public @Nullable String themePackage() {
                return null;
            }

            @Override
            public @Nullable String internationalizationPackage() {
                return null;
            }

            @Override
            public void initialize() {

            }
        };
    }

    @Override
    protected void onInit(FXConfiguration configuration) throws Exception {
        ((StructureConfiguration) context.getBean(ru.hzerr.fx.framework.core.context.config.now.IStructureConfiguration.class)).setFlag(true);
        System.out.println(((StructureConfiguration) context.getBean(ru.hzerr.fx.framework.core.context.config.now.IStructureConfiguration.class)).isFlag());
    }

    @Override
    protected void onStart(Stage stage) throws Exception {

    }

    @Override
    protected void onExit() throws Exception {

    }
}
