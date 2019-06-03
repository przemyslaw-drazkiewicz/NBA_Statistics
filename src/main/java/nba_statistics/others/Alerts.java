package nba_statistics.others;

import javafx.scene.control.Alert;

public class Alerts {

    public static void getAlertTeams(String ...team){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        if (team.length == 1){

            errorAlert.setHeaderText("Select team");
            errorAlert.setContentText("You must select team");

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

    public static void getAlertChoicePlayer(String s){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("You don't choice a player");
        errorAlert.setContentText("You must choice a player before click '" + s +"'");
        errorAlert.showAndWait();
    }

    public static void getAlertChoiceSquad(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("You don't choice a first squad");
        errorAlert.setContentText("You must choice a first squad of five players before click 'Confirm'");
        errorAlert.showAndWait();
    }

    public static void getAlertTooManyPlayersInSquad(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("You can't add this player");
        errorAlert.setContentText("You can add only five players to first squad");
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

    public static void getAlertDateBetween(String d){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Date '" + d + "' is incorrect");
        errorAlert.setContentText("Date must be date between start and end season");
        errorAlert.showAndWait();
    }

    public static void confirmation(int flag){

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("CONFIRMATION");
        switch (flag)
        {
            case 0:
                confirmation.setHeaderText("Added season");
                break;
            case 1:
                confirmation.setHeaderText("Added team");
                break;
            case 2:
                confirmation.setHeaderText("Added match");
                break;
            case 3:
                confirmation.setHeaderText("Added new player");
                break;
            case 4:
                confirmation.setHeaderText("Changed player's team");
                break;
            case 5:
                confirmation.setHeaderText("Changed player role");
                break;

        }
        confirmation.showAndWait();
    }

    public static void information(int flag){
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Success");
        switch(flag){
            case 0:
                information.setHeaderText("Registration successful");
                break;
        }
        information.showAndWait();
    }

    public static void infoMoreThanOnePlayers(){

        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setTitle("Problem");
        information.setHeaderText("Found more than one players");
        information.setContentText("Select player's date");
        information.showAndWait();
    }

    public static void getAlertComboBoxTeam(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Select team");
        errorAlert.setContentText("You must select team");
        errorAlert.showAndWait();
    }

    public static void getAlertComboBoxSeason(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Select season");
        errorAlert.setContentText("You must select season");
        errorAlert.showAndWait();
    }

    public static void getAlertEmptyListOfMatches(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("There is not any match in chosen team and season");
        errorAlert.setContentText("Please select existing match in given team and season");
        errorAlert.showAndWait();
    }

    public static void getAlertTransferToTheSameTeam(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("ERROR");
        errorAlert.setHeaderText("Current and next player's team is the same");
        errorAlert.setContentText("Please transfer player to other team");
        errorAlert.showAndWait();
    }
}
