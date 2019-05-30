package nba_statistics.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.entities.Roles;
import nba_statistics.entities.Users;
import nba_statistics.services.UsersService;

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
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userField;
    @FXML
    private Text errorText;


    public void onActionButton(ActionEvent actionEvent){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void changeScreenToRegister(ActionEvent event)throws IOException{
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        Scene reviewerScene = new Scene(reviewerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }

    public void changeScreenToAccount(ActionEvent event, Users user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = (Parent)loader.load();
        AccountController accountController = loader.getController();
        accountController.init(user);
        loader.setController(accountController);
        //Parent accountParent = FXMLLoader.load(getClass().getResource("/AccountView.fxml"));
        //AccountController accountController = new AccountController(user);
        Scene reviewerScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }

    public void authorize(ActionEvent event){
        UsersService usersService = new UsersService();
        //Users user = usersService.getUser(userField.getText());
        Roles role = usersService.authorize(userField.getText(),passwordField.getText());
        if (role==null){
            errorText.setVisible(true);
        }else{
            try {
                switch (role.getName()) {
//                    case "VIEWER":{changeScreenToReviewer(event);break;}
//                    case "PRESEASON":{changeScreen(event);break;}
//                    case "STATISTICIAN":{changeScreenToStatistician(event);break;}
//                    case "ADMIN":{changeScreenToAdmin(event);break;}
                    default:{changeScreenToAccount(event,usersService.getUser(userField.getText()));break;}
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
