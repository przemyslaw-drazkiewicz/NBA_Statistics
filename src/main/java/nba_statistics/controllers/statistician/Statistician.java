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
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import nba_statistics.entities.Zawodnicy;
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

    private ObservableList<String> choice;

    private Mecze match;

    private Visibility v;

    private Date date = new Date();

    private List<Mecze> matchs;


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
            match = matchs.get(matchSelected);

            Druzyny teamH = match.getDruzGosp();
            Druzyny teamA = match.getDruzGosc();
            int id_TeamH = teamH.getId();
            int id_TeamA = teamA.getId();

            PlayersService playersDAO = new PlayersService();
            List<Zawodnicy> playersTeamH = playersDAO.getPlayers(id_TeamH);
            List<Zawodnicy> playersTeamA = playersDAO.getPlayers(id_TeamA);
            ObservableList<String> playersA = FXCollections.observableArrayList();
            ObservableList<String> playersH = FXCollections.observableArrayList();

            for (Zawodnicy players : playersTeamH) {
                String player = players.getImie() + " " + players.getNazwisko();
                playersH.add(player);
            }

            for (Zawodnicy players : playersTeamA) {
                String player = players.getImie() + " " + players.getNazwisko();
                playersA.add(player);
            }
            homeTeamChoice.setItems(playersH);
            awayTeamChoice.setItems(playersA);
            homeTeamName.setText(teamH.getNazwa());
            awayTeamName.setText(teamA.getNazwa());


            v.setInvisibleC(choiceText, buttonOK, matchesComboBox);
            v.setVisibleS(homeTeamChoice,awayTeamChoice,homeTeamFive,awayTeamFive,homeTeamName,awayTeamName,confirmSquad,addHT,remHT,addAT,remAT,listPlayers,firstSquad);
            buttonBack.setVisible(true);
        }

    }
    public void onClickButtonConfirm() {

        if(homeTeamSquad.size()!=5 || awayTeamSquad.size()!=5 )
        {
            getAlertChoiceMatch();
        }
        else {

            Druzyny teamH = match.getDruzGosp();
            Druzyny teamA = match.getDruzGosc();
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

            String matchT2 = teamH.getNazwa() + " vs. " + teamA.getNazwa();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        v = new Visibility();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);

        MatchesService matchesDao = new MatchesService();
        matchs = matchesDao.findAllAtDate(dateS);

        matchesT = new ArrayList<>();

        for (Mecze mecze : matchs) {
            match = mecze;

            Druzyny teamH = match.getDruzGosp();
            Druzyny teamA = match.getDruzGosc();

            String matchT = teamH.getNazwa() + " vs. " + teamA.getNazwa();
            matchesT.add(matchT);
        }
/*
        PlayersService playersDao = new PlayersService();

        List<Zawodnicy> playersTeam = playersDao.getPlayers(15);
        ObservableList<String> players = FXCollections.observableArrayList();

        for (Zawodnicy playersBuffer : playersTeam) {
            String player = playersBuffer.getImie() + " " + playersBuffer.getNazwisko();
            players.add(player);
        }
         homeTeamChoice.setItems(players);
*/
        choice = FXCollections.observableArrayList
                (matchesT);
        matchesComboBox.setItems(choice);

    }
}
