package nba_statistics.controllers.preseason;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.services.MatchesService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;

public class AddMatch implements Initializable{
    @FXML private Text m10; @FXML private Text m11; @FXML private Text m12;
    @FXML private ComboBox<String> t20; @FXML private ComboBox<String> t21; @FXML private TextField t22;
    @FXML private Text tSeason; @FXML private Text tSeason0;
    @FXML private Text tDuration; @FXML private Text tDuration0;
    @FXML private Button sendBtn; @FXML private Text enterDataTxt; @FXML private ImageView helpBtn;

    @SuppressWarnings("Duplicates")
    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/SelectionView.fxml"));
        Parent accountParent = loader.load();
        Selection controller = loader.getController();
        controller.setCurrSeasonTmp(currSeason);
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    private String currSeason;
    public void setCurrSeason(String currSeason){
        this.currSeason = currSeason;
    }

    private void initComboBoxTeams(){
        TeamsService teamsService = new TeamsService();
        ArrayList<String> allTeams = teamsService.getAllTeams();
        t20.setItems(FXCollections.observableArrayList(allTeams));
        t21.setItems(FXCollections.observableArrayList(allTeams));
    }


    @SuppressWarnings("Duplicates")
    public void sendToDatabase() {
        MatchesService matchesService = new MatchesService();
        switch (matchesService.getData(t20.getValue(), t21.getValue(), t22.getText(), currSeason)) {
            case 0:
                information(2);
                t22.clear();
                t20.getSelectionModel().clearSelection();
                t21.getSelectionModel().clearSelection();
                break;
            case 1:
                getAlertTeams(t20.getValue());
                break;
            case 2:
                getAlertTeams(t21.getValue());
                break;
            case 3:
                getAlertDate(t22.getText());
                break;
            case 4:
                getAlertTeams(t20.getValue(), t21.getValue());
                break;
            case 5:
                getAlertDateBetween(t22.getText());
                break;
        }
    }

    public void init(){
        tSeason.setText(currSeason);
        SeasonsService seasonsService = new SeasonsService();
        tDuration.setText(seasonsService.getSeason(currSeason).getStartDate() + " / " + seasonsService.getSeason(currSeason).getEndDate());
        initComboBoxTeams();
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/Preseason/AddMatchView.fxml");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helpBtn.setImage(new Image("/help.png"));
    }
}
