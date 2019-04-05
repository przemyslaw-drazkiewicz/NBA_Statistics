/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.sql.Connection;
import java.sql.DriverManager;
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
                .buildSessionFactory();
        
        //create session
        Session session = sf.getCurrentSession();

        try {
            Mecze mecz = new Mecze(1, 2, "10.02.2019", 10, 15, 2, 4);
            
            session.beginTransaction();
            
            session.save(mecz);
            
            session.getTransaction().commit();
            
            System.out.println("Successful connection !!!");
        }  
        catch(Exception e){
            e.printStackTrace();
        }
        
        sf.close();

    }
}
