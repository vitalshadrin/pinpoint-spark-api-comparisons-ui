package comparison.app.components;

import comparison.app.file.FilePath;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Alerts {

    public boolean confirmationAlert(String text) {
        ButtonType continueBtn = new ButtonType("Continue");
        ButtonType stopButton = new ButtonType("Stop");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", continueBtn, stopButton);
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(FilePath.ICON.getFilePath()));
        alert.showAndWait();
        return alert.getResult() != null && alert.getResult().equals(continueBtn);
    }

    public void informationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "");
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(FilePath.ICON.getFilePath()));
        alert.showAndWait();
    }

    public void errorAlert(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "");
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> alert.hide());
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(errorText);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(FilePath.ICON.getFilePath()));
        alert.showAndWait();
    }
}
