package sample;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Controller {

    @FXML
    private JFXTextField notesTitle;

    @FXML
    private JFXTextArea notesField;

    @FXML
    private JFXButton backButton;

    @FXML
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void initialize() {

    }

    @FXML
    private void handleOk() throws IOException {

        if (isInputValid()) {
            Note mNote = new Note(notesTitle.getText(), notesField.getText(), "Заметка");
                ListNotes.notes.add(mNote);

            JSONObject jo = new JSONObject();
            jo.put("Title", notesTitle.getText());
            jo.put("Field", notesField.getText());
            jo.put("Tag", "Заметка");
            PrintWriter pw = new PrintWriter("JSONNote.json");
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();

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
