package nba_statistics.controllers.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nba_statistics.controllers.AccountController;
import nba_statistics.controllers.HelpController;
import nba_statistics.entities.Users;
import nba_statistics.services.RolesService;
import nba_statistics.services.UsersService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML private Button LogOutBtn;
    @FXML private Button btnApply;
    @FXML private ComboBox<String> userBox;
    @FXML private ComboBox<String> roleBox;
    @FXML private TextField userField;
    @FXML private Text empFieldText;
    @FXML private Text okText;
    @FXML private Text invUserText;
    @FXML private ImageView helpBtn;

    private Users currUser;

    private ObservableList<String> userLogins;

    private ObservableList<String> roleNames;

    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = (Parent)loader.load();
        AccountController accountController = loader.getController();
        accountController.init(AccountController.getUser());
        Scene parentScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(parentScene);
        window.show();
    }

    private void initUserLogins()
    {
        UsersService usersService = new UsersService();
        ArrayList<String> listUsersLogin = usersService.findAllLogin();
        userLogins = FXCollections.observableArrayList(listUsersLogin);
        userBox.setItems(userLogins);
    }

    private void initRoleNames()
    {
        RolesService rolesService = new RolesService();
        ArrayList<String> roleNameList = rolesService.findAllRoleName();
        roleNames = FXCollections.observableArrayList(roleNameList);
        roleBox.setItems(roleNames);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUserLogins();
        initRoleNames();
        helpBtn.setImage(new Image("/help.png"));
    }

    public void onClickApply(){
        okText.setVisible(false);
        invUserText.setVisible(false);
        empFieldText.setVisible(false);
        UsersService usersService = new UsersService();
        String roleName = roleBox.getValue();
        String userLogin = userBox.getValue();
        if(roleName!=null && userLogin!=null){
            if(!userLogin.equals("admin")){
                usersService.updateUser(userLogin, roleName);
                okText.setVisible(true);
            }
        }else if(roleName!=null && userLogin==null) {
            userLogin=userField.getText();
            if(!userLogin.equals("admin") && usersService.getUser(userLogin)!= null){
                usersService.updateUser(userLogin, roleName);
                okText.setVisible(true);
            }else{
                System.out.println("Unvalid user name");
                invUserText.setVisible(true);
            }
        }else{
            empFieldText.setVisible(true);
            System.out.println("Required field are not fill");
        }
        userBox.getSelectionModel().clearSelection();
        roleBox.getSelectionModel().clearSelection();
        userField.clear();
//        RolesService rolesService = new RolesService();
//        Users user = usersService.getUser(userLogin);
//        Roles role = rolesService.getRole(roleName);
//        user.setRole(role);
    }
    @SuppressWarnings("Duplicates")
    @FXML
    void helpClicked(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Help.fxml"));
        Parent accountParent = (Parent)loader.load();
        HelpController helpController = loader.getController();
        helpController.setView("/AdminView.fxml");
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
            onClickApply();
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
