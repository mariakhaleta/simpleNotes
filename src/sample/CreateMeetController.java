package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class CreateMeetController {

    @FXML
    private DatePicker dataMeets;

    @FXML
    private JFXTextField meetsTitle;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXTextArea meetsField;

    @FXML
    private JFXButton addButton;

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
                Set<Note> meetnote = m.readValue(text, new TypeReference<Set<Note>>() {});
                meetnote.add(new Note(meetsTitle.getText(), meetsField.getText(), "Встреча"));
                //System.out.println(meetnote);

                FileWriter pw = new FileWriter("JSONNote.json", false);
                Gson gson = new Gson();
                String jsonNote  = gson.toJson(meetnote);
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

        if (meetsTitle.getText() == null || meetsTitle.getText().length() == 0) {
            errorMessage += "No items!\n";
        }
        if (meetsField.getText() == null || meetsField.getText().length() == 0) {
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
