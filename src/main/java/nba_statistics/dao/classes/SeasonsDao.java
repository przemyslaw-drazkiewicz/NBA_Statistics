package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.ISeasonsDao;
import nba_statistics.entities.Seasons;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class SeasonsDao extends Dao implements ISeasonsDao {


    public SeasonsDao(){

    }


    public void persist(Seasons entity) {
        getCurrentSession().save(entity);
    }

    public Seasons getSeasons(String name) {
        Query<Seasons> theQuery = getCurrentSession().createQuery("from Seasons where name = :name", Seasons.class);
        theQuery.setParameter("name", name);
        Seasons s = theQuery.setMaxResults(1).uniqueResult();
        System.out.println(s.toString());
        return s;
    }
    public int getData(String name, String startDate, String endDate){
        try {
            Date d = Date.valueOf(startDate);
            try {
                Date d1 = Date.valueOf(endDate);
                if (d.compareTo(d1) > 0)
                    return 3;
                Seasons s = new Seasons(name, startDate, endDate);
                persist(s);
                return 0;
            } catch (IllegalArgumentException exc){
                return 2;
            }
        }
        catch (IllegalArgumentException exc){
            return 1;
        }


    }

    @Override
    public boolean checkSeason(String name) {
        Query<Seasons> theQuery = getCurrentSession().createQuery("from Seasons where name = :name", Seasons.class);
        theQuery.setParameter("name", name);
        Seasons s = theQuery.setMaxResults(1).uniqueResult();
        if (s == null)
            return false;
        else
            return true;
    }


    public List<Seasons> getAllSeasons() {
        Query<Seasons> theQuery = getCurrentSession().createQuery("from Seasons ");
        List <Seasons> s = theQuery.getResultList();

        return s;
    }
}