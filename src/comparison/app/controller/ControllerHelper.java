package comparison.app.controller;

import comparison.app.components.Alerts;
import comparison.app.components.Validator;
import comparison.app.property.PropertiesReader;
import comparison.app.property.PropertyPath;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ControllerHelper {
    Validator validator = new Validator();
    Alerts alerts = new Alerts();
    PropertiesReader dictionary = new PropertiesReader(PropertyPath.DICTIONARY);
    PropertiesReader options = new PropertiesReader(PropertyPath.OPTIONS);
    Map<TitledPane, TextField> optionsFields = new HashMap<>();


    public void updateOptions() {
        this.options = new PropertiesReader(PropertyPath.OPTIONS);
    }

    /**
     * @param titledPanes - option titled Panes
     * @param textFields- option titled Fields
     * @method set all options in Map
     */
    public void setOptionsFields(List<TitledPane> titledPanes, List<TextField> textFields) {
        titledPanes.forEach(titledPane -> this.optionsFields.put(titledPane,
                textFields
                        .stream()
                        .filter(textField -> titledPane.getAccessibleHelp().equals(textField.getAccessibleHelp()))
                        .collect(Collectors.toList()).get(0)));
    }


    /**
     * @param anchorId  - Main Anchor Pane
     * @param textField - Text Field To set Path
     *                  method help to select directory
     */
    public void browseEndpoint(AnchorPane anchorId, TextField textField) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            textField.setText(String.valueOf(file.getAbsoluteFile()));
        }
    }

    /**
     * @param command - command which need to run in shell
     *                method help to execute command in shell
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
