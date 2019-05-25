package nba_statistics.others;

import javafx.scene.control.Alert;

public class Alerts {

    public static void getAlertTeams(String ...team){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        if (team.length == 1){

            errorAlert.setHeaderText("Team '" + team[0] + "' doesnt exist");
            errorAlert.setContentText("You must enter correct team name");

        } else if (team.length == 2){
            errorAlert.setHeaderText("Host team '" + team[0] + "' and away team '" + team[1] + "' are the same");
            errorAlert.setContentText("You must enter various team name");
        }
        errorAlert.showAndWait();
    }


    public static void getAlertPlayer(String p1, String p2){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Player '" + p1 + " " + p2 +"' doesnt exist");
        errorAlert.setContentText("You must enter correct player name");
        errorAlert.showAndWait();
    }

    public static void getAlertSeason(String s){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Season '" + s + "' doesnt exist");
        errorAlert.setContentText("You must enter season name which exist in database");
        errorAlert.showAndWait();
    }

    public static void getAlertChoiceMatch(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("You don't choice a match");
        errorAlert.setContentText("You must choice a match before click 'OK'");
        errorAlert.showAndWait();
    }

    public static void getAlertSeasonRepeat(String s){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Season '" + s + "' exist in database");
        errorAlert.setContentText("You must enter unique season name or click 'SKIP' to choose this season");
        errorAlert.showAndWait();
    }
    public static void getAlertTeam(String t){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Team '" + t + "' exist in database");
        errorAlert.setContentText("You must enter unique team name");
        errorAlert.showAndWait();
    }

    public static void getAlertDate(String ...d){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        if (d.length == 0){
            errorAlert.setTitle("ERROR");
            errorAlert.setHeaderText("Start date is later than end date!");
            errorAlert.setContentText("Start date must be earlier than end date");
            errorAlert.showAndWait();
        } else if (d.length == 1){
            errorAlert.setTitle("ERROR");
            errorAlert.setHeaderText("Date '" + d[0] + "' is incorrect format");
            errorAlert.setContentText("Correct date format: RRRR-MM-DD");
            errorAlert.showAndWait();
        }
    }
    public static void getAlertFloat() {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Height or/and weight are incorrect number format");
        errorAlert.setContentText("Height and weight must be float number");
        errorAlert.showAndWait();
    }
    public static void getAlertDivision(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Choose division");
        errorAlert.setContentText("");
        errorAlert.showAndWait();
    }
    public static void getAlertConference(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Choose conference");
        errorAlert.setContentText("");
        errorAlert.showAndWait();
    }
}
