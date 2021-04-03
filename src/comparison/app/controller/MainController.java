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
                .withFxmlPath("../../fxml/general.fxml")
                .withIconPath("../../icons/ic_launcher.png")
                .withTitle("General settings")
                .withWeight(585)
                .withHeight(314)
                .getStage();
    }

    @FXML
    public void settings() {
        new StageBuilder.Builder()
                .withFxmlPath("../../fxml/setting.fxml")
                .withIconPath("../../icons/ic_launcher.png")
                .withTitle("Path settings")
                .withWeight(585)
                .withHeight(314)
                .getStage();
    }

    @FXML
    public void about() {
        alerts.informationAlert();
    }
}
