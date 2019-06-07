package nba_statistics.controllers.preseason;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class Visibility {

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

}
