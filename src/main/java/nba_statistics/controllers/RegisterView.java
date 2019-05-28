package nba_statistics.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nba_statistics.others.Alerts;
import nba_statistics.services.UsersService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static nba_statistics.others.Alerts.information;

public class RegisterView implements Initializable {
    @FXML  private TextField userField; @FXML private PasswordField passwordField; @FXML private PasswordField passwordField2;
    @FXML private Text errorText;
    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private boolean checkFields(TextField tf1, PasswordField tf2, PasswordField tf3){
        if (tf1.getText().equals("")||tf2.getText().equals("")|| tf3.getText().equals(""))
            return false;
        return true;
    }
    public void addUser(ActionEvent event){
        UsersService usersService = new UsersService();
        if(!checkFields(userField,passwordField,passwordField2)){
            errorText.setVisible(true);
        }else{
                String addUserResult=usersService.addUser(userField.getText(),passwordField.getText(),passwordField2.getText());
                if (addUserResult.equals("Correct")){
                    try {
                        information(0);
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
}
