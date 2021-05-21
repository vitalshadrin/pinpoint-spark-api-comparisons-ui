package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    public void initialize(URL location, ResourceBundle resources) {
        options.getProperties().stringPropertyNames().stream().sorted().forEach(name -> {
            TextField textField = new TextField();
            TitledPane titledPane = new TitledPane(name + "(" + options.getProperties().getProperty(name) + ")" , textField);
            textField.setText(options.getProperties().getProperty(name));
            titledPane.setFont(Font.font(null, FontWeight.BOLD, 12));
            accordion.getPanes().add(titledPane);
        });
        accordion.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
    }
}
