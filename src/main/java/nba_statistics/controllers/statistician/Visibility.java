package nba_statistics.controllers.statistician;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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

    public void setInvisibleM(Button oneH, Button twoH, Button threeH, Button oneA, Button twoA, Button threeA, Button BH, Button BA, Button RH, Button RA, Button FH, Button FA, Button TFH, Button TFA, Button SH, Button SA, ListView homeTeam,ListView awayTeam, Button buttonBack, Text matchText)
    {
        oneH.setVisible(false);
        twoH.setVisible(false);
        threeH.setVisible(false);
        oneA.setVisible(false);
        twoA.setVisible(false);
        threeA.setVisible(false);
        BH.setVisible(false);
        BA.setVisible(false);
        RH.setVisible(false);
        RA.setVisible(false);
        FH.setVisible(false);
        FA.setVisible(false);
        TFH.setVisible(false);
        TFA.setVisible(false);
        SH.setVisible(false);
        SA.setVisible(false);
        homeTeam.setVisible(false);
        awayTeam.setVisible(false);
        buttonBack.setVisible(false);
        matchText.setVisible(false);
    }

    public void setVisibleM(Button oneH, Button twoH, Button threeH, Button oneA, Button twoA, Button threeA, Button BH, Button BA, Button RH, Button RA, Button FH, Button FA, Button TFH, Button TFA, Button SH, Button SA, ListView homeTeam,ListView awayTeam, Button buttonBack, Text matchText)
    {
        oneH.setVisible(true);
        twoH.setVisible(true);
        threeH.setVisible(true);
        oneA.setVisible(true);
        twoA.setVisible(true);
        threeA.setVisible(true);
        BH.setVisible(true);
        BA.setVisible(true);
        RH.setVisible(true);
        RA.setVisible(true);
        FH.setVisible(true);
        FA.setVisible(true);
        TFH.setVisible(true);
        TFA.setVisible(true);
        SH.setVisible(true);
        SA.setVisible(true);
        homeTeam.setVisible(true);
        awayTeam.setVisible(true);
        buttonBack.setVisible(true);
        matchText.setVisible(true);
    }
}
