package nba_statistics.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.text.Text;

public class Statistician implements Initializable {

    @FXML
    private ComboBox<String> matchesComboBox;

    @FXML
    private Text dateText;

    private ObservableList<String> choice;



    private Date date = new Date();


    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = formatter.format(date);
        dateText.setText(dateS);

        choice = FXCollections.observableArrayList
                ("Match", "Player", "Team");
        matchesComboBox.setItems(choice);

    }
}
