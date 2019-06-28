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
                .buildSessionFactory();

        //create session
        Session session = sf.getCurrentSession();

        try {
            //Teams d1 = new Teams("Atlantycka", "Wschodnia", "Toronto Raptors", "Toronto");
            //Teams d2 = new Teams("Centralna", "Wschodnia", "Indiana Pacers", "Indiana");
//            Teams d3 = new Teams("Pacific", "Zachodnia", "Phoenix Suns", "Phoenix");
//
//            Players z1 = new Players("Andrzej", "Grabowski", "1940-02-10", 160, 140);
//            Seasons s1 = new Seasons("Play Offy", "2017-06-22", "2018-07-30");
//            PlayerTeamsHistory hdz1 = new PlayerTeamsHistory();
//            hdz1.setTeam(d2);
//            hdz1.setSeason(s1);
//            hdz1.setPlayer(z1);


            session.beginTransaction();


            ///////// TEST PlayerMatchAchievements ////////////

     //       Players zaw = session.get(Players.class, 4);
      //      Matches mecz = session.get(Matches.class, 6);
//            Matches mecz2 = session.get(Matches.class, 6);
//
        //    PlayerMatchAchievements osiag = new PlayerMatchAchievements(zaw, mecz, 30, 5, 3, 4, 5, 2);
//            PlayerMatchAchievements osiag2 = new PlayerMatchAchievements(zaw, mecz2, 6, 5, 4, 3, 2, 1);
   //         session.save(osiag);
//            session.save(osiag2);
//            PlayerMatchAchievements osiagResult = session.get(PlayerMatchAchievements.class, new PlayerMatchAchievementsId(7, 6));
//            System.out.println(zaw.toString());
//            System.out.println(mecz.toString());
//            System.out.println(osiagResult);

            ///////// TEST PlayerPosition ////////////
/*
              Positions pozycja = new Positions("Rozgrywajacy");
//            Positions pozycja2 = new Positions("Rzucajacy obronca");
              session.save(pozycja);
//            session.save(pozycja2);
              //Players zaw = session.get(Players.class, 5);
              Players zaw = new Players("Michael", "Jordan", "1968-02-10",198,98);
              session.save(zaw);
//            Positions poz = session.get(Positions.class, 2);
//            Positions poz2 = session.get(Positions.class, 3);
              PlayerPosition zawodnikPozycja = new PlayerPosition(zaw, pozycja);
              session.save(zawodnikPozycja);
//              PlayerPosition zawodnikPozycjaRes = session.get(PlayerPosition.class,new PlayerPositionId(zaw.getId(),poz.getId()));
//              session.delete(zawodnikPozycjaRes);
            System.out.println(zaw.toString());
            System.out.println(pozycja.toString());
              System.out.println(zawodnikPozycja.toString());
*/

            ///////// TEST PlayerMatchPositions ////////////
/*
            Matches mecz = session.get(Matches.class, 6);
            Players zawodnik = session.get(Players.class, 4);
            Positions pozycja = session.get(Positions.class, 1);
            //PlayerMatchPositions pozycjeZawWMeczu = new PlayerMatchPositions(zawodnik,mecz,pozycja);
            //session.save(pozycjeZawWMeczu);
            PlayerMatchPositions pozycjeZawWMeczuRes = session.get(PlayerMatchPositions.class,
                    new PlayerMatchPositionsId(zawodnik.getId(), mecz.getId(),pozycja.getId()));
            session.delete(pozycjeZawWMeczuRes);
            session.delete(mecz);

            SubstitutionReasons pz = new SubstitutionReasons("zmiana taktyczna");
            session.save(pz);

            Teams d2 = new Teams("Centralna", "Wschodnia", "Indiana Pacers", "Indiana");
            Teams d3 = new Teams("Pacific", "Zachodnia", "Phoenix Suns", "Phoenix");
            session.save(d2);
            session.save(d3);

            Matches mecz = new Matches("2000-02-02", 100, 80, 1);
            mecz.setHostTeam(d3);
            mecz.setGuestTeam(d2);
            Seasons sezon = new Seasons("2016/2017", "2016-03-03", "2017-03-30");
            session.save(sezon);
            mecz.setSeason(sezon);

            mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(4, "02:41", 6, pz));
            mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(6,"05:21", 4, session.get(SubstitutionReasons.class, 1)));

            session.save(mecz);

            Players zaw = session.get(Players.class, 4);
            Matches mecz = session.get(Matches.class, 8);
            PlayerMatchAchievements o = new PlayerMatchAchievements(zaw, mecz, 30, 6, 10, 4,2,1);
            session.save(o);

            Matches mecze = session.get(Matches.class, 8);
            session.delete(mecze);
*/
            //Players zaw = session.get(Players.class, 6);
            //System.out.println(zaw.toString());


            //Players zaw = new Players("ziomek", "usun sie", "1000-11-11", 1,1);
            //session.save(zaw);
//            Players zaw = session.get(Players.class, 4);
            //session.save(zaw);
//            session.delete(zaw);
/*
            Teams d = session.get(Teams.class, 8);

            Seasons s = session.get(Seasons.class, 5);

            Positions p = session.get(Positions.class, 1);

            Matches mecz = session.get(Matches.class, 3);

            SubstitutionReasons powod = session.get(SubstitutionReasons.class, 1);
*/
            //mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(13, "02:41", 6, powod));
            //mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(4,"05:21", 13, session.get(SubstitutionReasons.class, 1)));

            ////////////////////////////////////////////////////////////////////////
            /*Teams d2 = session.get(Teams.class, 10);
            Teams d3 = session.get(Teams.class, 11);

            Matches mecz2 = new Matches("2019-03-22", 90, 79, 2);
            mecz.setHostTeam(d3);
            mecz.setGuestTeam(d2);
            Seasons sezon = session.get(Seasons.class, 5);

            mecz.setSeason(sezon);

            mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(11, "02:41", 6, powod));
            mecz.dodajHistZmianWMeczu(new MatchSubstitutionHistory(4,"05:21", 11, session.get(SubstitutionReasons.class, 1)));

            session.save(mecz);
            */

/*            Query<Matches> theQuery = session.createQuery("from Matches", Matches.class);
            List<Matches> matches = theQuery.getResultList();*/

 /*            for (Matches m:matches)
            {
                System.out.println(m.toString());
            }*/


            //session.createQuery("delete from Teams where id>=8").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Successful connection !!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //close session
        session.close();
        //close session factory
        sf.close();

        //launch the main view
        launch(args);
    }

}
