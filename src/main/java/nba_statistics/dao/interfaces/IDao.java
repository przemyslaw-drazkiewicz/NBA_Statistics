package nba_statistics.dao.interfaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public interface IDao {
    Session openCurrentSession();
    Session openCurrentSessionwithTransaction();
    void closeCurrentSession();
    void closeCurrentSessionwithTransaction();

    static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
    Session getCurrentSession();

    void setCurrentSession(Session currentSession);

    Transaction getCurrentTransaction();

    void setCurrentTransaction(Transaction currentTransaction);

}