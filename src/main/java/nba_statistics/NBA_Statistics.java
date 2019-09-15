/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nba_statistics.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Przemek
 */
public class NBA_Statistics extends Application {

    private static final String MainFXML = "/MainView.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        //window = primaryStage;
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(MainFXML));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NBA Statistics");
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //create session factory
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Matches.class)
                .addAnnotatedClass(Teams.class)
                .addAnnotatedClass(PlayerTeamsHistory.class)
                .addAnnotatedClass(PlayerMatchAchievements.class)
                .addAnnotatedClass(Seasons.class)
                .addAnnotatedClass(Players.class)
                .addAnnotatedClass(MatchSubstitutionHistory.class)
                .addAnnotatedClass(SubstitutionReasons.class)
                .addAnnotatedClass(PlayerPosition.class)
                .addAnnotatedClass(Positions.class)
                .addAnnotatedClass(PlayerMatchPositions.class)
                //.addAnnotatedClass(Users.class)
                //.addAnnotatedClass(Roles.class)
                //.addAnnotatedClass(Help.class)
                .buildSessionFactory();

        //launch the main view
        launch(args);
    }

}
