package comparison.controller;

import comparison.components.Alerts;
import comparison.components.PropertiesReader;
import comparison.components.Validator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerHelper {
    Validator validator;
    Alerts alerts;
    PropertiesReader propertiesReader;

    ControllerHelper() {
        this.validator = new Validator();
        this.alerts = new Alerts();
        this.propertiesReader = new PropertiesReader();
    }

    /*
     * anchorId - Main Anchor Pane
     * textField - Text Field To set Path
     * method help to select directory
     */
    public void browseEndpoint(AnchorPane anchorId, TextField textField) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            textField.setText(String.valueOf(file.getAbsoluteFile()));
        }
    }

    /*
     * command - command which need to run in shell
     * method help to execute command in shell
     */
    public void executor(String command) {
        ExecutorService ex = Executors.newSingleThreadExecutor(r -> {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            thread.setDaemon(true);
            return thread;
        });
        ex.execute(() -> {
            try {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(command);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
