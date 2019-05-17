package nba_statistics.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
This is controller for MainView.fxml
 */
public class MainController implements Initializable {

    @FXML
    private Button loginBtn;


    public void onActionButton(ActionEvent actionEvent){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/Preseason.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void changeScreenToReviewer(ActionEvent event) throws IOException {
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        Scene reviewerScene = new Scene(reviewerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }
}
