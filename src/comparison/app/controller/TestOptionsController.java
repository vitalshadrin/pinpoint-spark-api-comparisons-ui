package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;

public class TestOptionsController extends ControllerHelper {
    @FXML
    private Accordion accordion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane pane;
    @FXML
    private Button save;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grid;

    @FXML
    private void saveOptions() {
        Map<String, String> fields = new HashMap<>();
        optionsFields.keySet().forEach(titledPane -> {
            fields.put(titledPane.getText(), optionsFields.get(titledPane).getText());
        });
        options.saveProperty(fields);
        updateOptions();
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
        options.getProperties().stringPropertyNames().stream().sorted().forEach(name -> {
            TextField textField = new TextField();
            TitledPane titledPane = new TitledPane(name + " (" + options.getProperties().getProperty(name) + ")", textField);
            textField.setText(options.getProperties().getProperty(name));
            titledPane.setFont(Font.font(null, FontWeight.BOLD, 12));
            accordion.getPanes().add(titledPane);
            optionsFields.put(titledPane, textField);
        });
        grid.prefWidthProperty().bind(pane.widthProperty());
        accordion.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
    }
}
