package nba_statistics.dao.classes;

import nba_statistics.HibernateUtil;
import nba_statistics.dao.interfaces.IMatchesDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import nba_statistics.entities.Sezony;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesDao implements IMatchesDao{


    private Session currentSession;

    private Transaction currentTransaction;

    public MatchesDao(){

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

    public List<Mecze> findAll() {
        // List<Mecze> matches = (List<Mecze>) getCurrentSession().createQuery("from Mecze").list();
        Query<Mecze> theQuery = getCurrentSession().createQuery("from Mecze", Mecze.class);
        List<Mecze> matches = theQuery.getResultList();

        return matches;
    }

    public void persist(Mecze entity) {
        getCurrentSession().save(entity);
    }

    public void getData(String home, String away, String date, String season){
        TeamsService teamsService = new TeamsService();
        SeasonsService seasonsService = new SeasonsService();
        Druzyny teamHome = teamsService.getTeam(home);
        Druzyny teamAway = teamsService.getTeam(away);
        Sezony s = seasonsService.getSeason(season);
        Mecze mecz = new Mecze();
        mecz.setData(date);
        mecz.setDruzGosp(teamHome);
        mecz.setDruzGosc(teamAway);
        mecz.setSezon(s);
        persist(mecz);
    }


}
