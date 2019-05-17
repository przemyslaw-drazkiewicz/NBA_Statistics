package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.ITeamsDao;
import nba_statistics.entities.Druzyny;
import org.hibernate.query.Query;

public class TeamsDao extends Dao implements ITeamsDao {

    public TeamsDao(){

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