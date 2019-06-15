package nba_statistics.controllers.preseason;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Selection {

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
}
