package nba_statistics;

import nba_statistics.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;


public class HibernateUtil {

    private static SessionFactory sessionFactory;// = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml

          return new Configuration()
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


/*            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().
                    configure("hibernate.cfg.xml").build();

            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().
                    build();

            SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();

            SessionFactory sessionFactory = sessionFactoryBuilder.build();

            return sessionFactory;*/
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;

    }

}


