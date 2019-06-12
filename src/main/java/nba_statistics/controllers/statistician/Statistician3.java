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

public class Statistician3 implements Initializable {


    @FXML private Text matchText;
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
    //TIMER
    @FXML private Text sec;
    @FXML private Text min;
    @FXML private Text colon;

    private List<Players> playersTeamH;
    private List<Players> playersTeamA;

    private Matches match;

    private String matchT2;

    private int extraTimeCount = 0;

    private ObservableList<String> playersSquadA = FXCollections.observableArrayList();
    private ObservableList<String> playersSquadH = FXCollections.observableArrayList();
    private ArrayList<PlayerMatchAchievements> home = new ArrayList<>();
    private ArrayList<PlayerMatchAchievements> away = new ArrayList<>();


    public void setPlayersSquadA(ObservableList<String> playersSquadA) {
        this.playersSquadA = playersSquadA;
    }

    public void setPlayersSquadH(ObservableList<String> playersSquadH) {
        System.out.println("SET IN STAT3");
        this.playersSquadH = playersSquadH;
    }

    public void setMatchT2(String matchT2) {
        this.matchT2 = matchT2;
    }

    public void setPlayersTeamH(List<Players> playersTeamH) {
        this.playersTeamH = playersTeamH;
    }

    public void setPlayersTeamA(List<Players> playersTeamA) {
        this.playersTeamA = playersTeamA;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    public void changeScreenBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician2.fxml"));
        Parent accountParent = loader.load();
        Statistician2 controller = loader.getController();
        controller.setMatch(match);
        controller.init();
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

        match.setHostPoints(getPoints(home));
        match.setGuestPoints(getPoints(away));
        match.setExtraTimeCount(extraTimeCount);

        MatchesService matchesService = new MatchesService();
        matchesService.update(match);




    }
    private int getPoints(ArrayList<PlayerMatchAchievements> team){
        int points = 0;
        for (PlayerMatchAchievements p : team){
            points+=p.getScoredPoints();
        }
        return points;
    }



    private void toTesting(){
        System.out.println("=======================HOME=============================");
        for (PlayerMatchAchievements p : home){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
        System.out.println("======================AWAY===============================");
        for (PlayerMatchAchievements p : away){System.out.println(p.getPlayer().toString() + p.getMatch().toString() + p.toString());}
    }


    private void initCollectAchievements(){
        for (Players p: playersTeamH){
            home.add(new PlayerMatchAchievements(p, match));
        }
        for (Players p : playersTeamA){
            away.add(new PlayerMatchAchievements(p,match));
        }

    }

    //STOPPER
    private int seconds =0;
    private int minutes= 0;
    private int []matchTime= {0,10}; //zero pos -> minutes, first pos -> seconds, now match duration= 10sec
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

                if (matchTime[0] == minutes && matchTime[1] == seconds) { //MATCH FINISHED (or not)
                    isStart = false;

                    if (getPoints(home) == getPoints(away)) //extra time, match not finished
                    {
                        extraTimeCount++;
                        matchTime[1]+=5; // for example: extra time duration = 5 sec xd

                    }
                    else
                        finishedMatch();


/*                isStart = false;
                myTimer.cancel();*/

                }
            }

        }
    };

    public void init(){

        matchText.setText(matchT2);
        homeTeam.setItems(playersSquadH);
        awayTeam.setItems(playersSquadA);

        initCollectAchievements();

        playersTeamA = new ArrayList<>();
        playersTeamH = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       myTimer.schedule(task,1000,1000);

    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.S) {
            isStart = !isStart;
        }
        if (event.getCode() == KeyCode.ENTER) {

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
