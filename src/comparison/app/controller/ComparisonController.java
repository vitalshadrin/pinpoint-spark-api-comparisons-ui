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
                validator.validate(browseFirst, labelFirstStore, "Please fill 1st stored path.") &
                        validator.validate(browseSecond, labelSecondStore, "Please fill 2st stored path.") &
                        validator.validate(browseComparePath, labelCompareStore, "Please fill compare results store path.");
        if (readyStatus && alerts.confirmationAlert("Are you sure you want to continue the comparison?")) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start compare-ui.cmd ") +
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
