package comparison.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class OptionsController extends ControllerHelper {
    private final int MAX_LENGTH = 110;
    @FXML
    TitledPane t_api;
    @FXML
    TitledPane t_demographics;
    @FXML
    TitledPane t_media;
    @FXML
    TitledPane t_periods;
    @FXML
    TitledPane t_universes;
    @FXML
    TitledPane t_subuniverses;
    @FXML
    TitledPane t_crossperiods;
    @FXML
    TitledPane t_build;
    @FXML
    TitledPane t_threads;
    @FXML
    TitledPane t_threshold;
    @FXML
    TitledPane t_minimumfraction;
    @FXML
    TitledPane t_tolerance;
    @FXML
    TitledPane t_kpitype;
    @FXML
    TitledPane t_plannerUrl;
    @FXML
    TitledPane t_resorceApi;
    @FXML
    TitledPane t_ifupdiff;
    @FXML
    TitledPane t_thresholdUpdiff;
    @FXML
    TitledPane t_skippedreportsUpdiff;
    @FXML
    TitledPane t_cache;
    @FXML
    TitledPane t_metricsGroupKey;
    @FXML
    TextField api;
    @FXML
    TextField demographics;
    @FXML
    TextField media;
    @FXML
    TextField periods;
    @FXML
    TextField universes;
    @FXML
    TextField subuniverses;
    @FXML
    TextField crossperiods;
    @FXML
    TextField build;
    @FXML
    TextField threads;
    @FXML
    TextField threshold;
    @FXML
    TextField minimumfraction;
    @FXML
    TextField tolerance;
    @FXML
    TextField kpitype;
    @FXML
    TextField plannerUrl;
    @FXML
    TextField resorceApi;
    @FXML
    TextField ifupdiff;
    @FXML
    TextField thresholdUpdiff;
    @FXML
    TextField skippedreportsUpdiff;
    @FXML
    TextField cache;
    @FXML
    TextField metricsGroupKey;
    @FXML
    Button save;

    @FXML
    private void saveOptions() {
        Map<String, String> fields = new HashMap<>();
        optionsFields.values().forEach(textField -> fields.put(textField.getAccessibleHelp(), textField.getText()));
        options.saveProperty(fields);
        updateOptions();
        optionsFields.keySet().forEach(titledPane -> {
            String value = options.getProperties().getProperty(titledPane.getAccessibleHelp());
            titledPane.setText(titledPane.getAccessibleHelp() +
                    " (" + (value.length() < MAX_LENGTH ? value : value.substring(0, MAX_LENGTH) + "...") + ")");
        });

    }

    @FXML
    private void undoOptions() {
        setOption();
    }

    @FXML
    public void initialize() {
        setOption();
    }

    private void setOption() {
        try {
            setOptionsFields(
                    Arrays.asList(t_api, t_demographics, t_media, t_periods, t_universes, t_subuniverses, t_crossperiods, t_build,
                            t_threads, t_threshold, t_minimumfraction, t_tolerance, t_kpitype, t_plannerUrl, t_resorceApi, t_ifupdiff,
                            t_thresholdUpdiff, t_skippedreportsUpdiff, t_cache, t_metricsGroupKey),
                    Arrays.asList(api, demographics, media, periods, universes, subuniverses, crossperiods, build, threads, threshold,
                            minimumfraction, tolerance, kpitype, plannerUrl, resorceApi, ifupdiff, thresholdUpdiff, skippedreportsUpdiff, cache, metricsGroupKey)
            );
            optionsFields.forEach((titledPane, textField) -> {
                String titledPaneValue = options.getProperties().getProperty(titledPane.getAccessibleHelp());
                titledPane.setText(titledPane.getAccessibleHelp() +
                        " (" + (titledPaneValue.length() < MAX_LENGTH ? titledPaneValue : titledPaneValue.substring(0, MAX_LENGTH) + "...") + ")");
                if (titledPaneValue.length() < 10) {
                    titledPane.setTooltip(new Tooltip(titledPaneValue));
                }

                titledPane.setFont(Font.font(null, FontWeight.BOLD, 12));
                textField.setText(options.getProperties().getProperty(textField.getAccessibleHelp()));
            });
        } catch (Exception e) {
            optionsFieldsErrorsStatus = true;
        }
    }
}


