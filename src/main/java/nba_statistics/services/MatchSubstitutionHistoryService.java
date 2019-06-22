package nba_statistics.services;

import nba_statistics.dao.classes.MatchSubstitutionHistoryDao;
import nba_statistics.entities.MatchSubstitutionHistory;

public class MatchSubstitutionHistoryService {
    private MatchSubstitutionHistoryDao matchSubstitutionHistoryDao;

    public MatchSubstitutionHistoryService(){matchSubstitutionHistoryDao= new MatchSubstitutionHistoryDao();}

    public int getData(MatchSubstitutionHistory matchSubstitutionHistory)
    {
        matchSubstitutionHistoryDao.openCurrentSessionwithTransaction();
        int tmp = matchSubstitutionHistoryDao.getData(matchSubstitutionHistory);
        matchSubstitutionHistoryDao.closeCurrentSessionwithTransaction();
        return tmp;
    }
}
