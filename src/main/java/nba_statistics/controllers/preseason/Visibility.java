package nba_statistics.controllers.preseason;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class Visibility {

    public void setVisibleD(Text d10, Text d11, Text d12, Text d13, Text p46, ComboBox<String> t10, TextField t12, TextField t13, Button imageBtn, ImageView image){
        d10.setVisible(true);d11.setVisible(true);d12.setVisible(true);d13.setVisible(true);image.setVisible(true);
        t10.setVisible(true);t12.setVisible(true);t13.setVisible(true);p46.setVisible(true);imageBtn.setVisible(true);

    }
    public void setInvisibleD(Text d10, Text d11, Text d12, Text d13, ComboBox<String> t10, TextField t12, TextField t13, ComboBox<String> DivE, ComboBox<String> DivW ){
        d10.setVisible(false);d11.setVisible(false);d12.setVisible(false);d13.setVisible(false);
        t10.setVisible(false);DivE.setVisible(false);DivW.setVisible(false);t12.setVisible(false);t13.setVisible(false);
    }

    public void setVisibleM(Text m10, Text m11, Text m12, Text tSeason,Text tSeason0, ComboBox<String> t20, ComboBox<String> t21, TextField t22,Text tDuration0,Text tDuration){
        m10.setVisible(true);m11.setVisible(true);m12.setVisible(true); tSeason.setVisible(true); tDuration.setVisible(true);
        t20.setVisible(true);t21.setVisible(true);t22.setVisible(true); tSeason0.setVisible(true);tDuration0.setVisible(true);

    }
    public void setInvisibleM(Text m10, Text m11, Text m12, Text tSeason,Text tSeason0, ComboBox<String> t20, ComboBox<String> t21, TextField t22,Text tDuration0,Text tDuration){
        m10.setVisible(false);m11.setVisible(false);m12.setVisible(false); tSeason.setVisible(false); tDuration0.setVisible(false);
        t20.setVisible(false);t21.setVisible(false);t22.setVisible(false); tSeason0.setVisible(false); tDuration.setVisible(false);
    }

    public void setInvisibleS(Text s10, Text s11, Text s12, Text s13, ComboBox comboBox, TextField t30, TextField t31, TextField t32, Button addedSeasonBtn ){
        s10.setVisible(false);s11.setVisible(false);s12.setVisible(false); s13.setVisible(false);
        t30.setVisible(false);t31.setVisible(false);t32.setVisible(false);comboBox.setVisible(false);
        addedSeasonBtn.setVisible(false);
    }

    public void setInvisibleP(Text p40,Text p41,Text p42,Text p43,Text p44,Text p45, Text p46, TextField t40, TextField t41, ComboBox<String> t42,TextField t43, TextField t44, TextField t45, Text p, TextField pp, Button b, ImageView image){
        p40.setVisible(false);p41.setVisible(false);p42.setVisible(false);p43.setVisible(false);p44.setVisible(false);p45.setVisible(false); p46.setVisible(false);p.setVisible(false);
        t40.setVisible(false);t41.setVisible(false);t42.setVisible(false);t43.setVisible(false);t44.setVisible(false);t45.setVisible(false); pp.setVisible(false);b.setVisible(false);image.setVisible(false);
    }
    public void setVisibleNewPlayerT(Text p40,Text p41,Text p42,Text p43,Text p44,Text p45, Text p46,  TextField t40, TextField t41, ComboBox<String> t42,TextField t43, TextField t44, TextField t45, Button b, ImageView image){
        p40.setVisible(true);p41.setVisible(true);p42.setVisible(true);p43.setVisible(true);p44.setVisible(true);p45.setVisible(true); p46.setVisible(true);
        t40.setVisible(true);t41.setVisible(true);t42.setVisible(true);t43.setVisible(true);t44.setVisible(true);t45.setVisible(true); b.setVisible(true); image.setVisible(true);
    }

    public void setVisibleTransferT(Text playerText,Text p41, Text p46,TextField playerField, ComboBox<String> t42,Button pictureBtn, ImageView image){
        playerText.setVisible(true);p41.setVisible(true); image.setVisible(true);p46.setVisible(true);
        playerField.setVisible(true);t42.setVisible(true);pictureBtn.setVisible(true);
    }

    public void setVisibleCheckBox(CheckBox newPlayerCheckBox, CheckBox transferCheckBox){
        newPlayerCheckBox.setVisible(true);
        transferCheckBox.setVisible(true);
    }
    public void setInvisibleCheckBox(CheckBox newPlayerCheckBox, CheckBox transferCheckBox){
        newPlayerCheckBox.setVisible(false);transferCheckBox.setVisible(false);
    }

    public void clearTextFieldD(TextField t12, TextField t13){
        t12.clear();t13.clear();
    }

    public void clearTextFieldM(TextField t22){
        t22.clear();
    }

    public void clearTextFieldNewPlayer(TextField t40, TextField t41,TextField t43, TextField t44, TextField t45){
        t40.clear(); t41.clear();t43.clear();t44.clear();t45.clear();
    }

    public void clearTextFieldTransfer(TextField playerField){
        playerField.clear();
    }

    public void setVisibleNewSeason(Text s10, Text s11, Text s12,  TextField t30, TextField t31, TextField t32, Button a){
        s10.setVisible(true); s11.setVisible(true); s12.setVisible(true);
        t30.setVisible(true); t31.setVisible(true); t32.setVisible(true);
        a.setVisible(true);
    }
    public void setVisibleExistingSeason(Text s10, Text s11, Text s12,  ComboBox t30, TextField t31, TextField t32, Button a){
        s10.setVisible(true); s11.setVisible(false); s12.setVisible(false);
        t30.setVisible(true); t31.setVisible(false); t32.setVisible(false);
        a.setVisible(true);
    }
    public void setInvisibleRadioButtons(RadioButton r, RadioButton r1){
        r.setVisible(false);
        r1.setVisible(false);
    }




}
