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
        alerts.informationAlert("Change log:\n" +
                "\n" +
                "v.1.1.0\n" +
                "Resizing the application window\n" +
                "\n" +
                "v.1.0.2\n" +
                "Fixed bug for scrolling by option fields\n" +
                "\n" +
                "v.1.0.1\n" +
                "Fixed typo exceptions\n" +
                "\n" +
                "v.1.0.0\n" +
                "The first version of the application");
    }

    @FXML
    public void about() {
        alerts.informationAlert("Application version: " + VERSION + "\n" +
                "\n" +
                "For detailed information please contact:\n" +
                "* vitali.shadrin.consultant@nielsen.com\n" +
                "* nina.x.rybchak.consultant@nielsen.com");
    }
}
