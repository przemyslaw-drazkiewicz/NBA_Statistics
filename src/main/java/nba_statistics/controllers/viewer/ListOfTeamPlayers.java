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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;

public class ListOfTeamPlayers implements Initializable {

    @FXML
    private ComboBox<String> seasonComboBox;
    @FXML
    private ComboBox<String> teamsComboBox;
    @FXML
    private Button backButton;
    @FXML
    private Button selectListButton;
    @FXML
    private ListView<String> teamPlayersListView;
    @FXML
    private ImageView image;
    @FXML
    private ImageView helpBtn;
    @FXML
    private Stage stage;

    List<String> toPrint= new ArrayList<>();
    String path = null;

    private void setVisibleListViewPlayers(){
        teamPlayersListView.setVisible(true);
    }
    private void setInvisibleListViewPlayers(){
        teamPlayersListView.setVisible(false);
    }

    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
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

    public void selectlistOfTeamPlayers() {
        image.setImage(null);
        String season, team;
        int idSeason = -1;
        int idTeam = -1;
        if ((season = seasonComboBox.getValue()) == null)
            getAlertComboBoxSeason();
        else if ((team = teamsComboBox.getValue()) == null) {
            getAlertComboBoxTeam();
        } else {
            setVisibleListViewPlayers();

            PlayersService playersService = new PlayersService();
            SeasonsService seasonsService = new SeasonsService();
            TeamsService teamsService = new TeamsService();

            idSeason = seasonsService.getId(season);
            idTeam = teamsService.getId(team);

            List<PlayerTeamsHistory> idPlayersList = playersService.getPlayersInTeam(idSeason, idTeam);
            ObservableList<String> listPlayersNameSurname = FXCollections.observableArrayList();

            String tmp = null;
            for(int i = 0; i< idPlayersList.size(); i++){
                tmp = null;
                tmp = idPlayersList.get(i).getPlayer().getName() + " " + idPlayersList.get(i).getPlayer().getSurname();
                listPlayersNameSurname.add(tmp);
            }
            image.setImage(new Image(teamsService.getTeam(idTeam).getImageURL()));
            path = teamsService.getTeam(idTeam).getImageURL();
            if(listPlayersNameSurname.isEmpty()) {
                setInvisibleListViewPlayers();
                getAlertNoPlayersInTeam();
            }
            else{
                listPlayersNameSurname.sorted();
                teamPlayersListView.setItems(listPlayersNameSurname);

                for(String s : listPlayersNameSurname){
                    toPrint.add(s);
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/ListOfTeamPlayersView.fxml");
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

    //print
    public void printToFile(ActionEvent event) throws IOException, DocumentException {
        if(toPrint.isEmpty()) getAlertNoSeasonsOrNoPlayer();
        else{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("ListOfTeamPlayers.pdf"));

            document.open();
            Font title = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLACK);
            Font subtitle = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font text = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
            Chunk chunk2 = new Chunk("List of " + teamsComboBox.getValue() + " players", title);
            document.add(new Paragraph(chunk2));
            document.add(Chunk.NEWLINE );

            for(int i = 0; i < toPrint.size(); i++){
                Chunk chunk = new Chunk(i+1 + ". " + toPrint.get(i), text);
                document.add(new Paragraph(chunk));
                document.add(Chunk.NEWLINE );
            }

            com.itextpdf.text.Image img  = com.itextpdf.text.Image.getInstance(path);
            img.setAbsolutePosition(440,650);
            img.scaleToFit(100,100);
            document.add(img);

            document.close();
            toPrint.clear();
        }




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxSeasons();
        initComboBoxTeams();
        setInvisibleListViewPlayers();
        helpBtn.setImage(new Image("/help.png"));
    }
}
