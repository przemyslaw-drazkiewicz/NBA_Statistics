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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button selected;


    @FXML private Text d10; @FXML private Text d11; @FXML private Text d12; @FXML private Text d13;
    @FXML private TextField t10; @FXML private TextField t11; @FXML private TextField t12; @FXML private TextField t13;

    @FXML private Text m10; @FXML private Text m11; @FXML private Text m12; @FXML private Text m13;
    @FXML private TextField t20; @FXML private TextField t21; @FXML private TextField t22; @FXML private TextField t23;

    @FXML private Text s10; @FXML private Text s11; @FXML private Text s12;
    @FXML private TextField t30; @FXML private TextField t31; @FXML private TextField t32;

    @FXML private Button sendBtn;

    private void setVisibleD(){
        d10.setVisible(true);d11.setVisible(true);d12.setVisible(true);d13.setVisible(true);
        t10.setVisible(true);t11.setVisible(true);t12.setVisible(true);t13.setVisible(true);

    }
    private void setInvisibleD(){
        d10.setVisible(false);d11.setVisible(false);d12.setVisible(false);d13.setVisible(false);
        t10.setVisible(false);t11.setVisible(false);t12.setVisible(false);t13.setVisible(false);
    }

    private void setVisibleM(){
        m10.setVisible(true);m11.setVisible(true);m12.setVisible(true);m13.setVisible(true);
        t20.setVisible(true);t21.setVisible(true);t22.setVisible(true);t23.setVisible(true);

    }
    private void setInvisibleM(){
        m10.setVisible(false);m11.setVisible(false);m12.setVisible(false);m13.setVisible(false);
        t20.setVisible(false);t21.setVisible(false);t22.setVisible(false);t23.setVisible(false);
    }

    private void setVisibleS(){
        s10.setVisible(true);s11.setVisible(true);s12.setVisible(true);
        t30.setVisible(true);t31.setVisible(true);t32.setVisible(true);

    }
    private void setInvisibleS(){
        s10.setVisible(false);s11.setVisible(false);s12.setVisible(false);
        t30.setVisible(false);t31.setVisible(false);t32.setVisible(false);
    }


    public void changeScreen(ActionEvent event) throws IOException {
        Parent preseasonParent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene preseasonScene = new Scene(preseasonParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(preseasonScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        spinner.setId("spinner");
        List<Object> names = new ArrayList<Object>();
        names.add("Druzyna");
        names.add("Mecz");
        names.add("Sezon");
        spinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Object>(FXCollections.observableArrayList(names)));
    }

    public void onSelected()
    {
        selected.setVisible(true);
       switch (spinner.getValue().toString()){
           case "Druzyna":
               setInvisibleM();
               setInvisibleS();
               setVisibleD();
               break;

           case "Mecz":
               setInvisibleD();
               setInvisibleS();
               setVisibleM();
               break;

           case "Sezon":
               setInvisibleM();
               setInvisibleD();
               setVisibleS();
               break;



       }
       sendBtn.setVisible(true);
    }
}
