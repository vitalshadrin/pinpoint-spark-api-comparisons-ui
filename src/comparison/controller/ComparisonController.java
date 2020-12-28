package comparison.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ComparisonController extends ControllerHelper implements Initializable {
    @FXML
    private TextArea consoleLog;

    @FXML
    private AnchorPane anchorId;

    @FXML
    private TextField endpointStorePath;

    @FXML
    private TextField browseFirst;

    @FXML
    private TextField browseSecond;

    @FXML
    private TextField browseComparePath;

    @FXML
    private TextField url;

    @FXML
    private Label urlErrorLabel;

    @FXML
    private Label pathErrorLabel;

    @FXML
    private void browseStoreEndpoint() {
        endpointStorePath.setOnMouseClicked(event -> pathErrorLabel.setText("hello"));
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
        Boolean readyStatus = true;
        if (url.getText().isEmpty()) {
            urlErrorLabel.setText("Please fill URL");
            url.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            readyStatus = false;
        }
        if (endpointStorePath.getText().isEmpty()) {
            pathErrorLabel.setText("Please fill endpoint store path");
            endpointStorePath.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            readyStatus = false;
        }else {
            pathErrorLabel.setText(" ");
            readyStatus = true;
        }

        if (readyStatus) {
            executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar generate-summary -url "
                    + url.getText() + " -s "
                    + endpointStorePath.getText() +
                    " && exit");
        }
    }

    @FXML
    private void compare() {
        executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar compare-summaries " +
                "-s1 " + browseFirst.getText() + " " +
                "-s2 " + browseSecond.getText() + " " +
                "-sr " + browseComparePath.getText() + "  " +
                " && exit");
    }


    public void appendText(String str) {
        Platform.runLater(() -> this.consoleLog.appendText(str));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        url.textProperty().addListener((obs, oldText, newText) -> {
            if(!newText.isEmpty()){
                urlErrorLabel.setText(" ");
                url.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
            }
        });

        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }
}
