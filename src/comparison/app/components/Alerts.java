package comparison.app.components;

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
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("./comparison/icons/ic_launcher.png"));
        alert.showAndWait();
        return alert.getResult() == null ? false : alert.getResult().equals(continueBtn);
    }
}
