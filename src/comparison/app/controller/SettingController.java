package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingController extends ControllerHelper {

    @FXML
    private AnchorPane anchorId;

    @FXML
    TextField generalText;

    @FXML
    TextField testOptionsText;

    @FXML
    Button cancel;

    @FXML
    void clickCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void browseGeneralSettings() {
        browseEndpoint(this.anchorId, this.generalText);
    }

    @FXML
    private void browseTestOption() {
        browseEndpoint(this.anchorId, this.testOptionsText);
    }
}
