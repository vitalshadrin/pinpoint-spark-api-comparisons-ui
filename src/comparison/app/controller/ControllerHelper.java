package comparison.app.controller;

import comparison.app.components.Alerts;
import comparison.app.components.Validator;
import comparison.app.property.PropertiesReader;
import comparison.app.property.PropertyPath;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerHelper {
    Validator validator = new Validator();
    Alerts alerts = new Alerts();
    PropertiesReader options = new PropertiesReader(PropertyPath.OPTIONS);
    PropertiesReader general = new PropertiesReader(PropertyPath.GENERAL);
    Map<TitledPane, TextField> optionsFields = new HashMap<>();
    public static Boolean optionsFieldsErrorsStatus = false;
    private static String folderPath = "";

    public void updateOptions() {
        this.options = new PropertiesReader(PropertyPath.OPTIONS);
    }

    /**
     * @param anchorId  - Main Anchor Pane
     * @param textField - Text Field To set Path
     * @method help to select directory
     */
    public void browseEndpoint(AnchorPane anchorId, TextField textField) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) anchorId.getScene().getWindow();
        if (!folderPath.isEmpty()) directoryChooser.setInitialDirectory(new File(this.folderPath));
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            this.folderPath = String.valueOf(file.getAbsoluteFile());
            textField.setText(String.valueOf(file.getAbsoluteFile()));
        }
    }

    /**
     * @param command - command which need to run in shell
     * @method help to execute command in shell
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

    /**
     * @param tooltip - java fx tooltip
     * @method change tooltip delay
     */
    public static void tooltipStartTiming(Tooltip tooltip, double timeOut) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);
            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);
            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(timeOut)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
