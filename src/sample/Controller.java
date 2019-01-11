package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.json.JSONException;

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
    private void handleOk() throws IOException, JSONException {

        if (isInputValid()) {

            try{
                String text = new String(Files.readAllBytes(Paths.get("JSONNote.json")), StandardCharsets.UTF_8);
                ObjectMapper m = new ObjectMapper();
                Set<Note> not = m.readValue(text, new TypeReference<Set<Note>>() {});
                not.add(new Note(notesTitle.getText(), notesField.getText(), "Заметка"));
                //System.out.println(not);

                FileWriter pw = new FileWriter("JSONNote.json", false);
                Gson gson = new Gson();
                String jsonNote  = gson.toJson(not);
                pw.write(jsonNote);
                pw.flush();
                pw.close();

            }
            catch(Exception ex){
                System.out.println(ex.toString());
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
            errorMessage += "No items!\n";
        }
        if (notesField.getText() == null || notesField.getText().length() == 0) {
            errorMessage += "No items!\n";
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
