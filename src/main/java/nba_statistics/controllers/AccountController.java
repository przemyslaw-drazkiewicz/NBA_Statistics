package nba_statistics.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.entities.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {

    @FXML
    private Text loginText;
    @FXML
    private Button viewButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button preButton;
    @FXML
    private Button statButton;
    @FXML
    private Button logoutButton;



    private static Users user;


    private void setVisibility(){
        switch(user.getRole().getName()){
            case "PRESEASON":
                statButton.setDisable(true);
                adminButton.setVisible(false);
                break;
            case "STATISTICIAN":
                preButton.setDisable(true);
                adminButton.setVisible(false);
                break;
            case "VIEWER":
                statButton.setDisable(true);
                preButton.setDisable(true);
                adminButton.setVisible(false);
                break;
        }
    }
    public void init(Users user){
        this.user=user;
        loginText.setText("Logged in user: "+this.user.getLogin());
        setVisibility();
    }

    public static Users getUser() {
        return user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

//    public AccountController(Users loggedInUser){
//        user=loggedInUser;
//    }

    private void createScene(Parent parent,Event event){

        Scene preseasonScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void logout(Event event) throws IOException {
        Parent mainParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        createScene(mainParent,event);
    }
//    public void changeScreen(Event event) throws IOException {
//        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/Preseason.fxml"));
//        createScene(preseasonParent,event);
//    }
    public void changeScreenToStatistician(Event event) throws IOException {
        Parent statisticianParent = FXMLLoader.load(getClass().getResource("/Statistician.fxml"));
        createScene(statisticianParent,event);
    }
    public void changeScreenToAdmin(Event event) throws IOException {
        Parent adminParent = FXMLLoader.load(getClass().getResource("/AdminView.fxml"));
        createScene(adminParent,event);
    }
    public void changeScreenToPreseason(Event event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/Preseason/AddSeasonView.fxml"));
        createScene(preseasonParent,event);
    }

    public void changeScreenToReviewer(Event event) throws IOException {
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/DataViewer.fxml"));
        createScene(reviewerParent,event);
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            try {
                logout(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void onKeyPressedPre(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                changeScreenToPreseason(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void onKeyPressedStat(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                changeScreenToStatistician(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void onKeyPressedView(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                changeScreenToReviewer(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void onKeyPressedAdmin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                changeScreenToAdmin(event);
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

}
