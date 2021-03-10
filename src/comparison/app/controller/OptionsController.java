package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Arrays;


public class OptionsController extends ControllerHelper {

    @FXML
    TextField api;

    @FXML
    TextField demographics;

    @FXML
    public void initialize() {
        TextField[] fields = {api, demographics};
        Arrays.asList(fields).forEach(field -> {
            field.setText(options.getProperty(field.getAccessibleHelp()));
        });
    }
}


