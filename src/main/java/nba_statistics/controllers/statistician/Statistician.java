package nba_statistics.controllers.statistician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import nba_statistics.entities.Matches;
import nba_statistics.entities.Players;
import nba_statistics.entities.Teams;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;

import static nba_statistics.others.Alerts.*;

public class Statistician implements Initializable {

    @FXML private ComboBox<String> matchesComboBox;
    @FXML private Text choiceText;
    @FXML private Text matchText;
    @FXML private Button buttonOK;
    @FXML private Button buttonBack;
    @FXML private ListView homeTeam;
    @FXML private ListView awayTeam;
    @FXML private Button oneH;
    @FXML private Button twoH;
    @FXML private Button threeH;
    @FXML private Button oneA;
    @FXML private Button twoA;
    @FXML private Button threeA;
    @FXML private Button BH;
    @FXML private Button BA;
    @FXML private Button RH;
    @FXML private Button RA;
    @FXML private Button FH;
    @FXML private Button FA;
    @FXML private Button TFH;
    @FXML private Button TFA;
    @FXML private Button SH;
    @FXML private Button SA;
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



    private List<String> matchesT;

    private List<String> homeTeamSquad;
    private List<String> awayTeamSquad;
    private List<String> playersListTeamH;
    private List<String> playersListTeamA;

    private ObservableList<String> choice;

    private Matches match;

    private Visibility v;

    private Date date = new Date();

    private List<Matches> matchs;


    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void onClickButtonOK() {
        int matchSelected = -1;
        String matchT = matchesComboBox.getValue();
        for(int i=0;i<matchesT.size();i++)
        {
            if(matchesT.get(i).equals(matchT))
            {
                matchSelected=i;
            }
        }
        if(matchSelected==-1)
        {
            getAlertChoiceMatch();
        }
        else {
            homeTeamSquad = new ArrayList<>();
            awayTeamSquad = new ArrayList<>();
            playersListTeamH = new ArrayList<>();
            playersListTeamA = new ArrayList<>();
            match = matchs.get(matchSelected);

            Teams teamH = match.getHostTeam();
            Teams teamA = match.getGuestTeam();
            int id_TeamH = teamH.getId();
            int id_TeamA = teamA.getId();

            PlayersService playersDAO = new PlayersService();
            List<Players> playersTeamH = playersDAO.getPlayers(id_TeamH);
            List<Players> playersTeamA = playersDAO.getPlayers(id_TeamA);
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


            v.setInvisibleC(choiceText, buttonOK, matchesComboBox);
            v.setVisibleS(homeTeamChoice,awayTeamChoice,homeTeamFive,awayTeamFive,homeTeamName,awayTeamName,confirmSquad,addHT,remHT,addAT,remAT,listPlayers,firstSquad);
            buttonBack.setVisible(true);
        }

    }
    public void onClickButtonConfirm() {

        if(homeTeamSquad.size()!=5 || awayTeamSquad.size()!=5 )
        {
            getAlertChoiceSquad();
        }
        else {

            Teams teamH = match.getHostTeam();
            Teams teamA = match.getGuestTeam();
            ObservableList<String> playersSquadA = FXCollections.observableArrayList();
            ObservableList<String> playersSquadH = FXCollections.observableArrayList();

            for(String playersH : homeTeamSquad)
            {
                playersSquadH.add(playersH);
            }
            for(String playersA : awayTeamSquad)
            {
                playersSquadA.add(playersA);
            }

            String matchT2 = teamH.getName() + " vs. " + teamA.getName();
            matchText.setText(matchT2);
            homeTeam.setItems(playersSquadH);
            awayTeam.setItems(playersSquadA);

            v.setInvisibleC(choiceText, buttonOK, matchesComboBox);
            v.setInvisibleS(homeTeamChoice,awayTeamChoice,homeTeamFive,awayTeamFive,homeTeamName,awayTeamName,confirmSquad,addHT,remHT,addAT,remAT,listPlayers,firstSquad);
            v.setVisibleM(oneH,twoH,threeH,oneA,twoA,threeA,BH,BA,RH,RA,FH,FA,TFH,TFA,SH,SA,homeTeam,awayTeam,buttonBack,matchText);

        }
    }

    public void onClickButtonBack() {
        v.setVisibleC(choiceText,buttonOK,matchesComboBox);
        v.setInvisibleS(homeTeamChoice,awayTeamChoice,homeTeamFive,awayTeamFive,homeTeamName,awayTeamName,confirmSquad,addHT,remHT,addAT,remAT,listPlayers,firstSquad);
        v.setInvisibleM(oneH,twoH,threeH,oneA,twoA,threeA,BH,BA,RH,RA,FH,FA,TFH,TFA,SH,SA,homeTeam,awayTeam,buttonBack,matchText);
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        v = new Visibility();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);

        MatchesService matchesDao = new MatchesService();
        matchs = matchesDao.findAllAtDate(dateS);

        matchesT = new ArrayList<>();

        for (Matches matches : matchs) {
            match = matches;

            Teams teamH = match.getHostTeam();
            Teams teamA = match.getGuestTeam();

            String matchT = teamH.getName() + " vs. " + teamA.getName();
            matchesT.add(matchT);
        }
/*
        PlayersService playersDao = new PlayersService();

        List<Players> playersTeam = playersDao.getPlayers(15);
        ObservableList<String> players = FXCollections.observableArrayList();

        for (Players playersBuffer : playersTeam) {
            String player = playersBuffer.getName() + " " + playersBuffer.getSurname();
            players.add(player);
        }
         homeTeamChoice.setItems(players);
*/
        choice = FXCollections.observableArrayList
                (matchesT);
        matchesComboBox.setItems(choice);

    }
}
