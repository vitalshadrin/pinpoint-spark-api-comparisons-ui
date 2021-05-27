package comparison.app.controller;

import comparison.app.components.StageBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;


public class MainController extends ControllerHelper {

    @FXML
    private OptionsController optionsController;

    @FXML
    private ComparisonController comparisonController;

    @FXML
    private GenerateController generateController;

    @FXML
    private MenuItem generalSettings;

    @FXML
    public void general() {
        new StageBuilder.Builder()
                .withFxmlPath("/comparison/fxml/general.fxml")
                .withIconPath("/comparison/icons/ic_launcher.png")
                .withTitle("General settings")
                .withWeight(585)
                .withHeight(314)
                .getStage();
    }

    @FXML
    public void settings() {
        new StageBuilder.Builder()
                .withFxmlPath("/comparison/fxml/setting.fxml")
                .withIconPath("/comparison/icons/ic_launcher.png")
                .withTitle("Path settings")
                .withWeight(437)
                .withHeight(214)
                .getStage();
    }

    @FXML
    public void changeLog() {
        new StageBuilder.Builder()
                .withFxmlPath("/comparison/fxml/changeLog.fxml")
                .withIconPath("/comparison/icons/ic_launcher.png")
                .withTitle("Change log (beta)")
                .withWeight(437)
                .withHeight(300)
                .getStage();
    }

    @FXML
    public void about() {
        alerts.informationAlert("Application version: 1.0.2 \nFor detailed information please contact: \n* vitali.shadrin.consultant@nielsen.com \n* nina.x.rybchak.consultant@nielsen.com");
    }
}
