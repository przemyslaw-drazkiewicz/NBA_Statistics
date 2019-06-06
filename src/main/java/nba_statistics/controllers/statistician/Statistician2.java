package nba_statistics.controllers.statistician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.controllers.AccountController;
import nba_statistics.entities.Matches;
import nba_statistics.entities.PlayerMatchAchievements;
import nba_statistics.entities.Players;
import nba_statistics.entities.Teams;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayerMatchAchievementsService;
import nba_statistics.services.PlayersService;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static nba_statistics.others.Alerts.*;

public class Statistician2 implements Initializable {


    @FXML private Button buttonBack;
    @FXML private ListView homeTeamChoice;
    @FXML private ListView awayTeamChoice;
    @FXML private ListView homeTeamFive;
    @FXML private ListView awayTeamFive;
    @FXML private Text homeTeamName;
    @FXML private Text awayTeamName;
    @FXML private Button confirmSquad;
    @FXML private Button addHT;
    @FXML private Button remHT;
    @FXML private Button addAT;
    @FXML private Button remAT;
    @FXML private Text listPlayers;
    @FXML private Text firstSquad;

    private List<String> homeTeamSquad;
    private List<String> awayTeamSquad;
    private List<String> playersListTeamH;
    private List<String> playersListTeamA;

    private List<Players> playersTeamH;
    private List<Players> playersTeamA;

    private static Matches match;

    private Date date = new Date();

    private List<Matches> matches;

    private String matchT2;

    private ObservableList<String> playersSquadA = FXCollections.observableArrayList();
    private ObservableList<String> playersSquadH = FXCollections.observableArrayList();

    public void setMatch(Matches match) {
        this.match = match;
    }
    public void changeScreenBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician.fxml"));
        Parent accountParent = loader.load();
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

        public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician3.fxml"));
        Parent accountParent = loader.load();
        Statistician3 controller = loader.getController();
        controller.setPlayersSquadA(playersSquadA);
        controller.setPlayersSquadH(playersSquadH);
        controller.setPlayersTeamH(playersTeamH);
        controller.setPlayersTeamA(playersTeamA);
        controller.setMatch(match);
        controller.setMatchT2(matchT2);
        controller.init();
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }


    public void onClickButtonConfirm(Event event) {

        if(homeTeamSquad.size()!=5 || awayTeamSquad.size()!=5 )
        {
            getAlertChoiceSquad();
        }
        else {

            Teams teamH = match.getHostTeam();
            Teams teamA = match.getGuestTeam();

            for(String playersH : homeTeamSquad)
            {
                playersSquadH.add(playersH);
            }
            for(String playersA : awayTeamSquad)
            {
                playersSquadA.add(playersA);
            }

            matchT2 = teamH.getName() + " vs. " + teamA.getName();

            try {
                changeScreen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressWarnings("Duplicates")
    public void onClickButtonAddH() {
        String player =(String) homeTeamChoice.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Add");
        }
        else if(homeTeamSquad.size()==5)
        {
            getAlertTooManyPlayersInSquad();
        }
        else{
            homeTeamSquad.add(player);
            playersListTeamH.remove(player);
            ObservableList<String> playersSquadH = FXCollections.observableArrayList();

            for (String playersH : homeTeamSquad) {
                playersSquadH.add(playersH);
            }

            ObservableList<String> playersListH = FXCollections.observableArrayList();

            for (String playersH : playersListTeamH) {
                playersListH.add(playersH);
            }

            homeTeamChoice.setItems(playersListH);
            homeTeamFive.setItems(playersSquadH);
        }
    }

    public void onClickButtonRemH() {
        String player =(String) homeTeamFive.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Remove");
        }
        else {
            homeTeamSquad.remove(player);
            playersListTeamH.add(player);
            ObservableList<String> playersSquadH = FXCollections.observableArrayList();

            for (String playersH : homeTeamSquad) {
                playersSquadH.add(playersH);
            }

            ObservableList<String> playersListH = FXCollections.observableArrayList();

            for (String playersH : playersListTeamH) {
                playersListH.add(playersH);
            }

            homeTeamChoice.setItems(playersListH);
            homeTeamFive.setItems(playersSquadH);
        }
    }

    public void onClickButtonAddA() {
        String player =(String) awayTeamChoice.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Add");
        }
        else if(awayTeamSquad.size()==5)
        {
            getAlertTooManyPlayersInSquad();
        }
        else {
            awayTeamSquad.add(player);
            playersListTeamA.remove(player);
            ObservableList<String> playersSquadA = FXCollections.observableArrayList();

            for (String playersA : awayTeamSquad) {
                playersSquadA.add(playersA);
            }

            ObservableList<String> playersListA = FXCollections.observableArrayList();

            for (String playersA : playersListTeamA) {
                playersListA.add(playersA);
            }

            awayTeamChoice.setItems(playersListA);
            awayTeamFive.setItems(playersSquadA);
        }
    }

    public void onClickButtonRemA() {
        String player =(String) awayTeamFive.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Remove");
        }
        else {
            awayTeamSquad.remove(player);
            playersListTeamA.add(player);
            ObservableList<String> playersSquadA = FXCollections.observableArrayList();

            for (String playersA : awayTeamSquad) {
                playersSquadA.add(playersA);
            }

            ObservableList<String> playersListA = FXCollections.observableArrayList();

            for (String playersA : playersListTeamA) {
                playersListA.add(playersA);
            }

            awayTeamChoice.setItems(playersListA);
            awayTeamFive.setItems(playersSquadA);
        }
    }

    public void init(){
        System.out.println("Init = " + match);
        homeTeamSquad = new ArrayList<>();
        awayTeamSquad = new ArrayList<>();
        playersListTeamH = new ArrayList<>();
        playersListTeamA = new ArrayList<>();
        Teams teamH = match.getHostTeam();
        Teams teamA = match.getGuestTeam();
        int id_TeamH = teamH.getId();
        int id_TeamA = teamA.getId();

        PlayersService playersDAO = new PlayersService();
        playersTeamH = playersDAO.getPlayers(id_TeamH);
        playersTeamA = playersDAO.getPlayers(id_TeamA);
        ObservableList<String> playersA = FXCollections.observableArrayList();
        ObservableList<String> playersH = FXCollections.observableArrayList();

        for (Players players : playersTeamH) {
            String player = players.getName() + " " + players.getSurname();
            playersListTeamH.add(player);
            playersH.add(player);
        }

        for (Players players : playersTeamA) {
            String player = players.getName() + " " + players.getSurname();
            playersListTeamA.add(player);
            playersA.add(player);
        }
        homeTeamChoice.setItems(playersH);
        awayTeamChoice.setItems(playersA);
        homeTeamName.setText(teamH.getName());
        awayTeamName.setText(teamA.getName());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(match + " initialize");

//        v = new Visibility();
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onClickButtonConfirm(event);
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
