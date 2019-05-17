package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.ISeasonsDao;
import nba_statistics.entities.Sezony;
import org.hibernate.query.Query;

public class SeasonsDao extends Dao implements ISeasonsDao {


    public SeasonsDao(){

    }


    public void persist(Sezony entity) {
        getCurrentSession().save(entity);
    }

    public Sezony getSeasons(String name) {
        Query<Sezony> theQuery = getCurrentSession().createQuery("from Sezony where nazwa = :name", Sezony.class);
        theQuery.setParameter("name", name);
        Sezony s = theQuery.setMaxResults(1).uniqueResult();
        System.out.println(s.toString());
        return s;
    }
    public void getData(String name, String startDate, String endDate){
        Sezony s = new Sezony(name, startDate, endDate);
        persist(s);

    }
}