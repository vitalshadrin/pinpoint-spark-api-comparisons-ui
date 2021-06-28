package comparison.app.controller;

import comparison.app.components.StageBuilder;
import javafx.fxml.FXML;

import static comparison.app.file.FilePath.CHANGE_LOG;
import static comparison.app.file.FilePath.INFO;


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
        alerts.informationAlert(fileReader.readFile(CHANGE_LOG));
    }

    @FXML
    public void about() {
        alerts.informationAlert(fileReader.readFile(INFO).replace("{{version}}", VERSION));
    }
}
