package sample;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListNotes {

    @FXML
    private AnchorPane content;

    @FXML
    private TableColumn<Note, String> notesTitle;

    @FXML
    private TableColumn<Note, String> tagView;

    @FXML
    private TableView<Note> notesView;
    @FXML
    private Button addImageButton;

    @FXML
    private Button addNewCheckLIstButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton addNewNotesButton;

    @FXML
    private Label titleViewShow;

    @FXML
    private Label noteShow;

    @FXML
    private JFXButton addNewMeetButton;

    @FXML
    private JFXButton addNewGoalButton;

    @FXML
    public static ObservableList<Note> notes = FXCollections.observableArrayList();

    public ObservableList<Note> getNotesData() {
        return notes;
    }

    @FXML
    void initialize() throws IOException {

        run();

        notesTitle.setCellValueFactory(new PropertyValueFactory<Note, String>("titleNote"));
        tagView.setCellValueFactory(new PropertyValueFactory<Note, String>("tag"));
        notesView.setItems(notes);

        showNoteDetails(null);

        notesView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showNoteDetails(newValue));
    }



    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        try {

            JsonFactory f = new JsonFactory();
            List<NoteMeet> lastNote = null;
            JsonParser jp = f.createJsonParser(new File("/Users/MariaKhaleta/Desktop/note/JSONNote.json"));
            TypeReference<List<Note>> tRef = new TypeReference<List<Note>>() {};
            lastNote = mapper.readValue(jp, tRef);
            for (Note note : lastNote) {
                ListNotes.notes.add(note);
            }
        } catch (JsonGenerationException | JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteNote() {
        int selectedIndex = notesView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            notesView.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Note Selected");
            alert.setContentText("Please select a note in the table.");

            alert.showAndWait();
        }
    }
    
    private void showNoteDetails(Note note){

        if(note!=null){

            titleViewShow.setText(note.titleNote);
            noteShow.setText(note.fieldNote);
        }
        else{
            titleViewShow.setText("");
            noteShow.setText("");
        }
    }

//    public void init(){
//        notes.add(new Note("Купить шавухи", "Купить шавухи завтра"));
//    }

    public void handleNewNote(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) addNewNotesButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createNote.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Создание заметки");
        stage.setScene(new Scene(root1));
        stage.show();
        notes.clear();

        Controller controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
    }

    @FXML
    void handleNewMeet(ActionEvent event) throws IOException {

        Stage stage = (Stage) addNewMeetButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createMeet.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Создание встречи");
        stage.setScene(new Scene(root1));
        stage.show();
        notes.clear();

        CreateMeetController createMeetController = fxmlLoader.getController();
        createMeetController.setDialogStage(stage);
    }

    @FXML
    void handleNewGoal(ActionEvent event) throws IOException {

        Stage stage = (Stage) addNewGoalButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createGoals.fxml"));
        Parent root1 = (Parent)fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Создание цели");
        stage.setScene(new Scene(root1));
        stage.show();
        notes.clear();

        Controller controller = fxmlLoader.getController();
        controller.setDialogStage(stage);
    }

}
