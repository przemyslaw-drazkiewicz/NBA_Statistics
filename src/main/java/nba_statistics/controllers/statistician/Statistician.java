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

    private List<Matches> matchs;

    public void changeScreen(ActionEvent event) throws IOException {
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

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.S) {
            isStart = !isStart;
        }
    }

    private int findPosPlayerHome(String player){
        int i =0;
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
            achievementsHome[findPosPlayerHome(player)][0] +=1;
    }
    public void twoH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            achievementsHome[findPosPlayerHome(player)][0] +=2;
    }
    public void threeH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][0] +=3;
    }
    public void stealsH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][1]+=1;
    }
    public void blockH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][2]+=1;
    }
    public void reboundH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][3]+=1;
    }
    public void foulH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][4]+=1;
    }
    public void techFoulH(){
        String player =(String) homeTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsHome[findPosPlayerHome(player)][5]+=1;
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
            achievementsAway[findPosPlayerAway(player)][0] +=1;
    }
    public void twoA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            achievementsAway[findPosPlayerAway(player)][0] +=2;
    }
    public void threeA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][0] +=3;
    }
    public void stealsA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][1]+=1;
    }
    public void blockA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][2]+=1;
    }
    public void reboundA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][3]+=1;
    }
    public void foulA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][4]+=1;
    }
    public void techFoulA(){
        String player =(String) awayTeam.getSelectionModel().getSelectedItem();
        if (player!=null)
            achievementsAway[findPosPlayerAway(player)][5]+=1;
    }

    private void finishedMatch(){
        setAchievementsToLists();
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
            match = matchs.get(matchSelected);
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

    @SuppressWarnings("Duplicates")
    private void setAchievementsToLists(){
        int i=0;
        for (PlayerMatchAchievements p : home){
            p.setScoredPoints(achievementsHome[i][0]);
            p.setSteals(achievementsHome[i][1]);
            p.setBlocks(achievementsHome[i][2]);
            p.setRebounds(achievementsHome[i][3]);
            p.setFouls(achievementsHome[i][4]);
            p.setTechnicalFouls(achievementsHome[i][5]);
            i++;
        }
        i =0;
        for (PlayerMatchAchievements p : away){
            p.setScoredPoints(achievementsAway[i][0]);
            p.setSteals(achievementsAway[i][1]);
            p.setBlocks(achievementsAway[i][2]);
            p.setRebounds(achievementsAway[i][3]);
            p.setFouls(achievementsAway[i][4]);
            p.setTechnicalFouls(achievementsAway[i][5]);
            i++;
        }
    }
    private void toTesting(){
        System.out.println("=======================HOME=============================");
        for (PlayerMatchAchievements p : home){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
        System.out.println("======================AWAY===============================");
        for (PlayerMatchAchievements p : away){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
    }

    private int[][] achievementsHome; //table with achievements -> x is 'id' player, y-> is points, blocks etc
    private int[][] achievementsAway;
    private ArrayList<PlayerMatchAchievements> home = new ArrayList<>();
    private ArrayList<PlayerMatchAchievements> away = new ArrayList<>();
    private void initCollectAchievements(){
        for (Players p: playersTeamH){
            home.add(new PlayerMatchAchievements(p, match));
        }
        for (Players p : playersTeamA){
            away.add(new PlayerMatchAchievements(p,match));
        }
        achievementsHome = new int[playersTeamH.size()][6]; //6 because collecting points, blocks, rebounds, fouls and tech fouls
        achievementsAway = new int[playersTeamA.size()][6];

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
            if (minutes == 1 && seconds == 0) { //MATCH FINISHED if time is 01:00 -> for testing
                finishedMatch();

            }
        }
    };


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
        choice = FXCollections.observableArrayList(matchesT);
        matchesComboBox.setItems(choice);

        myTimer.schedule(task,1000,1000);



    }
}
