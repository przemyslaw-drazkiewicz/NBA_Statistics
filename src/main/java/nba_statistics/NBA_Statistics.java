/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;


import nba_statistics.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Przemek
 */
public class NBA_Statistics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //create session factory
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Mecze.class)
                .addAnnotatedClass(Druzyny.class)
                .addAnnotatedClass(HistoriaDruzynZawodnika.class)
                .addAnnotatedClass(OsiagnieciaZawWMeczu.class)
                .addAnnotatedClass(Sezony.class)
                .addAnnotatedClass(Zawodnicy.class)
                .addAnnotatedClass(HistZmianWMeczu.class)
                .addAnnotatedClass(PowodZejscia.class)
                .addAnnotatedClass(ZawodnikPozycja.class)
                .addAnnotatedClass(Pozycje.class)
                .addAnnotatedClass(PozycjeZawWMeczu.class)
                .buildSessionFactory();

        //create session
        Session session = sf.getCurrentSession();

        try {
//            Druzyny d1 = new Druzyny("Atlantycka", "Wschodnia", "Toronto Raptors", "Toronto");
//            Druzyny d2 = new Druzyny("Centralna", "Wschodnia", "Indiana Pacers", "Indiana");
//            Druzyny d3 = new Druzyny("Pacific", "Zachodnia", "Phoenix Suns", "Phoenix");
//
//            Zawodnicy z1 = new Zawodnicy("Andrzej", "Grabowski", "1940-02-10", 160, 140);
//            Sezony s1 = new Sezony("Play Offy", "2017-06-22", "2018-07-30");
//            HistoriaDruzynZawodnika hdz1 = new HistoriaDruzynZawodnika();
//            hdz1.setDruzyna(d2);
//            hdz1.setSezon(s1);
//            hdz1.setZawodnik(z1);


            session.beginTransaction();

            ///////// TEST OsiagnieciaZawWMeczu ////////////

//            Zawodnicy zaw = session.get(Zawodnicy.class, 7);
//            Mecze mecz = session.get(Mecze.class, 2);
//            Mecze mecz2 = session.get(Mecze.class, 6);
//
//            OsiagnieciaZawWMeczu osiag = new OsiagnieciaZawWMeczu(zaw, mecz2, 1, 2, 3, 4, 5, 6);
//            OsiagnieciaZawWMeczu osiag2 = new OsiagnieciaZawWMeczu(zaw, mecz2, 6, 5, 4, 3, 2, 1);
//
//            session.save(osiag);
//            session.save(osiag2);
//            OsiagnieciaZawWMeczu osiagResult = session.get(OsiagnieciaZawWMeczu.class, new OsiagnieciaZawWMeczuId(7, 6));
//            System.out.println(zaw.toString());
//            System.out.println(mecz.toString());
//            System.out.println(osiagResult);

            ///////// TEST ZawodnikPozycja ////////////

//            Pozycje pozycja = new Pozycje("Rozgrywajacy");
//            Pozycje pozycja2 = new Pozycje("Rzucajacy obronca");
//            session.save(pozycja);
//            session.save(pozycja2);
//            Zawodnicy zaw = session.get(Zawodnicy.class, 7);
//            Pozycje poz = session.get(Pozycje.class, 2);
//            Pozycje poz2 = session.get(Pozycje.class, 3);
//            ZawodnikPozycja zawodnikPozycja = new ZawodnikPozycja(zaw, poz);
//            session.save(zawodnikPozycja);
//              ZawodnikPozycja zawodnikPozycjaRes = session.get(ZawodnikPozycja.class,new ZawodnikPozycjaID(zaw.getId(),poz.getId()));
//              session.delete(zawodnikPozycjaRes);
//            System.out.println(zaw.toString());
//            System.out.println(poz.toString());
//              System.out.println(zawodnikPozycjaRes.toString());

            ///////// TEST PozycjeZawWMeczu ////////////

            Mecze mecz = session.get(Mecze.class, 6);
            Zawodnicy zawodnik = session.get(Zawodnicy.class, 4);
            Pozycje pozycja = session.get(Pozycje.class, 3);
//            PozycjeZawWMeczu pozycjeZawWMeczu = new PozycjeZawWMeczu(zawodnik,mecz,pozycja);
//            session.save(pozycjeZawWMeczu);
//            PozycjeZawWMeczu pozycjeZawWMeczuRes = session.get(PozycjeZawWMeczu.class,
//                    new PozycjeZawWMeczuID(zawodnik.getId(), mecz.getId(),pozycja.getId()));
//            session.delete(pozycjeZawWMeczuRes);
//            session.delete(mecz);
            session.getTransaction().commit();

            //TODO
            //nie działa usuwanie meczów i zawodników


            System.out.println("Successful connection !!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //close session
        session.close();
        //close session factory
        sf.close();

    }
}
