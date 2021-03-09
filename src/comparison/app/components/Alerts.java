package comparison.app.components;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerts {

    public boolean informationAlert(String text) {
        ButtonType continueBtn = new ButtonType("Continue");
        ButtonType stopButton = new ButtonType("Stop");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "", continueBtn, stopButton);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
        return alert.getResult().equals(continueBtn);
    }
}
