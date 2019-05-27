package nba_statistics.controllers.preseason;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.entities.Players;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;

public class Preseason implements Initializable {

    @FXML private Button LogOutBtn;

    @FXML private ComboBox<String> comboBox;@FXML private Text enterDataTxt;

    @FXML private ComboBox<String> DivW; @FXML private ComboBox<String> DivE;
    @FXML private Text d10; @FXML private Text d11; @FXML private Text d12; @FXML private Text d13;
    @FXML private ComboBox<String> t10;@FXML private TextField t12; @FXML private TextField t13;

    @FXML private Text m10; @FXML private Text m11; @FXML private Text m12; @FXML private CheckBox transferCheckBox;
    @FXML private TextField t20; @FXML private TextField t21; @FXML private TextField t22; @FXML private CheckBox newPlayerCheckBox;

    @FXML private Text s10; @FXML private Text s11; @FXML private Text s12; @FXML private Text s13;
    @FXML private TextField t30; @FXML private TextField t31; @FXML private TextField t32;

    @FXML private Text p40; @FXML private Text p41; @FXML private Text p42; @FXML private Text p43; @FXML private Text p44; @FXML private Text p45;
    @FXML private TextField t40; @FXML private TextField t41; @FXML private TextField t42;@FXML private TextField t43; @FXML private TextField t44;@FXML private TextField t45;

    @FXML private Button sendBtn; @FXML private Button addedSeasonBtn; @FXML private Button skipBtn;

    @FXML private Text tSeason; @FXML private Text tSeason0;
    @FXML private Text tDuration; @FXML private Text tDuration0;



    private ObservableList<String> choice = FXCollections.observableArrayList
            ("Match", "Player", "Team");

    private ObservableList<String> conference = FXCollections.observableArrayList
            ("Eastern", "Western");

    private ObservableList<String> divisionEastern = FXCollections.observableArrayList
            ("Atlantic", "Central", "Southeast");

    private ObservableList<String> divisionWestern = FXCollections.observableArrayList
            ("Northwest", "Pacific", "Southwest");

    private String state;
    private String currSeason;

