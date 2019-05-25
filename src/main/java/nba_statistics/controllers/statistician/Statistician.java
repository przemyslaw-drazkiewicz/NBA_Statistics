package nba_statistics.controllers.statistician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.text.Text;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import nba_statistics.services.MatchesService;

import static nba_statistics.others.Alerts.*;

public class Statistician implements Initializable {

    @FXML
    private ComboBox<String> matchesComboBox;

    @FXML
    private Text choiceText;

    @FXML
    private Text matchText;

    @FXML
    private Button buttonOK;

    @FXML
    private Button buttonBack;

    private List<String> matchesT;

    private ObservableList<String> choice;

    private Mecze match;

    private Visibility v;

    private Date date = new Date();

    private List<Mecze> matchs;


    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    public void onClickButtonOK() {
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
            Mecze match = matchs.get(matchSelected);

            Druzyny teamH = match.getDruzGosp();
            Druzyny teamA = match.getDruzGosc();

            String matchT2 = teamH.getNazwa() + " vs. " + teamA.getNazwa();
            matchText.setText(matchT2);
            v.setInvisibleC(choiceText, buttonOK, matchesComboBox);
            buttonBack.setVisible(true);
            matchText.setVisible(true);
        }
    }

    public void onClickButtonBack() {
        v.setVisibleC(choiceText,buttonOK,matchesComboBox);
        buttonBack.setVisible(false);
        matchText.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        v = new Visibility();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);

        MatchesService matchesDao = new MatchesService();
        matchs = matchesDao.findAllAtDate(dateS);

        matchesT = new ArrayList<>();

        for (Mecze mecze : matchs) {
            match = mecze;

            Druzyny teamH = match.getDruzGosp();
            Druzyny teamA = match.getDruzGosc();

            String matchT = teamH.getNazwa() + " vs. " + teamA.getNazwa();
            matchesT.add(matchT);
        }


        choice = FXCollections.observableArrayList
                (matchesT);
        matchesComboBox.setItems(choice);

    }
}
