package nba_statistics.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.services.MatchesService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class DataViewer implements Initializable {

    @FXML
    private Button LogOutBtn;


    @FXML private Text dataViewer;
    @FXML private Text question;
    @FXML private Button viewDatabaseBtn;



    public void changeScreen(ActionEvent event) throws IOException {
        //Parent preseasonParent = FXMLLoader.load(getClass().getResource("/AccountView.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = (Parent)loader.load();
        AccountController accountController = loader.getController();
        accountController.init(AccountController.getUser());
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void changeScreenToSelectData(ActionEvent event) throws IOException {
        Parent selectDataParent = FXMLLoader.load(getClass().getResource("/SelectData.fxml"));
        Scene selectDataScene = new Scene(selectDataParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(selectDataScene);
        window.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




}
