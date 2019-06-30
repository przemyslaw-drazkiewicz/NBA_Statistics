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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.*;
import nba_statistics.services.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Statistician3 implements Initializable {


    @FXML
    private Text matchText;
    @FXML
    private Button buttonBack;
    @FXML
    private ListView homeTeam;
    @FXML
    private ListView awayTeam;
    @FXML
    private Button oneH;
    @FXML
    private Button twoH;
    @FXML
    private Button threeH;
    @FXML
    private Button oneA;
    @FXML
    private Button twoA;
    @FXML
    private Button threeA;
    @FXML
    private Button StH;
    @FXML
    private Button StA;
    @FXML
    private Button BH;
    @FXML
    private Button BA;
    @FXML
    private Button RH;
    @FXML
    private Button RA;
    @FXML
    private Button FH;
    @FXML
    private Button FA;
    @FXML
    private Button TFH;
    @FXML
    private Button TFA;
    @FXML
    private Button SH;
    @FXML
    private Button SA;
    //TIMER
    @FXML
    private Text sec;
    @FXML
    private Text min;
    @FXML
    private Text colon;

    //Results
    @FXML
    private Text pointsHome;
    @FXML
    private Text pointsAway;

    //quater
    @FXML
    private Text couter;
    @FXML
    private Text txt;

    //info
    @FXML
    private Text info;

    @FXML
    private Button backToChoice;

    @FXML
    private ListView reserveHT;
    @FXML
    private ListView reserveAT;

    @FXML
    private ListView substitutionsHT;
    @FXML
    private ListView substitutionsAT;

    @FXML
    private ListView positionsHT;
    @FXML
    private ListView positionsAT;
    @FXML
    private ImageView helpBtn;

    private List<Players> playersTeamH;
    private List<Players> playersTeamA;

    private ObservableList<String> reserveTeamH;
    private ObservableList<String> reserveTeamA;

    private List<String> homeTeamSquad;
    private List<String> awayTeamSquad;

    private List<String> positionsListTeamH;
    private List<String> positionsListTeamA;

    private Matches match;

    private String matchT2;

    private int extraTimeCount = 0;
    private int quarterCount = 1;

    private ObservableList<String> playersSquadA = FXCollections.observableArrayList();
    private ObservableList<String> playersSquadH = FXCollections.observableArrayList();
    private ArrayList<PlayerMatchAchievements> home = new ArrayList<>();
    private ArrayList<PlayerMatchAchievements> away = new ArrayList<>();

    private ArrayList<PlayerMatchPositions> homePositionsHistory = new ArrayList<>();
    private ArrayList<PlayerMatchPositions> awayPositionsHistory = new ArrayList<>();

    private ArrayList<MatchSubstitutionHistory> substitutionHistoriesHT = new ArrayList<>();
    private ArrayList<MatchSubstitutionHistory> substitutionHistoriesAT = new ArrayList<>();


    public void setPlayersSquadA(ObservableList<String> playersSquadA) {
        this.playersSquadA = playersSquadA;
    }

    public void setPlayersSquadH(ObservableList<String> playersSquadH) {
        this.playersSquadH = playersSquadH;
    }

    public void setReserveTeamH(ObservableList<String> reserveTeamH) {
        this.reserveTeamH = reserveTeamH;
    }

    public void setReserveTeamA(ObservableList<String> reserveTeamA) {
        this.reserveTeamA = reserveTeamA;
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

    public void setHomeTeamSquad(List<String> homeTeamSquad) {
        this.homeTeamSquad = homeTeamSquad;
    }

    public void setAwayTeamSquad(List<String> awayTeamSquad) {
        this.awayTeamSquad = awayTeamSquad;
    }

    public void setPositionsListTeamH(List<String> positionsListTeamH) {
        this.positionsListTeamH = positionsListTeamH;
    }

    public void setPositionsListTeamA(List<String> positionsListTeamA) {
        this.positionsListTeamA = positionsListTeamA;
    }

    public void setMatch(Matches match) {
        this.match = match;
    }

    @SuppressWarnings("Duplicates")
    public void changeScreenBack(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician2.fxml"));
        Parent accountParent = loader.load();
        Statistician2 controller = loader.getController();
        controller.setMatch(match);
        controller.init();
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    @SuppressWarnings("Duplicates")
    public void matchFinished(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician.fxml"));
        Parent accountParent = loader.load();
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    private boolean isStart = false;


    private int findPosPlayerHome(String player) {
        int i = 0;
        for (PlayerMatchAchievements p : home) {
            if ((p.getPlayer().getName() + " " + p.getPlayer().getSurname()).equals(player)) {
                return i;
            }
            i++;
        }
        return -1; // :((( but... NOT POSSIBLE ;)
    }

    public void oneH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null) { //player is not selected
            home.get(findPosPlayerHome(player)).addPoints(1);
            pointsHome.setText(String.valueOf((Integer.parseInt(pointsHome.getText()) + 1)));
        }

    }

    public void twoH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null) {
            home.get(findPosPlayerHome(player)).addPoints(2);
            pointsHome.setText(String.valueOf((Integer.parseInt(pointsHome.getText()) + 2)));
        }

    }

    public void threeH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null) {
            home.get(findPosPlayerHome(player)).addPoints(3);
            pointsHome.setText(String.valueOf((Integer.parseInt(pointsHome.getText()) + 3)));
        }
    }

    public void stealsH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addSteal();
    }

    public void blockH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addBlock();
    }

    public void reboundH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addRebound();
    }

    public void foulH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addFoul();
    }

    public void techFoulH() {
        String player = (String) homeTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            home.get(findPosPlayerHome(player)).addTechFoul();
    }

    private int findPosPlayerAway(String player) {
        int i = 0;
        for (PlayerMatchAchievements p : away) {
            if ((p.getPlayer().getName() + " " + p.getPlayer().getSurname()).equals(player)) {
                return i;
            }
            i++;
        }
        return -1; // :((( but... NOT POSSIBLE ;)
    }

    public void oneA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null) { //player is not selected
            away.get(findPosPlayerAway(player)).addPoints(1);
            pointsAway.setText(String.valueOf((Integer.parseInt(pointsAway.getText()) + 1)));
        }
    }

    public void twoA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null) {
            away.get(findPosPlayerAway(player)).addPoints(2);
            pointsAway.setText(String.valueOf((Integer.parseInt(pointsAway.getText()) + 2)));
        }
    }

    public void threeA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null) {
            away.get(findPosPlayerAway(player)).addPoints(3);
            pointsAway.setText(String.valueOf((Integer.parseInt(pointsAway.getText()) + 3)));
        }
    }

    public void stealsA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addSteal();
    }

    public void blockA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addBlock();
    }

    public void reboundA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addRebound();
    }

    public void foulA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addFoul();
    }

    public void techFoulA() {
        String player = (String) awayTeam.getSelectionModel().getSelectedItem();
        if (player != null)
            away.get(findPosPlayerAway(player)).addTechFoul();
    }

    @SuppressWarnings("Duplicates")
    public void substitutionHT() {
        String playerOut = (String) homeTeam.getSelectionModel().getSelectedItem();
        String playerIn = (String) reserveHT.getSelectionModel().getSelectedItem();
        String position = (String) positionsHT.getSelectionModel().getSelectedItem();
        String reason = (String) substitutionsHT.getSelectionModel().getSelectedItem();
        if (playerOut != null && playerIn != null && position != null && reason != null) {
            PlayersService playersDao = new PlayersService();
            PositionsService positionsDao = new PositionsService();
            SubstitutionReasonService substitutionReasonDao = new SubstitutionReasonService();
            String[] playerOutS = playerOut.split(" ");
            String[] playerInS = playerIn.split(" ");
            if (playerOutS.length > 1 && playerInS.length > 1) {
                Players playerOutP = playersDao.getPlayer(playerOutS[0], playerOutS[1]);
                Players playerInP = playersDao.getPlayer(playerInS[0], playerInS[1]);
                Positions positions = positionsDao.getPosition(position);
                SubstitutionReasons substitutionReason = substitutionReasonDao.getSubstitutionReason(reason);
                substitutionHistoriesHT.add(new MatchSubstitutionHistory(playerOutP.getId(), minutes + ":" + seconds, playerInP.getId(), substitutionReason,match));
                boolean find = false;
                PlayerMatchPositions playerMatchPositions = new PlayerMatchPositions(playerInP, match, positions);
                for (int i = 0; i < homePositionsHistory.size(); i++) {
                    if (EqualsPlayerMatchPosition(playerMatchPositions,homePositionsHistory.get(i))) {
                        find = true;
                    }
                }
                if (!find) {
                    homePositionsHistory.add(playerMatchPositions);
                }
                playersSquadH.remove(playerOut);
                reserveTeamH .remove(playerIn);
                playersSquadH.add(playerIn);
                reserveTeamH.add(playerOut);
                homeTeam.setItems(playersSquadH);
                reserveHT.setItems(reserveTeamH);
            }
        }
    }

    private boolean EqualsPlayerMatchPosition(PlayerMatchPositions playerMatchPositions1,PlayerMatchPositions playerMatchPositions2){
        if(playerMatchPositions1.getPlayer().getId()==playerMatchPositions2.getPlayer().getId()&&playerMatchPositions1.getMatch().getId()==playerMatchPositions2.getMatch().getId()&&playerMatchPositions1.getPosition().getId()==playerMatchPositions2.getPosition().getId())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @SuppressWarnings("Duplicates")
    public void substitutionAT() {
        String playerOut = (String) awayTeam.getSelectionModel().getSelectedItem();
        String playerIn = (String) reserveAT.getSelectionModel().getSelectedItem();
        String position = (String) positionsAT.getSelectionModel().getSelectedItem();
        String reason = (String) substitutionsAT.getSelectionModel().getSelectedItem();
        if (playerOut != null && playerIn != null && position != null && reason != null) {
            PlayersService playersDao = new PlayersService();
            PositionsService positionsDao = new PositionsService();
            SubstitutionReasonService substitutionReasonDao = new SubstitutionReasonService();
            String[] playerOutS = playerOut.split(" ");
            String[] playerInS = playerIn.split(" ");
            if (playerOutS.length > 1 && playerInS.length > 1) {
                Players playerOutP = playersDao.getPlayer(playerOutS[0], playerOutS[1]);
                Players playerInP = playersDao.getPlayer(playerInS[0], playerInS[1]);
                Positions positions = positionsDao.getPosition(position);
                SubstitutionReasons substitutionReason = substitutionReasonDao.getSubstitutionReason(reason);
                substitutionHistoriesAT.add(new MatchSubstitutionHistory(playerOutP.getId(), minutes + ":" + seconds, playerInP.getId(), substitutionReason, match));
                boolean find = false;
                PlayerMatchPositions playerMatchPositions = new PlayerMatchPositions(playerInP, match, positions);
                for (int i = 0; i < awayPositionsHistory.size(); i++) {
                    if (EqualsPlayerMatchPosition(playerMatchPositions,awayPositionsHistory.get(i))) {
                        find = true;
                    }
                }
                if (!find) {
                    awayPositionsHistory.add(playerMatchPositions);
                }
                playersSquadA.remove(playerOut);
                reserveTeamA .remove(playerIn);
                playersSquadA.add(playerIn);
                reserveTeamA.add(playerOut);
                awayTeam.setItems(playersSquadA);
                reserveAT.setItems(reserveTeamA);
            }
        }
    }

    private void saveSubstitutions()
    {
        MatchSubstitutionHistoryService matchSubstitutionHistoryDao = new MatchSubstitutionHistoryService();
        for (MatchSubstitutionHistory matchSubstitutionHistory : substitutionHistoriesHT) {
            matchSubstitutionHistoryDao.getData(matchSubstitutionHistory);
        }
        for (MatchSubstitutionHistory matchSubstitutionHistory : substitutionHistoriesAT) {
            matchSubstitutionHistoryDao.getData(matchSubstitutionHistory);
        }
    }

    private void finishedMatch(){
        //toTesting();
        PlayerMatchAchievementsService playerMatchAchievementsService = new PlayerMatchAchievementsService();
        playerMatchAchievementsService.save(home);
        playerMatchAchievementsService.save(away);

        savePlayerMatchPositions();
        saveSubstitutions();

        match.setHostPoints(getPoints(home));
        match.setGuestPoints(getPoints(away));
        match.setExtraTimeCount(extraTimeCount);

        MatchesService matchesService = new MatchesService();
        matchesService.update(match);
        myTimer.cancel();
        helpBtn.setVisible(false);
        backToChoice.setVisible(true);
    }
    private int getPoints(ArrayList<PlayerMatchAchievements> team){
        int points = 0;
        for (PlayerMatchAchievements p : team){
            points+=p.getScoredPoints();
        }
        return points;
    }

    @SuppressWarnings("Duplicates")
private void savePlayerMatchPositions() {

    PlayerMatchPositionsService playerMatchPositionsDao = new PlayerMatchPositionsService();
        for (PlayerMatchPositions playerMatchPositions : homePositionsHistory) {
            playerMatchPositionsDao.getData(playerMatchPositions.getPlayer(),playerMatchPositions.getMatch(),playerMatchPositions.getPosition());
        }
        for (PlayerMatchPositions playerMatchPositions : awayPositionsHistory) {
            playerMatchPositionsDao.getData(playerMatchPositions.getPlayer(),playerMatchPositions.getMatch(),playerMatchPositions.getPosition());
        }
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
    @SuppressWarnings("Duplicates")
    private void initCollectPositionsHistory(){
        PlayersService playersDao = new PlayersService();
        PositionsService positionsDao = new PositionsService();

        for (int i = 0; i < 5; i++) {
            String homeTeamPlayer = homeTeamSquad.get(i);
            String[] homeNamePlayer = homeTeamPlayer.split(" ");
            String homefirstname = homeNamePlayer[0];
            String homesurname = homeNamePlayer[1];
            String awayTeamPlayer = awayTeamSquad.get(i);
            String[] awayNamePlayer = awayTeamPlayer.split(" ");
            String awayfirstname = awayNamePlayer[0];
            String awaysurname = awayNamePlayer[1];
            Players homePlayer = null;
            Players awayPlayer = null;
            List<Players> homePlayers = playersDao.getPlayers(homefirstname, homesurname);
            for (Players player : homePlayers) {
                if (player.getTeam().getId() == match.getHostTeam().getId()) {
                    homePlayer = player;
                }
            }
            List<Players> awayPlayers = playersDao.getPlayers(awayfirstname, awaysurname);
            for (Players player : awayPlayers) {
                if (player.getTeam().getId() == match.getGuestTeam().getId()) {
                    awayPlayer = player;
                }
            }
            String homeTeamPlayerPosition = positionsListTeamH.get(i);
            String awayTeamPlayerPosition = positionsListTeamA.get(i);
            Positions positionHomePlayer = positionsDao.getPosition(homeTeamPlayerPosition);
            Positions positionAwayPlayer = positionsDao.getPosition(awayTeamPlayerPosition);
            homePositionsHistory.add(new PlayerMatchPositions(homePlayer,match,positionHomePlayer));
            awayPositionsHistory.add(new PlayerMatchPositions(awayPlayer,match,positionAwayPlayer));
        }

    }

    //STOPPER
    private int seconds =0;
    private int minutes= 0;;
    private int [] matchPartTime = {0,10}; //zero pos -> minutes, first pos -> seconds, now quarter duration= 10sec
    private int stopMinutes = matchPartTime[0];
    private int stopSeconds = matchPartTime[1];
    private int [] extraTime = {0,0};
    private Timer myTimer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (isStart) {
                info.setVisible(false);
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




                if ( stopMinutes == minutes && stopSeconds == seconds) { //Quarter FINISHED

                    isStart = false;
                    if (quarterCount == 4) { //end match (or no)
                        if (getPoints(home) == getPoints(away)) //extra time, match not finished
                        {
                            extraTimeCount++;
                            extraTime[1]=5; // for example: extra time duration = 5 sec xd
                            txt.setText("extra time");
                            couter.setText(String.valueOf(extraTimeCount));
                            info.setVisible(true);
                            info.setText("DRAW! Press 's' to start " + extraTimeCount + " extra time");

                        }
                        else {
                            info.setVisible(true);
                            info.setText("Match finished!");
                            finishedMatch();
                        }
                    }
                    else{

                        info.setVisible(true);
                        info.setText(quarterCount + " quarter finished! Press 's' to start next quarter");
                        quarterCount++;
                        txt.setText("quarter");
                        couter.setText(String.valueOf(quarterCount));
                    }

                    stopSeconds = matchPartTime[1]*quarterCount + extraTime[1] * extraTimeCount;
                    if (stopSeconds >=60){
                        stopMinutes = stopSeconds / 60;
                        stopSeconds = stopSeconds % 60;
                    }else
                        stopMinutes = matchPartTime[0]*quarterCount + extraTime[0] * extraTimeCount;





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
        PositionsService positionsDAO = new PositionsService();
        SubstitutionReasonService substitutionReasonDao = new SubstitutionReasonService();
        ArrayList<String> positions = positionsDAO.findAllPositionsName();
        ObservableList<String> positionsA = FXCollections.observableArrayList();
        ObservableList<String> positionsH = FXCollections.observableArrayList();

        ArrayList<String> substitutionReasons = substitutionReasonDao.findAllSubstitutionReasonsName();
        ObservableList<String> substitutionReasonsH = FXCollections.observableArrayList();
        ObservableList<String> substitutionReasonsA = FXCollections.observableArrayList();

        substitutionHistoriesHT = new ArrayList<>();
        substitutionHistoriesAT = new ArrayList<>();

        for (String position : positions) {
            positionsA.add(position);
            positionsH.add(position);
        }

        for (String substitutionReason : substitutionReasons) {
            substitutionReasonsA.add(substitutionReason);
            substitutionReasonsH.add(substitutionReason);
        }

        positionsAT.setItems(positionsA);
        positionsHT.setItems(positionsH);

        substitutionsAT.setItems(substitutionReasonsA);
        substitutionsHT.setItems(substitutionReasonsH);

        reserveAT.setItems(reserveTeamA);
        reserveHT.setItems(reserveTeamH);

        initCollectAchievements();
        initCollectPositionsHistory();



        playersTeamA = new ArrayList<>();
        playersTeamH = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helpBtn.setImage(new Image("/help.png"));
       myTimer.schedule(task,1000,1000);

    }
    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/Statistician3.fxml");
        helpController.init();
        Stage parent = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(parent);
        window.setHeight(350);
        window.setWidth(500);
        window.setTitle("Help window");
        Scene reviewerScene = new Scene(accountParent);
        window.setScene(reviewerScene);
        window.show();
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
