package comparison.app.controller;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TestOptionsController extends ControllerHelper implements Initializable {
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
    public void initialize(URL location, ResourceBundle resources) {

        anchorPane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            save.setLayoutX(newValue.doubleValue()/2 - (save.widthProperty().getValue() / 2));
        });

        options.getProperties().stringPropertyNames().stream().sorted().forEach(name -> {
            TextField textField = new TextField();
            TitledPane titledPane = new TitledPane(name + " (" + options.getProperties().getProperty(name) + ")" , textField);
            textField.setText(options.getProperties().getProperty(name));
            titledPane.setFont(Font.font(null, FontWeight.BOLD, 12));
            accordion.getPanes().add(titledPane);
        });
        accordion.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
    }
}
