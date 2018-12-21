package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField notesTitle;

    @FXML
    private RadioButton goalButton;

    @FXML
    private TextArea notesField;

    @FXML
    private Button backButton;

    @FXML
    private Button buttonAddNotes;

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private RadioButton meetButton;

    @FXML
    private RadioButton alertButton;


    private boolean okClicked = false;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void initialize() {
        alertButton.setToggleGroup(radioGroup);
        goalButton.setToggleGroup(radioGroup);
        meetButton.setToggleGroup(radioGroup);
    }

    @FXML
    private void handleOk() throws IOException {

        if (isInputValid()) {

            if(alertButton.isSelected()) {
                Note mNote = new Note(notesTitle.getText(), notesField.getText(), "Cрочное");
                ListNotes.notes.add(mNote);
            }else if (goalButton.isSelected()){
                Note mNote = new Note(notesTitle.getText(), notesField.getText(), "Goal");
                ListNotes.notes.add(mNote);
            }else if (meetButton.isSelected()){
                Note mNote = new Note(notesTitle.getText(), notesField.getText(), "Встреча");
                ListNotes.notes.add(mNote);
            }
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (notesTitle.getText() == null || notesTitle.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (notesField.getText() == null || notesField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    @FXML
    private void handleCancel() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
