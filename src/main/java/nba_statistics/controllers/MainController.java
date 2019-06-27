package nba_statistics.controllers;

import javafx.application.Platform;
import javafx.event.Event;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    @FXML
    private ImageView helpBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helpBtn.setImage(new Image("/help.png"));
    }


    public void changeScreenToRegister(ActionEvent event)throws IOException{
        Parent reviewerParent = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        Scene reviewerScene = new Scene(reviewerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }

    public void changeScreenToAccount(Event event, Users user) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = (Parent)loader.load();
        AccountController accountController = loader.getController();
        accountController.init(user);
        loader.setController(accountController);
        Scene reviewerScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(reviewerScene);
        window.show();
    }


    public void authorize(Event event){
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

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
          authorize(event);
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            Platform.exit();
        }
    }
    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/MainView.fxml");
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

}
