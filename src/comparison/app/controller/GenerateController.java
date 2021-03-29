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
    private void generate() {
        boolean readyStatus =
                validator.validate(url, urlLabel,  dictionary.getProperties().getProperty("urlError")) &
                        validator.validate(endpointStorePath, labelGenerateStore, dictionary.getProperties().getProperty("endpointStorePathError"));
        if (readyStatus && alerts.confirmationAlert( dictionary.getProperties().getProperty("generateInformationAlert"))) {
            executor((System.getProperty("os.name").equals("Linux") ? "gnome-terminal -- " : "cmd /c start generate1.cmd ")
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
        validator.validateListener(textFieldLabelHashMap);
    }
}
