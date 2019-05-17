package nba_statistics.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.entities.Zawodnicy;
import nba_statistics.services.PlayersService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectData implements Initializable {

    @FXML
    private Button LogOutBtn;


    @FXML private Text dataViewer;
    @FXML private Text question;
    @FXML private Button viewDatabaseBtn;
    @FXML private Button backBtn;
    @FXML private ListView<Zawodnicy> listView;
    @FXML private TextField surname;
    @FXML private ComboBox<String> comboBox;

    ObservableList<String> kindOfData = FXCollections.observableArrayList
            ("Player's achievements", "List of top 10 shooters", "List of team players", "Timetable");


    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void changeScreenBack(ActionEvent event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }


    public void selectPlayers(){
        PlayersService playersService = new PlayersService();
        ObservableList<Zawodnicy> players = FXCollections.observableArrayList( playersService.getPlayers(surname.getText()));

        if(players.size()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("No found a player");
            alert.setContentText("Player is not exist");

            alert.showAndWait();
        }

        listView.setItems(players);
    }

    public void selectListTopTenSchooters(){

    }

    public void selectlistOfTeamPlayers(){

    }

    public void selectTimetable(){

    }

    public void whatKindOfDataIsSelected(){

        switch(comboBox.getValue()){
            case "Player's achievements":
                selectPlayers();
                break;

            case "List of top 10 shooters":
                selectListTopTenSchooters();
                break;

            case "List of team players":
                selectlistOfTeamPlayers();
                break;

            case "Timetable":
                selectTimetable();
                break;

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(kindOfData);
    }




}
