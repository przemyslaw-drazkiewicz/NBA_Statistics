package nba_statistics.dao.classes;

import nba_statistics.HibernateUtil;
import nba_statistics.dao.interfaces.ISeasonsDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Sezony;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SeasonsDao implements ISeasonsDao {
    private Session currentSession;

    private Transaction currentTransaction;

    public SeasonsDao(){

    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    public Sezony getSeasons(String name) {
        Query<Sezony> theQuery = getCurrentSession().createQuery("from Sezony where nazwa = :name", Sezony.class);
        theQuery.setParameter("name", name);
        Sezony s = theQuery.setMaxResults(1).uniqueResult();
        System.out.println(s.toString());
        return s;
    }
}
