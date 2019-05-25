package nba_statistics.controllers.statistician;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class Visibility {

    public void setInvisibleC(Text choiceText, Button buttonOK,ComboBox matchesComboBox)
    {
        choiceText.setVisible(false);
        buttonOK.setVisible(false);
        matchesComboBox.setVisible(false);
    }

    public void setVisibleC(Text choiceText, Button buttonOK,ComboBox matchesComboBox)
    {
        choiceText.setVisible(true);
        buttonOK.setVisible(true);
        matchesComboBox.setVisible(true);
    }
}
