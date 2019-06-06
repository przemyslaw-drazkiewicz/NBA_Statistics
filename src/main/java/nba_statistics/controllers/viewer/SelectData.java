package nba_statistics.controllers.viewer;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import nba_statistics.controllers.AccountController;
import nba_statistics.entities.*;
//import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.io.IOException;
import java.io.LineNumberInputStream;
import java.net.URL;
import java.util.*;

import static nba_statistics.others.Alerts.*;

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
    @FXML private Label label9;
    @FXML private Label label10;
    @FXML private Label label11;
    @FXML private TableView resultTable;

    //Top 10 schooters
    @FXML private ComboBox<String> comboBoxSeasons;

    //Timetable
    @FXML private ComboBox<String> comboBoxTeam;


    @FXML private TableView<Matches> matchesTable;
    @FXML private TableColumn<Matches, String> homeTeam;
    @FXML private TableColumn<Matches, String> awayTeam;
    @FXML private TableColumn<Matches, String> date;
    @FXML private TableColumn<Matches, Integer> homePoints;
    @FXML private TableColumn<Matches, Integer> awayPoints;
    @FXML private TableColumn<Matches, Integer> extraTime;
    @FXML private ImageView image;


    ObservableList<String> kindOfData = FXCollections.observableArrayList
            ("Player's achievements", "List of top 10 shooters", "List of team players", "Timetable");


    //visibility Achievements
    private void setVisibleDateOfBirth(){
        selectDate.setVisible(true);
        listViewOfDateBirth.setVisible(true);
    }

    private void setInisibleDateOfBirth(){
        selectDate.setVisible(false);
        listViewOfDateBirth.setVisible(false);
    }

    private void setVisibleSeasons(){
        listSeasons.setVisible(true);
        selectSeason.setVisible(true);
      //  label.setVisible(true);
    }

    private void setInvisibleSeasons(){
        listSeasons.setVisible(false);
        selectSeason.setVisible(false);
      //  label.setVisible(false);
    }

    private void setVisibleLabelsPlayerAchievements(){
        label.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);
        label9.setVisible(true);
        label10.setVisible(true);
        label11.setVisible(true);
        image.setVisible(true);

    }

    private void setInvisibleLabelsPlayerAchievements(){
        label.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        label8.setVisible(false);
        label9.setVisible(false);
        label10.setVisible(false);
        label11.setVisible(false);
        image.setVisible(true);



    }


    //visibility top 10 schooters
    private void setVisibleComboBoxSeasons(){
       comboBoxSeasons.setVisible(true);
    }
    private void setInvisibleComboBoxSeasons(){
        comboBoxSeasons.setVisible(false);
    }


    //main view
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
        setInvisibleSeasons();
        setInisibleDateOfBirth();
        setInvisibleLabelsPlayerAchievements();
    }

    private void setVisibleListTopTenShooters(){

        String name = "";
        SeasonsService seasonsService = new SeasonsService();

        List<Seasons> s  = seasonsService.getAllSeasons();

        List<String> nameSeasons = new ArrayList<String>(s.size());
        for(Seasons sez : s){
            nameSeasons.add(sez.getName());
        }



        if(nameSeasons.size() > 0){

            setVisibleComboBoxSeasons();
            ObservableList<String> oNameSeasons = FXCollections.observableArrayList(nameSeasons);
            comboBoxSeasons.setItems(oNameSeasons);



        }else{
            setInvisibleListTopTenShooters();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("No seasons");
            alert.setContentText("No seasons!");
            alert.showAndWait();
        }
    }

    private void setInvisibleListTopTenShooters(){
        setInvisibleComboBoxSeasons();
    }

    private void setVisibleTimetable(){
        comboBoxTeam.setVisible(true);
        comboBoxSeasons.setVisible(true);
    }

    private void setInvisibleTimetable(){
        comboBoxTeam.setVisible(false);
        comboBoxSeasons.setVisible(false);
        matchesTable.setVisible(false);
        comboBoxTeam.getSelectionModel().clearSelection();
        comboBoxSeasons.getSelectionModel().clearSelection();

    }

    //clear
    private void clearLabelAndList(){
        label.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
        label7.setText("");
        label8.setText("");
        label9.setText("");
        label10.setText("");
        label11.setText("");


    }

    //changing screen
    public void changeScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = (Parent)loader.load();
        AccountController accountController = loader.getController();
        accountController.init(AccountController.getUser());
        Scene parentScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }

    public void changeScreenBack(Event event) throws IOException {
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
        setInvisibleLabelsPlayerAchievements();
        setInvisibleSeasons();

        //list of players
        List<Players> players = playersService.getPlayers(name.getText(), surname.getText());

        //if no one was found
        if(players.size()==0){
             //alert if all data is not available
             if(name.getText().length() == 0 || surname.getText().length() == 0){
                /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Warning");
                 alert.setHeaderText("Wrong Data");
                 alert.setContentText("You have to enter all data");
                 alert.showAndWait();*/
                getAlertPlayer();
             }else{
                /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Warning");
                 alert.setHeaderText("No found a player");
                 alert.setContentText("Player is not exist");
                 alert.showAndWait();*/
                 getAlertNoPlayer();
             }

        }//if is more people with the same name and surname
        else if(players.size() > 1){
            //TO DO
            setVisibleDateOfBirth();
            for(int i = 0; i<players.size(); i++){
                listViewOfDateBirth.getItems().addAll(players.get(i).getDateOfBirth());
            }
            setInisibleDateOfBirth();
        }
        else{
            int idPlayer = players.get(0).getId(); //   ObservableList<PlayerTeamsHistory> playerSeasons = FXCollections.observableArrayList(PlayerTeamsHistoryService.getPlayerSeasons(id));
            final int[] idSeason = {-1};
            final int[] idTeam = {-1};

            //choice season
            setVisibleSeasons();
            List<PlayerTeamsHistory> playerSeasons = playersService.getPlayerTeamsHistory(idPlayer);

            List<String> nameSeason = new ArrayList<String>(playerSeasons.size());
            for(PlayerTeamsHistory nameS : playerSeasons){
                nameSeason.add(nameS.getSeason().getName());
            }

            if(!nameSeason.isEmpty()){

                ObservableList<String> oNameSeason = FXCollections.observableArrayList(nameSeason);
                listSeasons.setItems(oNameSeason);
                listSeasons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


                listSeasons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        label.setText("Season: " + newValue);
                        label2.setText("Name: " + players.get(0).getName()); //
                        label3.setText("Surname: " + players.get(0).getSurname());

                        for(int i =0; i<playerSeasons.size(); i++){
                            if( newValue.equalsIgnoreCase(playerSeasons.get(i).getSeason().getName())) {
                                label4.setText("Team: " + playerSeasons.get(i).getTeam().getName());
                                idSeason[0] = playerSeasons.get(i).getSeason().getId();
                                idTeam[0] =playerSeasons.get(i).getTeam().getId();
                            }
                        }


                        List<PlayerMatchAchievements> achievementPlayer = matchesService.getAchievementPlayerInMatch(idPlayer, idSeason[0], idTeam[0] );
                        label5.setText("Number matches: " + achievementPlayer.size());


                        int points = 0, steals = 0, blocks = 0, rebounds = 0, fouls = 0, techFaul = 0;

                        for(PlayerMatchAchievements os : achievementPlayer){
                            points += os.getScoredPoints();
                            steals += os.getSteals();
                            blocks += os.getBlocks();
                            rebounds += os.getRebounds();
                            fouls += os.getFouls();
                            techFaul += os.getTechnicalFouls();
                        }

                        label6.setText("Points earned: " + points);
                        label7.setText("Steals: " + steals);
                        label8.setText("Blocks: " + blocks);
                        label9.setText("Rebounds: " + rebounds);
                        label10.setText("Fouls: " + fouls);
                        label11.setText("Technical fouls: " + techFaul);
                        image.setImage(new Image(players.get(0).getImageURL()));
                        setVisibleLabelsPlayerAchievements();

                    }
                });
            } else{
                setInvisibleSeasons();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("No seasons");
                alert.setContentText("No seasons for this player");
                alert.showAndWait();

            }
        }







    }

    public void selectListTopTenSchooters(){

        SeasonsService seasonsService = new SeasonsService();
        MatchesService matchesService = new MatchesService();

        String nameSeason = null;
        System.out.println(comboBoxSeasons.getValue());
        nameSeason = comboBoxSeasons.getValue();

        Seasons season;
        season = seasonsService.getSeason(nameSeason);

        System.out.println(season.getId());

        List<PlayerMatchAchievements> playerMatchAchievements = matchesService.getAchievementsPlayersInSeason(season.getId());

    }

    public void selectlistOfTeamPlayers(){

    }

    private void setTable(List<Matches> listMatches){

        if (listMatches.isEmpty()){
            getAlertEmptyListOfMatches();
        }
        else {
            matchesTable.setVisible(true);
            ObservableList<Matches> matchesObservableList = FXCollections.observableArrayList();
/*            for (Matches m : listMatches) {
                matchesObservableList.add(m);
            }*/
            matchesObservableList.addAll(listMatches);
            matchesTable.setItems(matchesObservableList);
        }
    }
    public void selectTimetable() {
        String team, season;
        matchesTable.setVisible(false);
        if ((season = comboBoxSeasons.getValue()) == null)
            getAlertComboBoxSeason();
        else if ((team = comboBoxTeam.getValue()) == null){
            getAlertComboBoxTeam();
        }else{
            MatchesService matchesService = new MatchesService();
            setTable(matchesService.getMatches(team, season));
        }



    }

    private void initComboBoxSeasons(){
        SeasonsService seasonsService = new SeasonsService();
        ArrayList<String> seasons = seasonsService.getAllSeasonsName();
        comboBoxSeasons.setItems(FXCollections.observableArrayList(seasons));
    }
    private void initComboBoxTeams(){
        TeamsService teamsService = new TeamsService();
        ArrayList<String> teams = teamsService.getAllTeams();
        comboBoxTeam.setItems(FXCollections.observableList(teams));
    }

    public void whatIsVisible(){
        switch(comboBox.getValue()) {
            case "Player's achievements":
                setVisiblePlayersAchievements();
                setInvisibleListTopTenShooters();
                setInvisibleTimetable();
                break;

            case "List of top 10 shooters":
                setInvisiblePlayersAchievements();
                setInvisibleTimetable();
                setVisibleListTopTenShooters();
                break;

            case "List of team players":
                setInvisiblePlayersAchievements();
                setInvisibleListTopTenShooters();
                setInvisibleTimetable();
                break;

            case "Timetable":
                setInvisiblePlayersAchievements();
                setInvisibleListTopTenShooters();
                setVisibleTimetable();
                initComboBoxTeams();
                initComboBoxSeasons();
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
        setInvisibleListTopTenShooters();
        setInvisibleSeasons();
        setInvisibleLabelsPlayerAchievements();
        setInvisibleTimetable();

        //init column in table view -> to TIMETABLE
        homeTeam.setCellValueFactory(p ->  new ReadOnlyStringWrapper(p.getValue().getHostTeam().getName()));
        awayTeam.setCellValueFactory(p ->  new ReadOnlyStringWrapper(p.getValue().getGuestTeam().getName()));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        homePoints.setCellValueFactory(new PropertyValueFactory<>("hostPoints"));
        awayPoints.setCellValueFactory(new PropertyValueFactory<>("guestPoints"));
        extraTime.setCellValueFactory(new PropertyValueFactory<>("extraTimeCount"));
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
                whatKindOfDataIsSelected();
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            try{
                changeScreenBack(event);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}
