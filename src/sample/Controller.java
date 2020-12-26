package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable {

    @FXML
    private TextArea consoleLog;

    Process generateProcess;

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
    private void generate(ActionEvent actionEvent) {
        ExecutorService ex = Executors.newSingleThreadExecutor(r -> {
            Thread t = Executors.defaultThreadFactory().newThread(r);
            t.setDaemon(true);
            return t;
        });
        ex.execute(() -> {
            try {
                List<String> commands = new ArrayList<>();
                commands.add("ping");
                commands.add("www.google.com");
                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                generateProcess = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(generateProcess.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void stopGenerating(ActionEvent actionEvent){
        generateProcess.destroy();
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
        System.setErr(new PrintStream(out, true));
    }
}
