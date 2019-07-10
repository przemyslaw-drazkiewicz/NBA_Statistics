package nba_statistics.controllers.viewer;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.Matches;
import nba_statistics.services.MatchesService;
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
import java.util.stream.Stream;

import static java.lang.String.*;
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
    @FXML
    private ImageView image;
    @FXML
    private ImageView helpBtn;
    @FXML
    private Stage stage;

    List<Matches> toPrint= new ArrayList<>();

    private TeamsService teamsService = new TeamsService();


    public void changeScreenBack(Event event) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene backScene = new Scene(backParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    private void setTable(List<Matches> listMatches) {

        if (listMatches.isEmpty()) {
            //image.setImage(null);
            getAlertEmptyListOfMatches();
        } else {
            matchesTable.setVisible(true);
            ObservableList<Matches> matchesObservableList = FXCollections.observableArrayList();

            matchesObservableList.addAll(listMatches);
            matchesTable.setItems(matchesObservableList);

            for(Matches m : listMatches){
                toPrint.add(m);
            }
        }
    }

    public void selectTimetable() {
        image.setImage(null);
        String team, season;
        matchesTable.setVisible(false);
        if ((season = seasonComboBox.getValue()) == null)
            getAlertComboBoxSeason();
        else if ((team = teamsComboBox.getValue()) == null) {
            getAlertComboBoxTeam();
        } else {
            image.setImage(new Image(teamsService.getTeam(team).getImageURL()));
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

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/Timetable.fxml");
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
            /*ObservableList<String> stringList = FXCollections.observableArrayList();
            stringList.add("a");
            stringList.add("b");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Copy of Report");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.setInitialFileName("Fourth report.txt");

            File file = fileChooser.showSaveDialog(stage);

            if(file!= null){
                FileWriter fileWriter = new FileWriter(file);
                for(int i = 0; i< toPrint.size();i++){

                    fileWriter.write(format("%20s %20s %20s %20s %20s %20s", "Host team: " + toPrint.get(i).getHostTeam().getName(),
                            "Away Team: " + toPrint.get(i).getGuestTeam().getName(),
                            "Date: " + toPrint.get(i).getDate(),
                            "Home points: " + toPrint.get(i).getHostPoints(),
                            "Away points: " + toPrint.get(i).getGuestPoints(),
                            "Extra time: " + toPrint.get(i).getExtraTimeCount()));
                    fileWriter.write(System.getProperty( "line.separator" ));
                }
                fileWriter.flush();
                fileWriter.close();
            }
            toPrint.clear();*/


            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream("Timetable.pdf"));

            Font title = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.BLACK);
            Font subtitle = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Font text = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

            document.open();

            Chunk chunk2 = new Chunk("Timetable in season " + seasonComboBox.getValue() + " for " + teamsComboBox.getValue() + " team", title);
            document.add(new Paragraph(chunk2));
            document.add(Chunk.NEWLINE );

            Chunk chunk3 = new Chunk(String.format("%16s %16s %15s %17s %15s %15s", "Home Team", "Away Team", "Date", "Home points", "Away points", "Extra time"), text);
            document.add(new Paragraph(chunk3));
            document.add(Chunk.NEWLINE );

            for(int i = 0; i < toPrint.size(); i++){
                Chunk chunk9 = new Chunk(String.format("%25s %25s %25s %27s %29s %30s", toPrint.get(i).getHostTeam().getName(), toPrint.get(i).getGuestTeam().getName(),
                        toPrint.get(i).getDate(), toPrint.get(i).getHostPoints(), toPrint.get(i).getGuestPoints(), toPrint.get(i).getExtraTimeCount()));

                document.add(new Paragraph(chunk9));
                document.add(Chunk.NEWLINE );

/*                Chunk chunk = new Chunk( String.format("%20s %20s", toPrint.get(i).substring(0, toPrint.get(i).lastIndexOf(
                        "\r\t\t\t\t\t\t Scored")) ,toPrint.get(i).substring(toPrint.get(i).indexOf(":")+1)), text);
                document.add(new Paragraph(chunk));
                document.add(Chunk.NEWLINE );*/
            }

            /*PdfPTable table = new PdfPTable(8);
            Stream.of("column header 1", "column header 2", "column header 3", "column header 1", "column header 2", "column header 3","column header 1")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });

            table.addCell("row 1, col 1");
            table.addCell("row 1, col 2");
            table.addCell("row 1, col 3");

            document.add(Chunk.NEWLINE );

            PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
            horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(horizontalAlignCell);

            PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
            verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            table.addCell(verticalAlignCell);

            document.add(table);*/

            document.close();
            toPrint.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboBoxSeasons();
        initComboBoxTeams();
        helpBtn.setImage(new Image("/help.png"));
        //init column in table view -> to TIMETABLE
        homeTeam.setCellValueFactory((TableColumn.CellDataFeatures<Matches, String> p) -> new ReadOnlyStringWrapper(p.getValue().getHostTeam().getName()));
        awayTeam.setCellValueFactory(p -> new ReadOnlyStringWrapper(p.getValue().getGuestTeam().getName()));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        homePoints.setCellValueFactory(new PropertyValueFactory<>("hostPoints"));
        awayPoints.setCellValueFactory(new PropertyValueFactory<>("guestPoints"));
        extraTime.setCellValueFactory(new PropertyValueFactory<>("extraTimeCount"));
    }
}
