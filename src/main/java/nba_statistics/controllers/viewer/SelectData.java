package nba_statistics.controllers.viewer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.*;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.net.URL;
import java.util.*;

import static nba_statistics.others.Alerts.*;

public class SelectData implements Initializable {


    List<PlayerTeamsHistory> playerSeasons= new ArrayList<>();
    List<String> toPrint= new ArrayList<>();
    private Players p;
    private Teams t;

    @FXML
    private Button backBtn;
    @FXML
    private Button OKbtn;
    @FXML
    private Button seasonBtn;
    @FXML
    private Text writeName;
    @FXML
    private Text selectSeason;
    @FXML
    private TextField playerField;
    @FXML
    private ComboBox<String> seasonsComboBox;
    @FXML
    private ListView<String> playerAchievListView;
    @FXML
    private ImageView image;
    @FXML
    private Stage stage;
    @FXML
    private MenuBar toPrintMenu;
    @FXML
    private ImageView helpBtn;

    int points = 0, steals = 0, blocks = 0, rebounds = 0, fouls = 0, techFaul = 0;

    //visibility
    private void setVisibleLabelsPlayerAchievements() {
        playerAchievListView.setVisible(true);
        image.setVisible(true);

    }

    private void setInvisibleLabelsPlayerAchievements() {
        playerAchievListView.setVisible(false);
        image.setVisible(false);

    }

    private void setVisibleSelectSeason(){
        seasonsComboBox.setVisible(true);
        selectSeason.setVisible(true);
        seasonBtn.setVisible(true);
    }

    private void setInvisibleSelectSeason(){
        seasonsComboBox.setVisible(false);
        selectSeason.setVisible(false);
        seasonBtn.setVisible(false);
    }

    //screen changing
    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    //list all players
    private List<String> getPlayers(){
        PlayersService playersService = new PlayersService();
        return playersService.getAll();
    }


    //OKbutton
    public void initFields(){
        PlayersService playersService = new PlayersService();

        setInvisibleSelectSeason();
        setInvisibleLabelsPlayerAchievements();

        p = playersService.getPlayerFromAutoCompleteField(playerField.getText());
        if (p != null) {
            playerSeasons = playersService.getPlayerTeamsHistory(p.getId());

            if(playerSeasons.isEmpty()) getAlertNoSeasonsForPlayer();
            else{
                ArrayList<String> seasonsNameList = new ArrayList<>();
                for (PlayerTeamsHistory seasonName : playerSeasons){
                    seasonsNameList.add(seasonName.getSeason().getName());
                }
                setVisibleSelectSeason();
                seasonsComboBox.setItems(FXCollections.observableArrayList(seasonsNameList));
            }
        }
        else
            getAlertNoPlayer();
    }

    //selectButton
    public void selectAchievements(){

        MatchesService matchesService = new MatchesService();
        SeasonsService seasonsService = new SeasonsService();
        TeamsService teamsService = new TeamsService();

        String seasonName = seasonsComboBox.getValue();

        if(seasonName != null){
            int seasonId = seasonsService.getId(seasonName);
            int teamId = -1;

            ObservableList<String> listString = FXCollections.observableArrayList();

            listString.add("Season: " + seasonName);
            listString.add("Name: " + p.getName()); //
            listString.add("Surname: " + p.getSurname());

            for (int i = 0; i < playerSeasons.size(); i++) {
                if (seasonName.equalsIgnoreCase(playerSeasons.get(i).getSeason().getName())) {
                    teamId = playerSeasons.get(i).getTeam().getId();

                }
            }

            t = teamsService.getTeam(teamId);
            listString.add("Team: " + t.getName());

            List<PlayerMatchAchievements> achievementPlayer = matchesService.getAchievementPlayerInMatch(p.getId(), seasonId, teamId);
            listString.add("Number matches: " + achievementPlayer.size());

            points = 0;
            steals = 0;
            blocks = 0;
            rebounds = 0;
            fouls = 0;
            techFaul = 0;

            for (PlayerMatchAchievements os : achievementPlayer) {
                points += os.getScoredPoints();
                steals += os.getSteals();
                blocks += os.getBlocks();
                rebounds += os.getRebounds();
                fouls += os.getFouls();
                techFaul += os.getTechnicalFouls();
            }

            listString.add("Points earned: " + points);
            listString.add("Steals: " + steals);
            listString.add("Blocks: " + blocks);
            listString.add("Rebounds: " + rebounds);
            listString.add("Fouls: " + fouls);
            listString.add("Technical fouls: " + techFaul);
            image.setImage(new Image(p.getImageURL()));

            playerAchievListView.setItems(listString);
            setVisibleLabelsPlayerAchievements();

            for(String s : listString){
                toPrint.add(s);
            }
        }
        else if(playerSeasons.isEmpty()) getAlertClickOkButton();
        else getAlertSelectSeason();


    }

    //print
    public void printToFile(ActionEvent event) throws IOException  {
        if(toPrint.isEmpty()) getAlertNoSeasonsOrNoPlayer();
        else{
            ObservableList<String> stringList = FXCollections.observableArrayList();
            stringList.add("a");
            stringList.add("b");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Copy of Report");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setInitialFileName("First report.txt");

            File file = fileChooser.showSaveDialog(stage);


            if(file!= null){
                FileWriter fileWriter = new FileWriter(file);

                fileWriter.write(toPrint.toString() + "\n");

                fileWriter.flush();
                fileWriter.close();
            }
            toPrint.clear();
        }

    }

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/SelectData.fxml");
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

        setInvisibleLabelsPlayerAchievements();
        setInvisibleSelectSeason();
        helpBtn.setImage(new Image("/help.png"));
        TextFields.bindAutoCompletion(playerField, getPlayers());
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            initFields();
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
