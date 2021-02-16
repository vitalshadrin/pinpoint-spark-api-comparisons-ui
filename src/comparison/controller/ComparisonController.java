package comparison.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ComparisonController extends ControllerHelper implements Initializable {

    @FXML
    private AnchorPane anchorId;

    @FXML
    private TextField endpointStorePath;

    @FXML
    private Label labelGenerateStore;

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
    private TextField url;

    @FXML
    private Label urlLabel;

    @FXML
    private void browseStoreEndpoint() {
        browseEndpoint(this.anchorId, this.endpointStorePath);
    }

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
    private void generate() {
        boolean readyStatus =
                validator.validate(url, urlLabel, "Please fill URL") &
                        validator.validate(endpointStorePath, labelGenerateStore, "Please fill endpoint store path");
        if (readyStatus && showAlert("Are you sure that want to continue new Endpoint generation")) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start generate1.cmd ")
                    + url.getText() + " "
                    + endpointStorePath.getText());
        }
    }

    @FXML
    private void compare() {
        boolean readyStatus =
                validator.validate(browseFirst, labelFirstStore, "Please fill 1st stored path") &
                        validator.validate(browseSecond, labelSecondStore, "Please fill 2st stored path") &
                        validator.validate(browseComparePath, labelCompareStore, "Please fill compare results store path");
        if (readyStatus && showAlert("Are you sure that want to continue compare")) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start compare.cmd ") +
                    browseFirst.getText() + " " +
                    browseSecond.getText() + " " +
                    browseComparePath.getText());
        }
    }

    private boolean showAlert(String text) {
        ButtonType continueBtn = new ButtonType("Continue");
        ButtonType stopButton = new ButtonType("Stop");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", continueBtn, stopButton);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
        return alert.getResult().equals(continueBtn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<TextField, Label> textFieldLabelHashMap = new HashMap<TextField, Label>() {{
            put(url, urlLabel);
            put(endpointStorePath, labelGenerateStore);
            put(browseFirst, labelFirstStore);
            put(browseSecond, labelSecondStore);
            put(browseComparePath, labelCompareStore);
        }};
        validator.validateListener(textFieldLabelHashMap);
    }
}
