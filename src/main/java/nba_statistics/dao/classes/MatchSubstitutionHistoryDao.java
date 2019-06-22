package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IMatchSubstitutionHistoryDao;
import nba_statistics.entities.MatchSubstitutionHistory;

public class MatchSubstitutionHistoryDao extends Dao implements IMatchSubstitutionHistoryDao {

    public void persist(MatchSubstitutionHistory entity)
     {
       getCurrentSession().save(entity);
     }

     @Override
    public int getData(MatchSubstitutionHistory matchSubstitutionHistory)
     {
         persist(matchSubstitutionHistory);
         return 0;
     }

}
