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
import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.TeamsService;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;
import static nba_statistics.others.Alerts.getAlertWrongDate;

public class AddTransferPlayer implements Initializable {
    @FXML private ImageView image;@FXML private Button backBtn; @FXML private Button sendBtn;
    @FXML private Text playerText; @FXML private TextField playerField;
    @FXML private ComboBox<String> t42; @FXML private Button pictureBtn;

    private String currImageURL = "";
    private String currSeason = "2019/2020";//until bug is fixed

    public void setCurrSeason(String currSeason){
        this.currSeason = currSeason;
    }

    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/SelectionView.fxml"));
        Parent accountParent = loader.load();
        Scene preseasonScene = new Scene(accountParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    private void initComboBoxTeamsTransfer(){
        TeamsService teamsService = new TeamsService();
        ArrayList<String> allTeams = teamsService.getAllTeams();
        t42.setItems(FXCollections.observableArrayList(allTeams));
    }

    private List<String> getPlayers(){
        PlayersService playersService = new PlayersService();
        return playersService.getAll();
    }

    @SuppressWarnings("Duplicates")
    public void sendToDatabase(){
        PlayersService playersService1 = new PlayersService();
        switch (playersService1.updatePlayer(playerField.getText(),t42.getValue(),currImageURL)){
            case 0:
                PlayerTeamsHistoryService playerTeamsHistoryService = new PlayerTeamsHistoryService();
                if (playerTeamsHistoryService.savePlayerTeamsHistory(playerField.getText(),t42.getValue(),currSeason)){
                    information(4);
                    playerField.clear();
                    image.setImage(null);
                    currImageURL="";
                    t42.getSelectionModel().clearSelection();
                }
                else
                    getAlertSecondTransfer();
                break;
            case 1:
                getAlertTransferToTheSameTeam();
                break;
            case 2:
                getAlertPlayer();
                break;
            case 3:
                getAlertWrongFormat();
                break;
            case 4:
                getAlertComboBoxTeam();
                break;
            case 5:
                getAlertNoDate();
                break;
            case 6:
                getAlertWrongDate();
                break;
            case 7:
                getAlertNoImage();
                break;
            case 8:
                getAlertImage();
                break;
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
        //System.out.println("currSeason=================" + currSeason);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        t42.getSelectionModel().clearSelection();
        playerField.clear();
        initComboBoxTeamsTransfer();
        TextFields.bindAutoCompletion(playerField, getPlayers());
    }
}
