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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nba_statistics.entities.Players;
import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;

public class UpdatePlayer implements Initializable {
    @FXML private ImageView image;@FXML private Button backBtn; @FXML private Button sendBtn;
    @FXML private Text playerText; @FXML private TextField playerField;
    @FXML private ComboBox<String> t42; @FXML private Button pictureBtn;
    @FXML private TextField heightField; @FXML private TextField weightField;
    @FXML private Text tSeason; @FXML private Text tDuration;

    private String currImageURL = "";
    private String currSeason;
    private Players p;

    public void setCurrSeason(String currSeason){
        this.currSeason = currSeason;
    }


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

    private void initComboBoxTeamsTransfer(){
        TeamsService teamsService = new TeamsService();
        ArrayList<String> allTeams = teamsService.getAllTeams();
        allTeams.add("RETIRED");
        t42.setItems(FXCollections.observableArrayList(allTeams));
    }

    private boolean clickOK = false;
    public void initFields(){
        PlayersService playersService = new PlayersService();
        p = playersService.getPlayerFromAutoCompleteField(playerField.getText());
        if (p != null) {
            if (p.getTeam() != null)
                t42.setValue(p.getTeam().getName());
            else
                t42.setValue("RETIRED");

            heightField.setText(String.valueOf(p.getHeight()));
            weightField.setText(String.valueOf(p.getWeight()));
            image.setImage(new Image(p.getImageURL()));
        }
        else
            getAlertNoPlayer();

        clickOK = true;

    }

    private Players getPlayerToCheck(){
        PlayersService playersService = new PlayersService();
        return playersService.getPlayerFromAutoCompleteField(playerField.getText());
    }
    private List<String> getPlayers(){
        PlayersService playersService = new PlayersService();
        return playersService.getAll();
    }

    private void clearFields(){
        playerField.clear();
        image.setImage(null);
        currImageURL="";
        t42.getSelectionModel().clearSelection();
        heightField.clear();
        weightField.clear();
    }
    private void clearFields2(){
        image.setImage(null);
        currImageURL="";
        t42.getSelectionModel().clearSelection();
        heightField.clear();
        weightField.clear();
    }

    private int getSeasonId(){
        SeasonsService seasonsService = new SeasonsService();
        return seasonsService.getId(currSeason);
    }
    @SuppressWarnings("Duplicates")
    public void sendToDatabase(){
        if (p != null && p.equals(getPlayerToCheck()) && clickOK) {
            PlayersService playersService1 = new PlayersService();
            clickOK = false;
            switch (playersService1.updatePlayer(p, t42.getValue(), currImageURL, heightField.getText(), weightField.getText(), getSeasonId())) {
                case 0:
                    PlayerTeamsHistoryService playerTeamsHistoryService = new PlayerTeamsHistoryService();
                    playerTeamsHistoryService.savePlayerTeamsHistory(playerField.getText(), t42.getValue(), currSeason);
                    information(4);
                    clearFields();
                    break;
                case 8:
                    getAlertImage();
                    break;
                case 60:
                    getAlertFloat();
                    break;
                case 70:
                    getAlertNegativeValue();
                    break;
                case 80:
                    getAlertSecondTransfer();
                    break;
                case 90:
                    getAlertChangedTeamButNotChangedImage();
                    break;

            }
        }
        else {
            getAlertClickOkButton();
            clearFields2();
        }
    }

    public void setImage(Event event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Open Resource File");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file!=null){
            currImageURL=file.toURI().toString();
            image.setImage(new Image(currImageURL));
        }

    }

    public void init(){
        tSeason.setText(currSeason);
        SeasonsService seasonsService = new SeasonsService();
        tDuration.setText(seasonsService.getSeason(currSeason).getStartDate() + " / " + seasonsService.getSeason(currSeason).getEndDate());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        t42.getSelectionModel().clearSelection();
        playerField.clear();
        initComboBoxTeamsTransfer();
        TextFields.bindAutoCompletion(playerField, getPlayers());
    }
}
