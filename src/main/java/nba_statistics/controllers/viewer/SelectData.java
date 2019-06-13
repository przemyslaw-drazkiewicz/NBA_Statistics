package nba_statistics.controllers.viewer;

import javafx.beans.property.ReadOnlyStringWrapper;
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
import nba_statistics.controllers.AccountController;
import nba_statistics.entities.*;
//import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByValue;
import static nba_statistics.others.Alerts.*;

public class SelectData implements Initializable {



    List<Players> players = new ArrayList<>();

    //main text
    @FXML
    private Button logOutBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button viewDatabaseBtn;


    @FXML
    private Button OfDateBirthbtt;
    @FXML
    private Text writeName;
    @FXML
    private Text writeSurname;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private Text selectDate;
    @FXML
    private ListView<String> listViewOfDateBirth;
    @FXML
    private ComboBox<String> comboBoxOfDateBirth;
    @FXML
    private Text selectSeason;
    @FXML
    private ListView<String> listSeasons;
    @FXML
    private ListView<String> playerAchievListView;
    @FXML
    private ImageView image;


    //visibility Achievements
    private void setVisibleDateOfBirth() {
        selectDate.setVisible(true);
       // listViewOfDateBirth.setVisible(true);
        comboBoxOfDateBirth.setVisible(true);
        OfDateBirthbtt.setVisible(true);
    }

    private void setInisibleDateOfBirth() {
        selectDate.setVisible(false);
       // listViewOfDateBirth.setVisible(false);
        comboBoxOfDateBirth.setVisible(false);
        OfDateBirthbtt.setVisible(false);
        comboBoxOfDateBirth.getSelectionModel().clearSelection();
    }

    private void setVisibleSeasons() {
        listSeasons.setVisible(true);
        selectSeason.setVisible(true);
    }

    private void setInvisibleSeasons() {
        listSeasons.setVisible(false);
        selectSeason.setVisible(false);
    }

    private void setVisibleLabelsPlayerAchievements() {
        playerAchievListView.setVisible(true);
        image.setVisible(true);

    }

    private void setInvisibleLabelsPlayerAchievements() {
        playerAchievListView.setVisible(false);
        image.setVisible(false);
    }


    private void setInvisiblePlayersAchievements() {
        setInvisibleSeasons();
        setInisibleDateOfBirth();
        setInvisibleLabelsPlayerAchievements();
    }


    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }


    //first option - Player's achievements
    public void selectPlayers() {
        PlayersService playersService = new PlayersService();

        listSeasons.getSelectionModel().clearSelection();
        setInvisibleLabelsPlayerAchievements();
        setInvisibleSeasons();

        //list of players
        if(players.size() > 0)        players.clear();
         players = playersService.getPlayers(name.getText(), surname.getText());

        //if no one was found
        if (players.size() == 0) {
            //alert if all data is not available
            if (name.getText().length() == 0 || surname.getText().length() == 0) {
                getAlertPlayer();
            } else {
                getAlertNoPlayer();
            }

        }//if is more people with the same name and surname
        else if (players.size() > 1) {
            //TO DO
            setVisibleDateOfBirth();

            comboBoxOfDateBirth.getSelectionModel().clearSelection();
            IntStream.range(0, players.size()).forEach(i -> comboBoxOfDateBirth.getItems().addAll(players.get(i).getDateOfBirth()));

        } else {
            dataPlayer();
        }

    }

    public void selectDateBtt(){
       String s = comboBoxOfDateBirth.getValue();
        System.out.println(s);
        setInisibleDateOfBirth();

        List<Players> playersTmp = new ArrayList<>();


        for(Players p: players) {
            playersTmp.add(p);
        }

        players.clear();

        for(Players p: playersTmp) {
            if (p.getDateOfBirth().equalsIgnoreCase(s)){
                players.add(p);
            }

        }

        playersTmp.clear();

        dataPlayer();
    }


    public void dataPlayer(){
        {

            PlayersService playersService = new PlayersService();

            int idPlayer = players.get(0).getId();

            List<PlayerTeamsHistory> playerSeasons = playersService.getPlayerTeamsHistory(idPlayer);
            ObservableList<PlayerTeamsHistory> nameTmp = FXCollections.observableArrayList();

            List<String> nameSeason = new ArrayList<String>(playerSeasons.size());
            for (PlayerTeamsHistory nameS : playerSeasons) {
                nameSeason.add(nameS.getSeason().getName());
                nameTmp.add(nameS);
            }

            if (!nameSeason.isEmpty()) {
                listener( playerSeasons);

                ObservableList<String> oNameSeason = FXCollections.observableArrayList(nameSeason);
                listSeasons.setItems(oNameSeason);
                listSeasons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            } else {
                setInvisibleSeasons();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("No seasons");
                alert.setContentText("No seasons for this player");
                alert.showAndWait();

            }
        }
    }

    private void listener(List<PlayerTeamsHistory> playerSeasons) {
        MatchesService matchesService = new MatchesService();
        final int[] idSeason = {-1};
        final int[] idTeam = {-1};

        setVisibleSeasons();

        ObservableList<String> listString = FXCollections.observableArrayList();

        listSeasons.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue == null) listener( playerSeasons);
            listString.add("Season: " + newValue);
            listString.add("Name: " + players.get(0).getName()); //
            listString.add("Surname: " + players.get(0).getSurname());

            for (int i = 0; i < playerSeasons.size(); i++) {
                if (newValue != null) {
                    if (newValue.equalsIgnoreCase(playerSeasons.get(i).getSeason().getName())) {
                        listString.add("Team: " + playerSeasons.get(i).getTeam().getName());
                        idSeason[0] = playerSeasons.get(i).getSeason().getId();
                        idTeam[0] = playerSeasons.get(i).getTeam().getId();
                    }
                }
            }


            List<PlayerMatchAchievements> achievementPlayer = matchesService.getAchievementPlayerInMatch(players.get(0).getId(), idSeason[0], idTeam[0]);
            listString.add("Number matches: " + achievementPlayer.size());


            int points = 0, steals = 0, blocks = 0, rebounds = 0, fouls = 0, techFaul = 0;

            for (PlayerMatchAchievements os : achievementPlayer) {
                points += os.getScoredPoints();
                steals += os.getSteals();
                blocks += os.getBlocks();
                rebounds += os.getRebounds();
                fouls += os.getFouls();
                techFaul += os.getTechnicalFouls();
            }

            listString.add("Points earned: " + points);
            listString.add("Steals: " + steals);
            listString.add("Blocks: " + blocks);
            listString.add("Rebounds: " + rebounds);
            listString.add("Fouls: " + fouls);
            listString.add("Technical fouls: " + techFaul);
            image.setImage(new Image(players.get(0).getImageURL()));

            playerAchievListView.setItems(listString);
            setVisibleLabelsPlayerAchievements();


        });
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setInisibleDateOfBirth();
        setInvisiblePlayersAchievements();
        setInvisibleSeasons();
        setInvisibleLabelsPlayerAchievements();


    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            selectPlayers();
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            try {
                changeScreenBack(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
