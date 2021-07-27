package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GenerateController extends ControllerHelper implements Initializable {

    @FXML
    private AnchorPane generateAnchorId;

    @FXML
    private void browseStoreEndpoint() {
        browseEndpoint(this.generateAnchorId, this.endpointStorePath);
    }

    @FXML
    private TextField endpointStorePath;

    @FXML
    private Label labelGenerateStore;

    @FXML
    private TextField url;

    @FXML
    private Label urlLabel;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label urlTextLabel;

    @FXML
    Label pathLabelTxt;

    @FXML
    private void generate() {
        boolean readyStatus =
                validator.validate(url, urlLabel, appText.getAppText("urlError")) &
                        validator.validate(endpointStorePath, labelGenerateStore, appText.getAppText("endpointStorePathError"));
        if (readyStatus && alerts.confirmationAlert(appText.getAppText("generateInformationAlert"))) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start generate-ui.cmd ")
                    + url.getText() + " "
                    + endpointStorePath.getText());
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<TextField, Label> textFieldLabelHashMap = new HashMap<TextField, Label>() {{
            put(url, urlLabel);
            put(endpointStorePath, labelGenerateStore);
        }};
        urlTextLabel.setText(appText.getAppText("urlLabelTxt"));
        pathLabelTxt.setText(appText.getAppText("pathLabelTxt"));
        validator.validateListener(textFieldLabelHashMap);
    }
}
