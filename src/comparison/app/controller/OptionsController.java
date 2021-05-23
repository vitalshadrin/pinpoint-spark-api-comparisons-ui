package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;

public class OptionsController extends ControllerHelper {
    private final Font fieldFont = Font.font(null, FontWeight.BOLD, 14);
    @FXML
    private Accordion accordion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane pane;
    @FXML
    private GridPane grid;

    @FXML
    private void saveOptions() {
        Map<String, String> fields = new HashMap<>();
        optionsFields.keySet().forEach(titledPane -> {
            String titledPaneValue = titledPane.getText().split(" ")[0];
            String textFieldValue = optionsFields.get(titledPane).getText();
            fields.put(titledPaneValue, textFieldValue);
            titledPane.setText(titledPaneValue + " (" + textFieldValue + ")");
        });
        options.saveProperty(fields);
        updateOptions();
    }

    @FXML
    private void undoOptions() {
        optionsFields.forEach((key, value) -> value.setText(options.getProperties().getProperty(key.getText().split(" ")[0])));
    }

    @FXML
    public void initialize() {
        setOption();
        setPrefSizeOptions();
    }

    private void setOption() {
        try {
        options.getProperties().stringPropertyNames().stream().sorted().forEach(value -> {
            TextField textField = getTextField(value);
            TitledPane titledPane = getTitledPane(textField, value);
            optionsFields.put(titledPane, textField);
            accordion.getPanes().add(titledPane);
        });
        } catch (Exception e) {
            optionsFieldsErrorsStatus = true;
        }
    }

    private void setPrefSizeOptions(){
        grid.prefWidthProperty().bind(pane.widthProperty());
        accordion.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
    }

    private TitledPane getTitledPane(TextField textField, String value) {
        TitledPane titledPane = new TitledPane(value + " (" + options.getProperties().getProperty(value) + ")", textField);
        titledPane.setFont(this.fieldFont);
        return titledPane;
    }

    private TextField getTextField(String value) {
        TextField textField = new TextField();
        textField.setText(options.getProperties().getProperty(value));
        textField.setFont(this.fieldFont);
        return textField;
    }
}
