/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
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
                .buildSessionFactory();

        //create session
        Session session = sf.getCurrentSession();

        try {
//            Druzyny d1 = new Druzyny("a", "b", "c", "d");
//            Druzyny d2 = new Druzyny("a2", "b2", "c2", "d2");
//
              session.beginTransaction();
//
//            session.save(d1);
//            session.save(d2);
//
//            session.getTransaction().commit();
//
//            Mecze mecz = new Mecze("10.02.2019", 10, 15, 2);
//
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
//            for(Mecze mecz: mecze){
//                System.out.println(mecz+"\n");
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
