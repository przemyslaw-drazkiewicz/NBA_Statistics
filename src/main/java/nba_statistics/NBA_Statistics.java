/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;


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
                .addAnnotatedClass(PowodyZejscia.class)
                .addAnnotatedClass(ZawodnikPozycja.class)
                .addAnnotatedClass(Pozycje.class)
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

            Zawodnicy zaw = session.get(Zawodnicy.class, 7);
            Mecze mecz = session.get(Mecze.class, 2);
            Mecze mecz2 = session.get(Mecze.class, 6);

//            OsiagnieciaZawWMeczu osiag = new OsiagnieciaZawWMeczu(zaw, mecz, 1, 2, 3, 4, 5, 6);
//            OsiagnieciaZawWMeczu osiag2 = new OsiagnieciaZawWMeczu(zaw, mecz2, 6, 5, 4, 3, 2, 1);

//            session.save(osiag);
//            session.save(osiag2);
            OsiagnieciaZawWMeczu osiagResult = session.get(OsiagnieciaZawWMeczu.class, new OsiagnieciaZawWMeczuId(7, 6));
            System.out.println(zaw.toString());
            System.out.println(mecz.toString());
            System.out.println(osiagResult);


//            session.save(d2);
//            session.save(z1);
//            session.save(s1);
//            session.save(hdz1);
//
//            session.save(d3);
//            z1.setDruzyna(d3);
//
//            session.save(z1);
//            session.createQuery("delete from DruzynyDruzyny d1 = session.get(Druzyny.class, 1);").executeUpdate();
//            session.createQuery("delete from Druzyny where id < 6 ").executeUpdate();
//
//
//            session.save(d1);
//            session.save(d2);
//            session.save(d3);
//
//
//            session.getTransaction().commit();
//
//            session.save(d2);
//            session.save(d3);
//            Mecze mecz = new Mecze("30.01.2019", 90, 73, 0);
//
//            mecz.setDruzGosc(d3);
//            mecz.setDruzGosp(d2);
//            session.save(mecz);
//
//            mecz.setSezon(s1);
//
//            session.save(s1);
//            mecz.setDruzGosp(d1);
//            mecz.setDruzGosc(d2);
//
//            session = sf.getCurrentSession();
//
//            session.beginTransaction();
//
//            session.save(mecz);
//            Mecze mecz = session.get(Mecze.class, 2);
//
//            session.delete(mecz);
//            session.createQuery("delete from Mecze where id=1 ").executeUpdate();
//            List<Mecze> mecze = session.createQuery("from Mecze").list();
//
//            for (Mecze mecz : mecze) {
//                System.out.println(mecz + "\n");
//            }


            session.getTransaction().commit();


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
