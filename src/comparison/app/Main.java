package comparison.app;

import comparison.app.components.Alerts;
import comparison.app.file.FilePath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static comparison.app.controller.ControllerHelper.appText;
import static comparison.app.controller.ControllerHelper.optionsFieldsErrorsStatus;

public class Main extends Application {
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Comparison " + appText.getAppText("appVersion"));
        stage.setResizable(true);
        stage.setScene(new Scene(rootNode, 800, 526));
        stage.setMinWidth(563);
        stage.setMinHeight(565);
        stage.setMaxHeight(565);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(FilePath.ICON.getFilePath())));
        stage.show();
        if (optionsFieldsErrorsStatus)
            new Alerts().errorAlert(appText.getAppText("pleaseCheckConfigPath"));
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/comparison/fxml/main.fxml"));
        rootNode = fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
