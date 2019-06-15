package nba_statistics.controllers.statistician;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.text.Text;
import nba_statistics.controllers.AccountController;
import nba_statistics.entities.*;
import nba_statistics.services.MatchesService;

import static nba_statistics.others.Alerts.*;

public class Statistician implements Initializable {

    @FXML private ComboBox<String> matchesComboBox;
    @FXML private Text choiceText;
    @FXML private Button buttonOK;

    private List<String> matchesT;

    private ObservableList<String> choice;

    private Matches match;

    private Visibility v;

    private Date date = new Date();

    private List<Matches> matches;


    public void changeScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AccountView.fxml"));
        Parent accountParent = loader.load();
        AccountController accountController = loader.getController();
        accountController.init(AccountController.getUser());
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void nextScreen(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistician2.fxml"));
        Parent accountParent = loader.load();
        Statistician2 controller = loader.getController();
        controller.setMatch(match);
        controller.init();
        Scene preseasonScene = new Scene(accountParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }


    public void onClickButtonOK(Event event) {
        int matchSelected = -1;
        String matchT = matchesComboBox.getValue();
        for(int i=0;i<matchesT.size();i++)
        {
            if(matchesT.get(i).equals(matchT))
            {
                matchSelected=i;
            }
        }
        if(matchSelected==-1)
        {
            getAlertChoiceMatch();
        }
        else {

            match = matches.get(matchSelected);

            try {
                nextScreen(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        v = new Visibility();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);

        MatchesService matchesDao = new MatchesService();
        matches = matchesDao.findAllAtDate(dateS);

        matchesT = new ArrayList<>();
        for (Matches matches : matches) {
            match = matches;

            Teams teamH = match.getHostTeam();
            Teams teamA = match.getGuestTeam();

            String matchT = teamH.getName() + " vs. " + teamA.getName();
            matchesT.add(matchT);
        }

        choice = FXCollections.observableArrayList(matchesT);
        matchesComboBox.setItems(choice);


    }


    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onClickButtonOK(event);
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
