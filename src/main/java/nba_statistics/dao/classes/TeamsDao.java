package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.ITeamsDao;
import nba_statistics.entities.Teams;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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

    public boolean getData(String division, String conference, String name, String location, String imageURL){
        Teams d = new Teams(division, conference, name, location,imageURL);
        if (getTeamByImage(imageURL)!=null){
            return false;
        }
        persist(d);
        return true;
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
    public Teams getTeamByImage(String image){
        Query<Teams> theQuery = getCurrentSession().createQuery("from Teams where imageURL=:image")
                .setParameter("image", image);
        try{
            return theQuery.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ArrayList<String> getAllTeams() {
        Query<Teams> theQuery = getCurrentSession().createQuery("from Teams", Teams.class);
        List<Teams> teams = theQuery.getResultList();
        ArrayList <String> teamsName = new ArrayList<>();
        for (Teams t:teams){
            teamsName.add(t.getName());
        }
        return teamsName;
    }

    @Override
    public int getId(String name) {
        Query<Integer> theQuery = getCurrentSession().createQuery("select id from Teams where name = :name");
        theQuery.setParameter("name", name);
        return theQuery.setMaxResults(1).getSingleResult();
    }
}