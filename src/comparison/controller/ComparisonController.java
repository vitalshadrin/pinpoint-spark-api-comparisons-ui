package comparison.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    private void generate() {
        executor("cmd /c start java -jar pinpoint-spark-api-comparisons.jar generate-summary -url " + url.getText() + " -s " + endpointStorePath.getText() + " && exit");
    }

    @FXML
    private void stop() {
        try {
            getRuntime().exec("taskkill /f /im cmd.exe") ;
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void browseComparePath() {
        browseEndpoint(this.anchorId, this.browseComparePath);
    }

    public void appendText(String str) {
        Platform.runLater(() -> this.consoleLog.appendText(str));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
