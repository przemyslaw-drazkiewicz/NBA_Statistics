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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.text.Text;
import nba_statistics.controllers.AccountController;
import nba_statistics.entities.*;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayerMatchAchievementsService;
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
    @FXML private Button StH;
    @FXML private Button StA;
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
    //TIMER
    @FXML private Text sec;
    @FXML private Text min;
    @FXML private Text colon;



    private List<String> matchesT;

    private List<String> homeTeamSquad;
    private List<String> awayTeamSquad;
    private List<String> playersListTeamH;
    private List<String> playersListTeamA;

    private List<Players> playersTeamH;
    private List<Players> playersTeamA;

    private ObservableList<String> choice;

    private Matches match;

    private Visibility v;

    private Date date = new Date();

    private List<Matches> matches;

    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = loader.load();
        AccountController accountController = loader.getController();
        accountController.init(AccountController.getUser());
       // AccountController accountController = AccountController.getInstance();
        //loader.setController(accountController);
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }
    private boolean isStart = false;


    private int findPosPlayerHome(String player){
        int i = 0;
        for(PlayerMatchAchievements p : home){
            if ((p.getPlayer().getName() + " " + p.getPlayer().getSurname()).equals(player)){
                return i;
            }
            i++;
        }
        return -1; // :((( but... NOT POSSIBLE ;)
    }
    public void oneH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if(player!=null) //player is not selected
            home.get(findPosPlayerHome(player)).addPoints(1);
    }
    public void twoH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addPoints(2);
    }
    public void threeH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addPoints(3);
    }
    public void stealsH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addSteal();
    }
    public void blockH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addBlock();
    }
    public void reboundH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addRebound();
    }
    public void foulH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addFoul();
    }
    public void techFoulH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            home.get(findPosPlayerHome(player)).addTechFoul();
    }

    private int findPosPlayerAway(String player){
        int i =0;
        for(PlayerMatchAchievements p : away){
            if ((p.getPlayer().getName() + " " + p.getPlayer().getSurname()).equals(player)){
                return i;
            }
            i++;
        }
        return -1; // :((( but... NOT POSSIBLE ;)
    }
    public void oneA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if(player!=null) //player is not selected
            away.get(findPosPlayerAway(player)).addPoints(1);
    }
    public void twoA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addPoints(2);
    }
    public void threeA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addPoints(3);
    }
    public void stealsA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addSteal();
    }
    public void blockA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addBlock();
    }
    public void reboundA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addRebound();
    }
    public void foulA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addFoul();
    }
    public void techFoulA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            away.get(findPosPlayerAway(player)).addTechFoul();
    }

    private void finishedMatch(){
        toTesting();
        PlayerMatchAchievementsService playerMatchAchievementsService = new PlayerMatchAchievementsService();
        playerMatchAchievementsService.save(home);
        playerMatchAchievementsService.save(away);

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
            match = matches.get(matchSelected);
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
            v.setVisibleM(oneH,twoH,threeH,oneA,twoA,threeA,BH,BA,RH,RA,FH,FA,TFH,TFA,SH,SA,homeTeam,awayTeam,buttonBack,matchText,min,sec,colon, StH, StA);

        }
        initCollectAchievements();
    }
    public void onClickButtonBack() {
        v.setVisibleC(choiceText,buttonOK,matchesComboBox);
        v.setInvisibleS(homeTeamChoice,awayTeamChoice,homeTeamFive,awayTeamFive,homeTeamName,awayTeamName,confirmSquad,addHT,remHT,addAT,remAT,listPlayers,firstSquad);
        v.setInvisibleM(oneH,twoH,threeH,oneA,twoA,threeA,BH,BA,RH,RA,FH,FA,TFH,TFA,SH,SA,homeTeam,awayTeam,buttonBack,matchText,min,sec,colon,StH, StA);
    }


    private void toTesting(){
        System.out.println("=======================HOME=============================");
        for (PlayerMatchAchievements p : home){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
        System.out.println("======================AWAY===============================");
        for (PlayerMatchAchievements p : away){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
    }

    private ArrayList<PlayerMatchAchievements> home = new ArrayList<>();
    private ArrayList<PlayerMatchAchievements> away = new ArrayList<>();
    private void initCollectAchievements(){
        for (Players p: playersTeamH){
            home.add(new PlayerMatchAchievements(p, match));
        }
        for (Players p : playersTeamA){
            away.add(new PlayerMatchAchievements(p,match));
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

    //STOPPER
    private int seconds =0;
    private int minutes= 0;
    private Timer myTimer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (isStart) {
                seconds = Integer.parseInt(sec.getText());
                if (seconds != 59){
                    seconds++;
                }
                else{
                    seconds = 0;
                    minutes++;
                }
                if (seconds >= 10)
                    sec.setText(String.valueOf(seconds));
                else
                    sec.setText(0 + String.valueOf(seconds));
                if (minutes >= 10)
                    min.setText(String.valueOf(minutes));
                else
                    min.setText(0 + String.valueOf(minutes));
            }
            if (minutes == 0 && seconds == 20) { //MATCH FINISHED if time is 01:00 -> for testing
                finishedMatch();
/*                isStart = false;
                myTimer.cancel();*/

            }
        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        v = new Visibility();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);

        MatchesService matchesDao = new MatchesService();
        matches = matchesDao.findAllAtDate(dateS);

        matchesT = new ArrayList<>();

        for (Matches matches : matches) {
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
        choice = FXCollections.observableArrayList(matchesT);
        matchesComboBox.setItems(choice);

        myTimer.schedule(task,1000,1000);



    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.S) {
            isStart = !isStart;
        }
        if (event.getCode() == KeyCode.ENTER) {
            onClickButtonOK();        }
        if (event.getCode() == KeyCode.ESCAPE) {
            try{
                changeScreen(event);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
