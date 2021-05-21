package comparison.app;

import comparison.app.components.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static comparison.app.controller.ControllerHelper.optionsFieldsErrorsStatus;

public class Main extends Application {
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Comparison v1.0.2");
        stage.setResizable(true);
        stage.setScene(new Scene(rootNode, 563, 480));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/comparison/icons/ic_launcher.png")));
        stage.show();
        if (optionsFieldsErrorsStatus)
            new Alerts().errorAlert("Please check test options config path.");
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
