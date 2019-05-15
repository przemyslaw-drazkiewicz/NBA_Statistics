package nba_statistics.dao.classes;

import nba_statistics.HibernateUtil;
import nba_statistics.dao.interfaces.IPlayersDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Zawodnicy;
import nba_statistics.services.TeamsService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PlayersDao implements IPlayersDao {

    private Session currentSession;

    private Transaction currentTransaction;

    public PlayersDao(){

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

    public void persist(Zawodnicy entity) {
        getCurrentSession().save(entity);
    }

    public void getData(String name, String surname, String date, float height, float weight, String team) {
        Zawodnicy z = new Zawodnicy(name, surname, date, height, weight);
        TeamsService teamsService = new TeamsService();
        Druzyny d = teamsService.getTeam(team);
        z.setDruzyna(d);
        persist(z);

    }

    public void updatePlayer(String name, String surname, String team){
        //Query<Zawodnicy> theQuery = getCurrentSession().createQuery("from Zawodnicy where imie = :name and nazwisko = :surname",Zawodnicy.class);
        TeamsService teamsService = new TeamsService();
        Druzyny d = teamsService.getTeam(team);
        int id = d.getId();

        getCurrentSession().createQuery("update Zawodnicy set id_druzyny = :id  where imie = :name and nazwisko = :surname")
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .executeUpdate();


    }

}
