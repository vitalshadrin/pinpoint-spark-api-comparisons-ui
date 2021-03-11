package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ComparisonController extends ControllerHelper implements Initializable {

    @FXML
    private AnchorPane anchorId;

    @FXML
    private TextField browseFirst;

    @FXML
    private Label labelFirstStore;

    @FXML
    private TextField browseSecond;

    @FXML
    private Label labelSecondStore;

    @FXML
    private TextField browseComparePath;

    @FXML
    private Label labelCompareStore;

    @FXML
    private void browseFirstEndpoint() {
        browseEndpoint(this.anchorId, this.browseFirst);
    }

    @FXML
    private void browseSecondEndpoint() {
        browseEndpoint(this.anchorId, this.browseSecond);
    }

    @FXML
    private void browseComparePath() {
        browseEndpoint(this.anchorId, this.browseComparePath);
    }

    @FXML
    private void compare() {
        boolean readyStatus =
                validator.validate(browseFirst, labelFirstStore, dictionary.getProperties().getProperty("browseFirstError")) &
                        validator.validate(browseSecond, labelSecondStore, dictionary.getProperties().getProperty("browseSecondError")) &
                        validator.validate(browseComparePath, labelCompareStore, dictionary.getProperties().getProperty("browseComparePathError"));
        if (readyStatus && alerts.informationAlert(dictionary.getProperties().getProperty("compareInformationAlert"))) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start compare.cmd ") +
                    browseFirst.getText() + " " +
                    browseSecond.getText() + " " +
                    browseComparePath.getText());
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<TextField, Label> textFieldLabelHashMap = new HashMap<TextField, Label>() {{
            put(browseFirst, labelFirstStore);
            put(browseSecond, labelSecondStore);
            put(browseComparePath, labelCompareStore);
        }};
        validator.validateListener(textFieldLabelHashMap);
    }
}
