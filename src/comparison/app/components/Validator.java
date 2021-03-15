package comparison.app.components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class Validator {

    public void setTextFieldColor(TextField textField, Label label, String error, Boolean status) {
        if (!status) {
            label.setText(error);
            textField.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
        } else {
            label.setText(" ");
            textField.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
        }
    }

    public Boolean validate(TextField textField, Label label, String error) {
        if (textField.getText().isEmpty()) {
            setTextFieldColor(textField, label, error, false);
            return false;
        } else {
            setTextFieldColor(textField, label, " ", true);
            return true;
        }
    }

    public void validateListener(HashMap<TextField, Label> textFieldLabelHashMap) {
        textFieldLabelHashMap.forEach((textField, label) -> textField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
                label.setText(" ");
                textField.setStyle("-fx-border-color: black ; -fx-border-width: 0px ;");
            }
        }));
    }
}
