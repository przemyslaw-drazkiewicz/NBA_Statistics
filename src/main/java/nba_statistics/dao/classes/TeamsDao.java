package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.ITeamsDao;
import nba_statistics.entities.Teams;
import org.hibernate.query.Query;

public class    TeamsDao extends Dao implements ITeamsDao {

    public TeamsDao(){

    }

    public void persist(Teams entity) {
        getCurrentSession().save(entity);
    }

    public Teams getTeam(String name) {
        Query<Teams> theQuery = getCurrentSession().createQuery("from Teams where name = :name", Teams.class);
        theQuery.setParameter("name", name);
        //theQuery.executeUpdate();
        Teams d = theQuery.setMaxResults(1).uniqueResult();
        if (d == null) {
            return null;
        }
        System.out.println(d.toString());
        return d;
    }

    public Teams getTeam(int id) {
        Query<Teams> theQuery = getCurrentSession().createQuery("from Teams where team_id = :id", Teams.class);

        theQuery.setParameter("id", id);
        //theQuery.executeUpdate();
        Teams d = theQuery.setMaxResults(1).uniqueResult();
        if (d == null) {
            return null;
        }
        System.out.println(d.toString());
        return d;
    }

    public void getData(String division, String conference, String name, String location){
        Teams d = new Teams(division, conference, name, location);
        persist(d);
    }

    @Override
    public boolean checkTeam(String team) {
        Query<Teams> theQuery = getCurrentSession().createQuery("from Teams where name = :team", Teams.class);

        theQuery.setParameter("team", team);
        Teams d = theQuery.setMaxResults(1).uniqueResult();
        if (d == null){
            return true;
        }
        else
            return false;
    }
}