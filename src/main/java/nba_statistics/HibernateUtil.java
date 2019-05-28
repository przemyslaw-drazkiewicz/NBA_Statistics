package nba_statistics;

import nba_statistics.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static SessionFactory sessionFactory;// = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml

          return new Configuration()
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
                    .addAnnotatedClass(Users.class)
                    .addAnnotatedClass(Roles.class)
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


