package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
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
        TitledPane tilePane = new TitledPane();
        tilePane.setText("Hello");
        accordion.setExpandedPane(tilePane);

        accordion.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefWidthProperty().bind(pane.widthProperty());
        scrollPane.prefHeightProperty().bind(pane.heightProperty());
    }
}
