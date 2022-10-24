package ru.hzerr.fx.framework.core.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

@Route("fxml/main.fxml")
@Controller("main")
public class MainController extends AbstractController {

    @FXML
    private AnchorPane root;

    @Override
    protected void onInit() throws Exception {
        root.getChildren().add(new Button("Super!"));
    }

    @Override
    protected void updateLocale() {

    }

    @Override
    protected void updateUI() {

    }

    @Override
    protected void onInitializationFailure(Exception e) {
        super.onInitializationFailure(e);
    }
}
