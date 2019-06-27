package nba_statistics.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.controllers.preseason.*;
import nba_statistics.entities.Users;
import nba_statistics.services.HelpService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController {
@FXML private Text txt;

private String view;
public void setView(String view){
    this.view = view;
}

    public void init(){
        HelpService helpService = new HelpService();
        txt.setText(view + "\n" + helpService.getText( view));
    }


    @SuppressWarnings("Duplicates")
    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        Parent accountParent = loader.load();
/*        switch (view){
            case "/AccountView.fxml":
                AccountController accountController = loader.getController();
                accountController.init(AccountController.getUser());
                break;
            case "/Preseason/SelectionView.fxml":
                Selection selection = loader.getController();
                selection.setCurrSeasonTmp(currSeason);
                break;
            case "/Preseason/AddMatchView.fxml":
                AddMatch addMatch = loader.getController();
                addMatch.setCurrSeason(currSeason);
                addMatch.init();
                break;
            case "/Preseason/AddPlayerView.fxml":
                AddPlayer addPlayer = loader.getController();
                addPlayer.setCurrSeason(currSeason);
                break;
            case "/Preseason/AddTeamView.fxml":
                AddTeam addTeam = loader.getController();
                addTeam.setCurrSeason(currSeason);
                break;
            case "/Preseason/UpdatePlayerView.fxml":
                UpdatePlayer updatePlayer = loader.getController();
                updatePlayer.setCurrSeason(currSeason);
                updatePlayer.init();
                break;

        }*/
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }
}
