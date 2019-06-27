package nba_statistics.controllers.preseason;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.HelpController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Selection implements Initializable {

    @FXML
    private ImageView helpBtn;
    private String currSeasonTmp;
    public void setCurrSeasonTmp(String currSeasonTmp){
        this.currSeasonTmp = currSeasonTmp;
    }


    private void createScene(Parent parent,Event event){
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void changeScreenSeason(Event event) throws IOException {
        Parent mainParent = FXMLLoader.load(getClass().getResource("/Preseason/AddSeasonView.fxml"));
        createScene(mainParent,event);
    }

    public void changeScreenToAddMatch(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/AddMatchView.fxml"));
        Parent accountParent = loader.load();
        AddMatch controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp);
        controller.init();
        createScene(accountParent,event);
    }

    public void changeScreenToTeam(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/AddTeamView.fxml"));
        Parent accountParent = loader.load();
        AddTeam controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp);
        createScene(accountParent,event);
    }

    public void changeScreenToAddPlayer(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/AddPlayerView.fxml"));
        Parent accountParent = loader.load();
        AddPlayer controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp);
        createScene(accountParent,event);
    }

    public void changeScreenToUpdatePlayer(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/UpdatePlayerView.fxml"));
        Parent accountParent = loader.load();
        UpdatePlayer controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp);
        controller.init();
        createScene(accountParent,event);
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/Preseason/SelectionView.fxml");
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
