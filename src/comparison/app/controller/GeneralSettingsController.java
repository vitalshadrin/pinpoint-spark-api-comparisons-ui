package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GeneralSettingsController extends ControllerHelper {
    @FXML
    AnchorPane generalPane;

    @FXML
    TextField all;

    @FXML
    TextField bau;

    @FXML
    TextField features;

    @FXML
    TextField metadata;

    @FXML
    public Button generalCancel;

    @FXML
    public void clickGeneralSave() {
        Map<String, String> newSettings = new HashMap<>();
        newSettings.put("all", all.getText());
        newSettings.put("bau", bau.getText());
        newSettings.put("features", features.getText());
        newSettings.put("metadata", metadata.getText());
        general.saveProperty(newSettings);
        alerts.informationAlert(appText.getAppText("generalSettingsUpdated"));
    }

    @FXML
    void clickGeneralCancel() {
        Stage stage = (Stage) generalCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        try {
            all.setText(general.getProperties().getProperty("all"));
            bau.setText(general.getProperties().getProperty("bau"));
            features.setText(general.getProperties().getProperty("features"));
            metadata.setText(general.getProperties().getProperty("metadata"));
        } catch (Exception ex) {
            alerts.errorAlert(appText.getAppText("pleaseCheckGeneralConfig"));
            final Stage stage = (Stage) generalPane.getScene().getWindow();
            stage.close();
        }
    }
}
