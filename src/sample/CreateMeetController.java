package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
    void initialize() {

    }


    @FXML
    void handleOk(ActionEvent event) {

    }

    @FXML
    void handleCancel(ActionEvent event) throws IOException {

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
