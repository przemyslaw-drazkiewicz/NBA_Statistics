package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IHelpDao;
import nba_statistics.entities.Help;
import org.hibernate.query.Query;

import java.util.List;


public class HelpDao extends Dao implements IHelpDao {
    @Override
    public String getText(String view) {
        Query<Help> theQuery = getCurrentSession().createQuery("from Help where view = :view").setParameter("view", view);
        try{return theQuery.getSingleResult().getText();}
        catch(Exception e){ return null;}
        }
}
