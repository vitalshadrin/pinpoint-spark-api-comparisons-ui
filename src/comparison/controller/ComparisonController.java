package comparison.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ComparisonController extends ControllerHelper implements Initializable {

    Validator validator= new Validator();

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
    private Button compareBtn;

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
            executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar generate-summary -url "
                    + url.getText() + " -s "
                    + endpointStorePath.getText() +
                    " && exit");
        }
    }

    @FXML
    private void compare() {
        boolean readyStatus =
                validator.validate(browseFirst, labelFirstStore, "Please fill 1st stored path") &
                        validator.validate(browseSecond, labelSecondStore, "Please fill 2st stored path") &
                        validator.validate(browseComparePath, labelCompareStore, "Please fill compare results store path");
        if (readyStatus && showAlert("Are you sure that want to continue compare")) {
            executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar compare-summaries " +
                    "-s1 " + browseFirst.getText() + " " +
                    "-s2 " + browseSecond.getText() + " " +
                    "-sr " + browseComparePath.getText() + "  " +
                    " && exit");
        }
    }

    private boolean showAlert(String text){
        ButtonType continueBtn = new ButtonType("Continue");
        ButtonType stopButton = new ButtonType("Stop");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", continueBtn, stopButton);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
        if (alert.getResult().equals(continueBtn)) {
           return true;
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<TextField, Label> textFieldLabelHashMap = new HashMap<TextField, Label>(){{
            put(url, urlLabel);
            put(endpointStorePath, labelGenerateStore);
            put(browseFirst, labelFirstStore);
            put(browseSecond, labelSecondStore);
            put(browseComparePath, labelCompareStore);
        }};
        validator.validateListener(textFieldLabelHashMap);
    }



}
