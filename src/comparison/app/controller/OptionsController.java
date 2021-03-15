package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;


public class OptionsController extends ControllerHelper {

    @FXML
    TextField api;

    @FXML
    TextField demographics;

    @FXML
    Button save;

    @FXML
    private void saveOptions() {
        Map<String, String> fields = new HashMap<>();
        textFields.forEach(textField -> fields.put(textField.getAccessibleHelp(), textField.getText()));
        options.saveProperty(fields);
    }

    @FXML
    private void undoOptions() {
        setOption();
    }

    @FXML
    public void initialize() {
        setOption();
    }

    private void setOption() {
        setTextFields(api, demographics);
        textFields.forEach(textField -> textField.setText(options.getProperties().getProperty(textField.getAccessibleHelp())));
    }
}


