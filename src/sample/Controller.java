package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
    private void browseStoreEndpoint(ActionEvent actionEvent) {
        browseEndpoint(endpointStorePath);
    }

    @FXML
    private void browseFirstEndpoint(ActionEvent actionEvent) {
        browseEndpoint(browseFirst);
    }

    @FXML
    private void browseSecondEndpoint(ActionEvent actionEvent) {
        browseEndpoint(browseSecond);
    }

    @FXML
    private Button compareBtn;

    @FXML
    private void generate(ActionEvent actionEvent){
        try {
            Process process = Runtime.getRuntime().exec("java -version");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void browseComparePath(ActionEvent actionEvent) {
        browseEndpoint(browseComparePath);
    }

    private void browseEndpoint(TextField textField) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            textField.setText(String.valueOf(file.getAbsoluteFile()));
        }
    }

    public void appendText(String str) {
        Platform.runLater(() -> consoleLog.appendText(str));
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
    }
}
