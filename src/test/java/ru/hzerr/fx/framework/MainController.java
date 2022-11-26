package ru.hzerr.fx.framework;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.hzerr.fx.framework.core.controller.BaseController;
import ru.hzerr.fx.framework.core.controller.ILanguage;
import ru.hzerr.fx.framework.core.controller.annotation.FXController;
import ru.hzerr.fx.framework.core.controller.theme.AbstractTheme;

@FXController(id = "main", route = "fxml/main.fxml", themePrefix = "i", themePostfix = "i", localeBasePath = "locale.main-controller")
public class MainController extends BaseController {

    @FXML
    private AnchorPane root;

    @Override
    protected void onChangeLanguage(ILanguage language) {

    }

    @Override
    protected void onChangeUI(AbstractTheme theme) {

    }

    @Override
    protected void onInit() throws Exception {
        root.getChildren().add(new Button("Super!"));
    }

    @Override
    protected void onInitializationFailure(Exception e) {
        super.onInitializationFailure(e);
    }
}
