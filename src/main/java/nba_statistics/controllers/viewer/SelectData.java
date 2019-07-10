package nba_statistics.controllers.viewer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

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

    String path = null;

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
    public void selectAchievements() throws URISyntaxException {

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
            path = p.getImageURL();

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
    public void printToFile(ActionEvent event) throws IOException, DocumentException, URISyntaxException {
        if(toPrint.isEmpty()) getAlertNoSeasonsOrNoPlayer();
        else {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("PlayerAchievement.pdf"));

            document.open();
            Font title = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLACK);
            Font subtitle = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font text = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
            Chunk chunk = new Chunk(toPrint.get(1).substring(6) + " " + toPrint.get(2).substring(9), title);

            Chunk chunk2 = new Chunk("The achievements in season: " + toPrint.get(0).substring(8), subtitle);
            Chunk chunk3 = new Chunk(toPrint.get(3), text);
            Chunk chunk4 = new Chunk(toPrint.get(4), text);
            Chunk chunk5 = new Chunk(toPrint.get(5), text);
            Chunk chunk6 = new Chunk(toPrint.get(6), text);
            Chunk chunk7 = new Chunk(toPrint.get(7), text);
            Chunk chunk8 = new Chunk(toPrint.get(8), text);
            Chunk chunk9 = new Chunk(toPrint.get(9), text);
            Chunk chunk10 = new Chunk(toPrint.get(10), text);

            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance(path);
            img.setAbsolutePosition(250, 430);
            document.add(img);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk2));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk3));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk4));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk5));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk6));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk7));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk8));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk9));
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(chunk10));
            document.close();

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
