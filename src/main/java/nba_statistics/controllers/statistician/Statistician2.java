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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.AccountController;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.*;
import nba_statistics.services.*;

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
    @FXML private ListView positionsChoice;
    @FXML private Text homeTeamName;
    @FXML private Text awayTeamName;
    @FXML private ImageView helpBtn;
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
    private List<String> positionsListTeamH;
    private List<String> positionsListTeamA;
    private ObservableList<String> reserveListTeamH;
    private ObservableList<String> reserveListTeamA;

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
        controller.setReserveTeamH(reserveListTeamH);
        controller.setReserveTeamA(reserveListTeamA);
        controller.setHomeTeamSquad(homeTeamSquad);
        controller.setAwayTeamSquad(awayTeamSquad);
        controller.setPositionsListTeamH(positionsListTeamH);
        controller.setPositionsListTeamA(positionsListTeamA);
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
        String position = (String) positionsChoice.getSelectionModel().getSelectedItem();
        if(player==null || position==null)
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
            positionsListTeamH.add(position);
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
            reserveListTeamH = playersListH;
        }
    }

    @SuppressWarnings("Duplicates")
    public void onClickButtonRemH() {
        String player =(String) homeTeamFive.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Remove");
        }
        else {

            int index = 0;
            for(int i=0;i<homeTeamSquad.size();i++)
            {
                if(player.equals(homeTeamSquad.get(i)))
                {
                    index = i;
                }
            }
            homeTeamSquad.remove(player);
            positionsListTeamH.remove(index);
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
            reserveListTeamH = playersListH;
        }
    }

    @SuppressWarnings("Duplicates")
    public void onClickButtonAddA() {
        String player =(String) awayTeamChoice.getSelectionModel().getSelectedItem();
        String position = (String) positionsChoice.getSelectionModel().getSelectedItem();
        if(player==null || position==null)
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
            positionsListTeamA.add(position);
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
            reserveListTeamA = playersListA;
        }
    }

    @SuppressWarnings("Duplicates")
    public void onClickButtonRemA() {
        String player =(String) awayTeamFive.getSelectionModel().getSelectedItem();
        if(player==null)
        {
            getAlertChoicePlayer("Remove");
        }
        else {
            int index = 0;
            for(int i=0;i<awayTeamSquad.size();i++)
            {
                if(player.equals(awayTeamSquad.get(i)))
                {
                    index = i;
                }
            }

            awayTeamSquad.remove(player);
            playersListTeamA.add(player);
            positionsListTeamA.remove(index);
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
            reserveListTeamA = playersListA;
        }
    }

    public void init(){
        System.out.println("Init = " + match);
        homeTeamSquad = new ArrayList<>();
        awayTeamSquad = new ArrayList<>();
        playersListTeamH = new ArrayList<>();
        playersListTeamA = new ArrayList<>();
        positionsListTeamH = new ArrayList<>();
        positionsListTeamA = new ArrayList<>();

        List<String> positionsList = new ArrayList<>();
        Teams teamH = match.getHostTeam();
        Teams teamA = match.getGuestTeam();
        int id_TeamH = teamH.getId();
        int id_TeamA = teamA.getId();

        PlayersService playersDAO = new PlayersService();
        PositionsService positionsDao = new PositionsService();

        playersTeamH = playersDAO.getPlayers(id_TeamH);
        playersTeamA = playersDAO.getPlayers(id_TeamA);
        positionsList = positionsDao.findAllPositionsName();
        ObservableList<String> playersA = FXCollections.observableArrayList();
        ObservableList<String> playersH = FXCollections.observableArrayList();
        ObservableList<String> positionsL = FXCollections.observableArrayList();

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

        for (String position : positionsList) {
            positionsL.add(position);
        }

        homeTeamChoice.setItems(playersH);
        awayTeamChoice.setItems(playersA);
        positionsChoice.setItems(positionsL);
        homeTeamName.setText(teamH.getName());
        awayTeamName.setText(teamA.getName());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helpBtn.setImage(new Image("/help.png"));
        System.out.println(match + " initialize");

//        v = new Visibility();
    }
    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/Statistician2.fxml");
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
