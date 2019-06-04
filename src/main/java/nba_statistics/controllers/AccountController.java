package nba_statistics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.entities.Roles;
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
    public void logout(ActionEvent event) throws IOException {
        Parent mainParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(mainParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }
    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/Preseason.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.showAndWait();

       // window.show();
    }
    public void changeScreenToStatistician(ActionEvent event) throws IOException {
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/Statistician.fxml"));
        Scene reviewerScene = new Scene(reviewerParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }
    public void changeScreenToAdmin(ActionEvent event) throws IOException {
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/AdminView.fxml"));
        Scene reviewerScene = new Scene(reviewerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }
    public void changeScreenToPreseason(ActionEvent event) throws IOException {
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
