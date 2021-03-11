package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.util.Arrays;


public class OptionsController extends ControllerHelper {

    @FXML
    TextField api;

    @FXML
    Button save;

    @FXML
    TextField demographics;

    @FXML
    private void saveOptions() {
        TextField[] fields = {api, demographics};
        Arrays.asList(fields).forEach(field -> {
            System.out.println(field.getAccessibleHelp() + " = " + field.getText());
            options.saveProperty(field.getAccessibleHelp(), field.getText());
        });
    }

    @FXML
    public void initialize() {
        TextField[] fields = {api, demographics};
        Arrays.asList(fields).forEach(field -> {
            field.setText(options.getProperties().getProperty(field.getAccessibleHelp()));
        });
    }

}


