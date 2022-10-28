package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.hzerr.fx.framework.core.controller.annotation.Route;
import ru.hzerr.fx.framework.core.controller.theme.ITheme;

@Route("fxml/main.fxml")
@ru.hzerr.fx.framework.core.controller.annotation.Controller("main")
public class MainController extends Controller {

    @FXML
    private AnchorPane root;

    @Override
    protected void onChangeLanguage(ILanguage language) {

    }

    @Override
    protected void onChangeUI(ITheme theme) {

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
