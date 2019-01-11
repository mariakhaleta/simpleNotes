package sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class CreateGoalsController {

    @FXML
    private JFXTextField goalsTitle;

    @FXML
    private JFXCheckBox achiveGoal;

    @FXML
    private JFXButton backButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXTextArea goalsField;

    @FXML
    void initialize() {

    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (goalsTitle.getText() == null || goalsTitle.getText().length() == 0) {
            errorMessage += "No items!\n";
        }
        if (goalsField.getText() == null || goalsField.getText().length() == 0) {
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

    @FXML
    private void handleOk() throws IOException, JSONException {

        if (isInputValid()) {

            try{
                String text = new String(Files.readAllBytes(Paths.get("JSONNote.json")), StandardCharsets.UTF_8);
                ObjectMapper m = new ObjectMapper();
                Set<Note> goalnote = m.readValue(text, new TypeReference<Set<Note>>() {});
                goalnote.add(new Note(goalsTitle.getText(), goalsField.getText(), "Цель"));
                //System.out.println(goalnote);
                FileWriter pw = new FileWriter("JSONNote.json", false);
                Gson gson = new Gson();
                String jsonNote  = gson.toJson(goalnote);
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
}
