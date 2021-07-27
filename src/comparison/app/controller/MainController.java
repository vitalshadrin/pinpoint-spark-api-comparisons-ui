package comparison.app.controller;

import comparison.app.components.StageBuilder;
import javafx.fxml.FXML;


public class MainController extends ControllerHelper {

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
        alerts.informationAlert(appText.getAppText("changeLog"));
    }

    @FXML
    public void about() {
        alerts.informationAlert(appText.getAppText("information").replace("{{version}}", appText.getAppText("appVersion")));
    }
}
