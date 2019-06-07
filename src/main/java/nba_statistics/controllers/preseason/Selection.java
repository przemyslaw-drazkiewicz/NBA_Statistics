package nba_statistics.controllers.preseason;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import nba_statistics.controllers.AccountController;
import nba_statistics.controllers.statistician.Statistician2;

import java.io.IOException;

public class Selection {
    @FXML private Button addMatchBtn;
    @FXML private Button addTeamBtn;
    @FXML private Button addPlayerBtn;
    @FXML private Button transferPlayerBtn;
    @FXML private Button logOutBtn;
    @FXML private Button logOutBtn1;

    private String currSeasonTmp;
    public void setCurrSeasonTmp(String currSeasonTmp){
        this.currSeasonTmp = currSeasonTmp;
    }
    private String currSeasonTmp2;
    public void setCurrSeasonTmp2(String currSeasonTmp2){
        this.currSeasonTmp2 = currSeasonTmp2;
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
/*        AddMatch controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp);
        controller.init();*/
        createScene(accountParent,event);
    }

    public void changeScreenToTeam(Event event) throws IOException {
        Parent adminParent = FXMLLoader.load(getClass().getResource("/Preseason/AddTeamView.fxml"));
        createScene(adminParent,event);
    }

    public void changeScreenToAddPlayer(Event event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/Preseason/AddPlayerView.fxml"));
        createScene(preseasonParent,event);
    }

    public void changeScreenToTransferPlayer(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Preseason/AddTransferPlayerView.fxml"));
        Parent accountParent = loader.load();
/*        AddTransferPlayer controller = loader.getController();
        controller.setCurrSeason(currSeasonTmp2);
        controller.init();*/
        createScene(accountParent,event);
    }
}
