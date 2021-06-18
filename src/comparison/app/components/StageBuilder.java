package comparison.app.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StageBuilder {
    String title;
    String fxmlPath;
    String iconPath;
    int height;
    int weight;

    public static class Builder {
        private final StageBuilder newStageBuilder;

        public Builder() {
            newStageBuilder = new StageBuilder();
        }

        public Builder withFxmlPath(String fxmlPath) {
            newStageBuilder.fxmlPath = fxmlPath;
            return this;
        }

        public Builder withIconPath(String iconPath) {
            newStageBuilder.iconPath = iconPath;
            return this;
        }

        public Builder withTitle(String title) {
            newStageBuilder.title = title;
            return this;
        }

        public Builder withHeight(Integer height) {
            newStageBuilder.height = height;
            return this;
        }

        public Builder withWeight(Integer weight) {
            newStageBuilder.weight = weight;
            return this;
        }

        public StageBuilder build() {
            return newStageBuilder;
        }

        public Stage getStage() {
            Parent root;
            Stage newStage;
            try {
                root = FXMLLoader.load(getClass().getResource(newStageBuilder.fxmlPath));
                newStage = new Stage();
                newStage.setTitle(newStageBuilder.title);
                newStage.setResizable(false);
                newStage.setScene(new Scene(root, newStageBuilder.weight, newStageBuilder.height));
                newStage.getIcons().add(new Image(getClass().getResourceAsStream(newStageBuilder.iconPath)));
                newStage.show();
                return newStage;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
