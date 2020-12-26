package comparison;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Parent rootNode;

    @Override
    public void start(Stage stage) {
        //stage.setScene(new Scene(rootNode));
        stage.setTitle("Comparison");
        stage.setResizable(false);
        stage.setScene(new Scene(rootNode, 800, 900));
        stage.show();
    }

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        rootNode = fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
