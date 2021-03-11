package comparison.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Comparison");
        stage.setResizable(false);
        stage.setScene(new Scene(rootNode, 563, 450));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../icons/ic_launcher.png")));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));
        rootNode = fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
