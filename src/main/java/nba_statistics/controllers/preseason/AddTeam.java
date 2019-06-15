package nba_statistics.controllers.preseason;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nba_statistics.services.TeamsService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static nba_statistics.others.Alerts.*;
import static nba_statistics.others.Alerts.getAlertTeam;

public class AddTeam implements Initializable {
    @FXML
    private ComboBox<String> DivW; @FXML private ComboBox<String> DivE;
    @FXML private Text d10; @FXML private Text d11; @FXML private Text d12; @FXML private Text d13;
    @FXML private ComboBox<String> t10;@FXML private TextField t12; @FXML private TextField t13;
    @FXML private Button sendBtn; @FXML private Button pictureBtn;  @FXML private ImageView image;
    @FXML private Text enterDataTxt; @FXML private Button BackBtn;

    private ObservableList<String> conference = FXCollections.observableArrayList
            ("Eastern", "Western");

    private ObservableList<String> divisionEastern = FXCollections.observableArrayList
            ("Atlantic", "Central", "Southeast");

    private ObservableList<String> divisionWestern = FXCollections.observableArrayList
            ("Northwest", "Pacific", "Southwest");

    private String currImageURL="";
    private String currSeason;

    public void setCurrSeason(String currSeason) {this.currSeason = currSeason; }

    @SuppressWarnings("Duplicates")
    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/SelectionView.fxml"));
        Parent accountParent = loader.load();

        Selection controller = loader.getController();
        controller.setCurrSeasonTmp(currSeason);
        //controller.init();

        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
        //logOutBtn.getScene().getWindow().hide();
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
    private void clearFields(){
        information(1);
        image.setImage(null);
        currImageURL = "";
        t12.clear();
        t13.clear();
        t10.getSelectionModel().clearSelection();
        DivE.setVisible(false);
        DivW.setVisible(false);
    }
    @SuppressWarnings("Duplicates")
    public void sendToDatabase(){

        TeamsService teamsService = new TeamsService();
        if (!currImageURL.equals("")) {
            if (teamsService.checkTeam(t12.getText())) {
                if (t10.getValue() == "Eastern") {
                    if (DivE.getValue() == null)
                        getAlertDivision();
                    else {
                        if (teamsService.getData(t10.getValue(), DivE.getValue(), t12.getText(), t13.getText(), currImageURL)) {
                            clearFields();
                        } else getAlertImage();
                    }
                } else if (t10.getValue() == "Western") {
                    if (DivW.getValue() == null)
                        getAlertDivision();
                    else {
                        if (teamsService.getData(t10.getValue(), DivW.getValue(), t12.getText(), t13.getText(), currImageURL)) {
                            clearFields();
                        } else getAlertImage();
                    }
                } else {
                    getAlertConference();
                }
            } else
                getAlertTeam(t12.getText());
        }else
            getAlertNoImage();
    }
    @SuppressWarnings("Duplicates")
    public void setImage(Event event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Open Resource File");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        if (file!=null) {
            currImageURL = file.toURI().toString();
            image.setImage(new Image(currImageURL));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DivE.setItems(divisionEastern);
        DivW.setItems(divisionWestern);
        t10.setItems(conference);
        t10.getSelectionModel().clearSelection();
        DivE.setVisible(false);DivW.setVisible(false);
    }
}
