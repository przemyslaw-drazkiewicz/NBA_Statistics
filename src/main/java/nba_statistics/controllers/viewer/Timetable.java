package nba_statistics.controllers.viewer;

import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nba_statistics.entities.Matches;
import nba_statistics.services.MatchesService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;

public class Timetable implements Initializable {

    @FXML
    private ComboBox<String> seasonComboBox;
    @FXML
    private ComboBox<String> teamsComboBox;
    @FXML
    private Button backButton;
    @FXML
    private Button selectTimetableButton;
    @FXML
    private TableView<Matches> matchesTable;
    @FXML
    private TableColumn<Matches, String> homeTeam;
    @FXML
    private TableColumn<Matches, String> awayTeam;
    @FXML
    private TableColumn<Matches, String> date;
    @FXML
    private TableColumn<Matches, Integer> homePoints;
    @FXML
    private TableColumn<Matches, Integer> awayPoints;
    @FXML
    private TableColumn<Matches, Integer> extraTime;


    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    private void setTable(List<Matches> listMatches) {

        if (listMatches.isEmpty()) {
            getAlertEmptyListOfMatches();
        } else {
            matchesTable.setVisible(true);
            ObservableList<Matches> matchesObservableList = FXCollections.observableArrayList();

            matchesObservableList.addAll(listMatches);
            matchesTable.setItems(matchesObservableList);
        }
    }

    public void selectTimetable() {
        String team, season;
        matchesTable.setVisible(false);
        if ((season = seasonComboBox.getValue()) == null)
            getAlertComboBoxSeason();
        else if ((team = teamsComboBox.getValue()) == null) {
            getAlertComboBoxTeam();
        } else {
            MatchesService matchesService = new MatchesService();
            setTable(matchesService.getMatches(team, season));
        }


    }

    private void initComboBoxSeasons() {
        SeasonsService seasonsService = new SeasonsService();
        ArrayList<String> seasons = seasonsService.getAllSeasonsName();
        seasonComboBox.setItems(FXCollections.observableArrayList(seasons));
    }

    private void initComboBoxTeams() {
        TeamsService teamsService = new TeamsService();
        ArrayList<String> teams = teamsService.getAllTeams();
        teamsComboBox.setItems(FXCollections.observableList(teams));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxSeasons();
        initComboBoxTeams();

        //init column in table view -> to TIMETABLE
        homeTeam.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getHostTeam().getName()));
        awayTeam.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getGuestTeam().getName()));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        homePoints.setCellValueFactory(new PropertyValueFactory<>("hostPoints"));
        awayPoints.setCellValueFactory(new PropertyValueFactory<>("guestPoints"));
        extraTime.setCellValueFactory(new PropertyValueFactory<>("extraTimeCount"));
    }
}
