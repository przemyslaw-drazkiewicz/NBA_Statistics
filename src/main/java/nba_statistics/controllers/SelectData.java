package nba_statistics.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import nba_statistics.entities.HistoriaDruzynZawodnika;
import nba_statistics.entities.OsiagnieciaZawWMeczu;
import nba_statistics.entities.Zawodnicy;
//import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SelectData implements Initializable {

    //main text
    @FXML private Button LogOutBtn;
    @FXML private Button backBtn;
    @FXML private Button viewDatabaseBtn;
    @FXML private ComboBox<String> comboBox;
    @FXML private Text dataViewer;

    //Player's achievements
    @FXML private Text writeName;
    @FXML private Text writeSurname;
    @FXML private TextField surname;
    @FXML private TextField name;
    @FXML private DialogPane selectDate;
    @FXML private ListView<String> listViewOfDateBirth;
    @FXML private DialogPane selectSeason;
    @FXML private ListView<String> listSeasons;
    @FXML private Label label;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label label6;
    @FXML private Label label7;
    @FXML private Label label8;
    @FXML private TableView resultTable;

    ObservableList<String> kindOfData = FXCollections.observableArrayList
            ("Player's achievements", "List of top 10 shooters", "List of team players", "Timetable");


    //visibility
    private void setVisibleDateOfBirth(){
        selectDate.setVisible(true);
        listViewOfDateBirth.setVisible(true);
    }

    private void setInisibleDateOfBirth(){
        selectDate.setVisible(false);
        listViewOfDateBirth.setVisible(false);
    }

    private void setVisiblePlayersAchievements(){
        writeName.setVisible(true);
        writeSurname.setVisible(true);
        name.setVisible(true);
        surname.setVisible(true);
    }

    private void setInvisiblePlayersAchievements(){
        writeName.setVisible(false);
        writeSurname.setVisible(false);
        name.setVisible(false);
        surname.setVisible(false);
    }

    private void setVisibleSeasons(){
        listSeasons.setVisible(true);
        selectSeason.setVisible(true);
        label.setVisible(true);
    }

    private void setInvisibleSeasons(){
        listSeasons.setVisible(false);
        selectSeason.setVisible(false);
        label.setVisible(false);
    }

    //clear
    private void clearLabelAndList(){
        label.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");

    }

    //changing screen
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


    //first option - Player's achievements
    public void selectPlayers(){
        PlayersService playersService = new PlayersService();
        MatchesService matchesService = new MatchesService();

        clearLabelAndList();

        //list of players
        List<Zawodnicy> players = playersService.getPlayers(name.getText(), surname.getText());

        //if no one was found
        if(players.size()==0){
             //alert if all data is not available
             if(name.getText().length() == 0 || surname.getText().length() == 0){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Warning");
                 alert.setHeaderText("Wrong Data");
                 alert.setContentText("You have to enter all data");
                 alert.showAndWait();
             }else{
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Warning");
                 alert.setHeaderText("No found a player");
                 alert.setContentText("Player is not exist");
                 alert.showAndWait();
             }

        }


        //if is more people with the same name and surname
        if(players.size() > 1){
            //TO DO
            setVisibleDateOfBirth();
            for(int i = 0; i<players.size(); i++){
                listViewOfDateBirth.getItems().addAll(players.get(i).getDataUr());
            }
            setInisibleDateOfBirth();
        }


        int idPlayer = players.get(0).getId(); //   ObservableList<HistoriaDruzynZawodnika> playerSeasons = FXCollections.observableArrayList(PlayerTeamsHistoryService.getPlayerSeasons(id));
        final int[] idSeason = {-1};
        final int[] idTeam = {-1};

        //choice season
        setVisibleSeasons();
        List<HistoriaDruzynZawodnika> playerSeasons = playersService.getPlayerTeamsHistory(idPlayer);


        List<String> nameSeason = new ArrayList<String>(playerSeasons.size());
        for(HistoriaDruzynZawodnika nameS : playerSeasons){
            nameSeason.add(nameS.getSezon().getNazwa());
            System.out.println(nameSeason);
        }

        ObservableList<String> oNameSeason = FXCollections.observableArrayList(nameSeason);
        listSeasons.setItems(oNameSeason);
        listSeasons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        listSeasons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                label.setText("Season: " + newValue);
                label2.setText("Name: " + players.get(0).getImie()); //
                label3.setText("Surname: " + players.get(0).getNazwisko());

                for(int i =0; i<playerSeasons.size(); i++){
                    if( newValue.equalsIgnoreCase(playerSeasons.get(i).getSezon().getNazwa())) {
                        label4.setText("Team: " + playerSeasons.get(i).getDruzyna().getNazwa());
                        idSeason[0] = playerSeasons.get(i).getSezon().getId();
                        idTeam[0] =playerSeasons.get(i).getDruzyna().getId();
                    }
                }


                List<OsiagnieciaZawWMeczu> achievementPlayer = matchesService.getAchievementPlayerInMatch(idPlayer, idSeason[0], idTeam[0] );
                label5.setText("Number matches: " + achievementPlayer.size());

            }
        });








    }

    public void selectListTopTenSchooters(){

    }

    public void selectlistOfTeamPlayers(){

    }

    public void selectTimetable() {

    }


    public void whatIsVisible(){
        switch(comboBox.getValue()) {
            case "Player's achievements":
                setVisiblePlayersAchievements();
                break;

            case "List of top 10 shooters":
                setInvisiblePlayersAchievements();
                break;

            case "List of team players":
                setInvisiblePlayersAchievements();
                break;

            case "Timetable":
                setInvisiblePlayersAchievements();
                break;
        }
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
        setInisibleDateOfBirth();
        setInvisiblePlayersAchievements();
        setInvisibleSeasons();
    }




}
