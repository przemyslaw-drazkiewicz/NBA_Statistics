package nba_statistics.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.services.UsersService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static nba_statistics.others.Alerts.information;

public class RegisterController implements Initializable {
    @FXML  private TextField userField; @FXML private PasswordField passwordField; @FXML private PasswordField passwordField2;
    @FXML private Text errorText; @FXML private ImageView helpBtn;

    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/MainView.fxml"));
        Parent registerParent = loader.load();
        Scene registerScene = new Scene(registerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helpBtn.setImage(new Image("/help.png"));
    }

    private boolean checkFields(TextField tf1, PasswordField tf2, PasswordField tf3){
        if (tf1.getText().equals("")||tf2.getText().equals("")|| tf3.getText().equals(""))
            return false;
        return true;
    }
    public void addUser(Event event){
        UsersService usersService = new UsersService();
        if(!checkFields(userField,passwordField,passwordField2)){
            errorText.setVisible(true);
        }else{
                String addUserResult=usersService.addUser(userField.getText(),passwordField.getText(),passwordField2.getText());
                if (addUserResult.equals("Correct")){
                    try {
                        information(6);
                        changeScreen(event);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }else{
                    errorText.setText(addUserResult);
                    errorText.setVisible(true);
                }
        }
    }
    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/RegisterView.fxml");
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
    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addUser(event);
        }
        if (event.getCode() == KeyCode.ESCAPE) {
            try{
                changeScreen(event);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
