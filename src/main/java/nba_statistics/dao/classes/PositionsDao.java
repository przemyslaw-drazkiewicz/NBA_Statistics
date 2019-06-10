package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPositionsDao;
import nba_statistics.entities.Positions;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class PositionsDao extends Dao implements IPositionsDao {
    @Override
    public Positions getPosition(String name)
    {
        Query<Positions> theQuery = getCurrentSession().createQuery("from Positions where name = :name", Positions.class);
        theQuery.setParameter("name",name);
        return theQuery.getSingleResult();
    }

    @Override
    public ArrayList<String> findAllPositionsName()
    {
        Query<String> theQuery = getCurrentSession().createQuery("select name from Positions ", String.class);
        ArrayList<String> positionsNameList = new ArrayList<String>(theQuery.getResultList());
        return positionsNameList;
    }
}
