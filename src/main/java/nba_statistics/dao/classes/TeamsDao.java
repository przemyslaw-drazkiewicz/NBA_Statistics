package nba_statistics.dao.classes;

import nba_statistics.HibernateUtil;
import nba_statistics.dao.interfaces.ITeamsDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TeamsDao implements ITeamsDao {

    private Session currentSession;

    private Transaction currentTransaction;

    public TeamsDao(){

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


    public void persist(Druzyny entity) {
        getCurrentSession().save(entity);
    }

    public Druzyny getTeam(String name) {
        Query<Druzyny> theQuery = getCurrentSession().createQuery("from Druzyny where nazwa = :name", Druzyny.class);
        theQuery.setParameter("name", name);
        //theQuery.executeUpdate();
        Druzyny d = theQuery.setMaxResults(1).uniqueResult();
        System.out.println(d.toString());
        return d;
    }
    public void getData(String division, String conference, String name, String location){
        Druzyny d = new Druzyny(division, conference, name, location);
        persist(d);
    }
}
