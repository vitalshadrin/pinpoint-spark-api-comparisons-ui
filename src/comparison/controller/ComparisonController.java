package comparison.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ComparisonController extends ControllerHelper implements Initializable {
    Validator validator = new Validator();

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
        if (readyStatus) {
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
        if (readyStatus) {
            executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar compare-summaries " +
                    "-s1 " + browseFirst.getText() + " " +
                    "-s2 " + browseSecond.getText() + " " +
                    "-sr " + browseComparePath.getText() + "  " +
                    " && exit");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validator.validateListener(url, urlLabel);
        validator.validateListener(endpointStorePath, labelGenerateStore);
        validator.validateListener(browseFirst, labelFirstStore);
        validator.validateListener(browseSecond, labelSecondStore);
        validator.validateListener(browseComparePath, labelCompareStore);
    }
}