    private Visibility v;

    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }


    private void initScene() { //init scene after added season

        comboBox.setItems(choice);
        v.setInvisibleS(s10, s11, s12, s13, t30, t31, t32, addedSeasonBtn);
        skipBtn.setVisible(false);
        comboBox.setVisible(true);
        enterDataTxt.setVisible(true);
        tSeason.setText(currSeason);
        DivE.setItems(divisionEastern);
        DivW.setItems(divisionWestern);
        t10.setItems(conference);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        v = new Visibility();
    }

    public void addedSeason() {

        SeasonsService seasonsDao = new SeasonsService();

        if (!seasonsDao.checkSeason(t30.getText())) {

            switch (seasonsDao.getData(t30.getText(), t31.getText(), t32.getText())) {
                case 0:
                    currSeason = t30.getText();
                    tDuration.setText(t31.getText() + " / " + t32.getText());
                    confirmation(0);
                    initScene();
                    break;
                case 1:
                    getAlertDate(t31.getText());
                    break;
                case 2:
                    getAlertDate(t32.getText());
                    break;
                case 3:
                    getAlertDate();
                    break;
            }
        } else
            getAlertSeasonRepeat(t30.getText());


    }

    public void skipAction() {
        SeasonsService seasonsDao = new SeasonsService();
        if (seasonsDao.checkSeason(t30.getText())) {
            SeasonsService seasonsService = new SeasonsService();
            currSeason = t30.getText(); //get season but not save to database 'this season exist in database'
            tDuration.setText(seasonsService.getSeason(currSeason).getStartDate() + " / " + seasonsService.getSeason(currSeason).getEndDate());
            initScene();
        } else {
            getAlertSeason(t30.getText());
        }

    }

    public void onSelected() {
        switch (comboBox.getValue()) {
            case "Team":
                v.setInvisibleCheckBox(newPlayerCheckBox, transferCheckBox);
                v.setInvisibleM(m10, m11, m12, tSeason, tSeason0, t20, t21, t22,tDuration0, tDuration);
                v.setInvisibleP(p40, p41, p42, p43, p44, p45, t40, t41, t42, t43, t44, t45);
                v.setInvisibleS(s10, s11, s12, s13, t30, t31, t32, addedSeasonBtn);
                v.setVisibleD(d10, d11, d12, d13, t10, t12, t13);
                t10.getSelectionModel().clearSelection();
                v.clearTextFieldD(t12,t13);
                state = "Team";

                break;

            case "Match":
                v.setInvisibleCheckBox(newPlayerCheckBox, transferCheckBox);
                v.setInvisibleD(d10, d11, d12, d13, t10, t12, t13, DivE, DivW);
                v.setInvisibleP(p40, p41, p42, p43, p44, p45, t40, t41, t42, t43, t44, t45);
                v.setInvisibleS(s10, s11, s12, s13, t30, t31, t32, addedSeasonBtn);
                v.setVisibleM(m10, m11, m12, tSeason, tSeason0, t20, t21, t22, tDuration0, tDuration);
                v.clearTextFieldM(t20,t21,t22);

                state = "Match";
                break;

            case "Player":
                v.setInvisibleD(d10, d11, d12, d13, t10, t12, t13, DivE, DivW);
                v.setInvisibleS(s10, s11, s12, s13, t30, t31, t32, addedSeasonBtn);
                v.setInvisibleM(m10, m11, m12, tSeason, tSeason0, t20, t21, t22,tDuration0, tDuration);
                v.setVisibleCheckBox(newPlayerCheckBox, transferCheckBox);
                v.clearTextFieldNewPlayer(t40,t41,t42,t43,t44,t45);
                newPlayerCheckBox.setSelected(false);
                transferCheckBox.setSelected(false);

                break;


        }
        sendBtn.setVisible(true);
    }

    public void getConference() {
        if (t10.getValue() == "Eastern") {
            DivE.setVisible(true);
            DivW.setVisible(false);
        } else if (t10.getValue() == "Western") {
            DivW.setVisible(true);
            DivE.setVisible(false);
        }

    }

    public void addNewPlayer() {
        v.setInvisibleP(p40, p41, p42, p43, p44, p45, t40, t41, t42, t43, t44, t45);
        v.setVisibleNewPlayerT(p40, p41, p42, p43, p44, p45, t40, t41, t42, t43, t44, t45);
        v.clearTextFieldNewPlayer(t40,t41,t42,t43,t44,t45);
        transferCheckBox.setSelected(false);
        state = "NewPlayer";
    }

    public void changeTeam() {
        v.setInvisibleP(p40, p41, p42, p43, p44, p45, t40, t41, t42, t43, t44, t45);
        v.setVisibleTransferT(p40, p41, p42, t40, t41, t42);
        v.clearTextFieldTransfer(t40,t41,t42);
        state = "Transfer";
        newPlayerCheckBox.setSelected(false);
    }

    public void sendToDatabase() {
        switch (state) {
            case "Team":


                TeamsService teamsService = new TeamsService();
                if (teamsService.checkTeam(t12.getText())) {

                    if (t10.getValue() == "Eastern") {
                        if (DivE.getValue() == null)
                            getAlertDivision();
                        else {
                            teamsService.getData(t10.getValue(), DivE.getValue(), t12.getText(), t13.getText());
                            confirmation(1);
                            v.clearTextFieldD(t12, t13);
                        }
                    } else if (t10.getValue() == "Western") {
                        if (DivW.getValue() == null)
                            getAlertDivision();
                        else {
                            teamsService.getData(t10.getValue(), DivW.getValue(), t12.getText(), t13.getText());
                            confirmation(1);
                            v.clearTextFieldD(t12, t13);
                        }
                    } else {
                        getAlertConference();
                    }
                } else
                    getAlertTeam(t12.getText());

                break;
            case "Match":
                MatchesService matchesService = new MatchesService();

                switch (matchesService.getData(t20.getText(), t21.getText(), t22.getText(), currSeason)) {
                    case 0:
                        confirmation(2);
                        v.clearTextFieldM(t20, t21, t22);
                        break;
                    case 1:
                        getAlertTeams(t20.getText());
                        break;
                    case 2:
                        getAlertTeams(t21.getText());
                        break;
                    case 3:
                        getAlertDate(t22.getText());
                        break;
                    case 4:
                        getAlertTeams(t20.getText(), t21.getText());
                        break;
                    case 5:
                        getAlertDateBetween(t22.getText());
                        break;
                }


                break;
            case "NewPlayer":
                PlayersService playersService = new PlayersService();
                try {
                    switch (playersService.getData(t40.getText(), t41.getText(), t43.getText(), Float.parseFloat(t44.getText()), Float.parseFloat(t45.getText()), t42.getText())) {
                        case 0:
                            confirmation(3);
                            v.clearTextFieldNewPlayer(t40, t41, t42, t43, t44, t45);
                            break;
                        case 1:
                            getAlertTeams(t42.getText());
                            break;
                        case 2:
                            getAlertDate(t43.getText());
                            break;
                    }
                } catch (NumberFormatException exc) {
                    getAlertFloat();
                }
                break;
            case "Transfer":
                PlayersService playersService1 = new PlayersService();


                if (playersService1.updatePlayer(t40.getText(), t41.getText(), t42.getText()) == 1) {
                    getAlertTeams(t42.getText());
                } else {
                    List<Players> players = playersService1.getPlayers(t40.getText(), t41.getText());
                    switch (players.size()) {
                        case 0:
                            getAlertPlayer(t40.getText(), t41.getText());
                            break;
                        case 1:
                            confirmation(4);
                            v.clearTextFieldTransfer(t40, t41, t42);
                            break;
                        default:
                            //problemy: TO DO
                    }

                }
                break;
        }
    }


}