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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Preseason implements Initializable {

    @FXML
    private Button LogOutBtn;

    @FXML
    private Spinner spinner;

    @FXML
    private CheckBox newPlayerCheckBox;

    @FXML
    private CheckBox transferCheckBox;

    @FXML
    private Button selected;

    @FXML
    private Text enterDataTxt;

    @FXML private Text d10; @FXML private Text d11; @FXML private Text d12; @FXML private Text d13;
    @FXML private TextField t10; @FXML private TextField t11; @FXML private TextField t12; @FXML private TextField t13;

    @FXML private Text m10; @FXML private Text m11; @FXML private Text m12;
    @FXML private TextField t20; @FXML private TextField t21; @FXML private TextField t22;

    @FXML private Text s10; @FXML private Text s11; @FXML private Text s12; @FXML private Text s13;
    @FXML private TextField t30; @FXML private TextField t31; @FXML private TextField t32;

    @FXML private Text p40; @FXML private Text p41; @FXML private Text p42; @FXML private Text p43; @FXML private Text p44; @FXML private Text p45;
    @FXML private TextField t40; @FXML private TextField t41; @FXML private TextField t42;@FXML private TextField t43; @FXML private TextField t44;@FXML private TextField t45;

    @FXML private Button sendBtn; @FXML private Button addedSeasonBtn; @FXML private Button skipBtn;


    private void setVisibleD(){
        d10.setVisible(true);d11.setVisible(true);d12.setVisible(true);d13.setVisible(true);
        t10.setVisible(true);t11.setVisible(true);t12.setVisible(true);t13.setVisible(true);

    }
    private void setInvisibleD(){
        d10.setVisible(false);d11.setVisible(false);d12.setVisible(false);d13.setVisible(false);
        t10.setVisible(false);t11.setVisible(false);t12.setVisible(false);t13.setVisible(false);
    }

    private void setVisibleM(){
        m10.setVisible(true);m11.setVisible(true);m12.setVisible(true);
        t20.setVisible(true);t21.setVisible(true);t22.setVisible(true);

    }
    private void setInvisibleM(){
        m10.setVisible(false);m11.setVisible(false);m12.setVisible(false);
        t20.setVisible(false);t21.setVisible(false);t22.setVisible(false);
    }

    private void setInvisibleS(){
        s10.setVisible(false);s11.setVisible(false);s12.setVisible(false); s13.setVisible(false);
        t30.setVisible(false);t31.setVisible(false);t32.setVisible(false);
        addedSeasonBtn.setVisible(false);
    }

    private void setInvisibleP(){
        p40.setVisible(false);p41.setVisible(false);p42.setVisible(false);p43.setVisible(false);p44.setVisible(false);p45.setVisible(false);
        t40.setVisible(false);t41.setVisible(false);t42.setVisible(false);t43.setVisible(false);t44.setVisible(false);t45.setVisible(false);
    }
    private void setVisibleNewPlayerT(){
        p40.setVisible(true);p41.setVisible(true);p42.setVisible(true);p43.setVisible(true);p44.setVisible(true);p45.setVisible(true);
        t40.setVisible(true);t41.setVisible(true);t42.setVisible(true);t43.setVisible(true);t44.setVisible(true);t45.setVisible(true);
    }

    private void setVisibleTransferT(){
        p40.setVisible(true);p41.setVisible(true);p42.setVisible(true);
        t40.setVisible(true);t41.setVisible(true);t42.setVisible(true);
    }
    private void setVisibleCheckBox(){
        newPlayerCheckBox.setVisible(true);transferCheckBox.setVisible(true);
    }
    private void setInvisibleCheckBox(){
        newPlayerCheckBox.setVisible(false);transferCheckBox.setVisible(false);
    }

    private void clearTextFieldD(){
        t10.clear(); t11.clear();t12.clear();t13.clear();
    }
    private void clearTextFieldM(){
        t20.clear(); t21.clear();t22.clear();
    }
    private void clearTextFieldNewPlayer(){
        t40.clear(); t41.clear();t42.clear();t43.clear();t44.clear();t45.clear();
    }
    private void clearTextFieldTransfer(){
        t40.clear(); t41.clear();t42.clear();
    }

    private String state;
    private boolean addSeasonState = false;
    private String currSeason;

    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }


    void initScene(){ //init scene after added season

        spinner.setId("spinner");
        List<Object> names = new ArrayList<Object>();
        names.add("Team");
        names.add("Match");
        names.add("Player");
        spinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Object>(FXCollections.observableArrayList(names)));
        setInvisibleS();
        skipBtn.setVisible(false);
        spinner.setVisible(true);
        selected.setVisible(true);
        enterDataTxt.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addedSeason(){

        SeasonsService seasonsDao = new SeasonsService();
        seasonsDao.getData(t30.getText(), t31.getText(), t32.getText());
        currSeason = t30.getText();
        addSeasonState = true;
        initScene();

    }

    public void skipAction(){
        addSeasonState = true;
        currSeason = t30.getText(); //get season but not save to database 'this season exist in database'
        initScene();
    }

    public void onSelected()
    {

        selected.setVisible(true);
       switch (spinner.getValue().toString()){
           case "Team":
               setInvisibleCheckBox();
               setInvisibleM();
               setInvisibleP();
               setInvisibleS();
               setVisibleD();
               state = "Team";
               break;

           case "Match":
               setInvisibleCheckBox();
               setInvisibleD();
               setInvisibleP();
               setInvisibleS();
               setVisibleM();
               state = "Match";
            break;

           case "Player":
               setInvisibleD();
               setInvisibleS();
               setInvisibleM();
               setVisibleCheckBox();
               break;



       }
       sendBtn.setVisible(true);
    }
    public void addNewPlayer(){
        setInvisibleP();
        setVisibleNewPlayerT();
        transferCheckBox.setSelected(false);
        state = "NewPlayer";
    }

    public void changeTeam(){
        setInvisibleP();
        setVisibleTransferT();
        state = "Transfer";
        newPlayerCheckBox.setSelected(false);
    }
    public void sendToDatabase(){
        switch (state){
            case "Team":
                TeamsService teamsService = new TeamsService();
                teamsService.getData(t10.getText(), t11.getText(), t12.getText(), t13.getText());
                clearTextFieldD();
                break;
            case "Match":
                MatchesService matchesService = new MatchesService();
                matchesService.getData(t20.getText(),t21.getText(), t22.getText(), currSeason);
                clearTextFieldM();
                break;
            case "NewPlayer":
                PlayersService playersService = new PlayersService();
                playersService.getData(t40.getText(), t41.getText(), t43.getText(), Float.parseFloat(t44.getText()), Float.parseFloat(t45.getText()),t42.getText());
                clearTextFieldNewPlayer();
                break;
            case "Transfer":
                PlayersService playersService1 = new PlayersService();
                playersService1.updatePlayer(t40.getText(), t41.getText(), t42.getText());
                clearTextFieldTransfer();

                break;
        }
    }
}
