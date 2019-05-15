package nba_statistics.services;

import nba_statistics.dao.classes.SeasonsDao;
import nba_statistics.entities.Sezony;

public class SeasonsService {
    private static SeasonsDao seasonsDao;
    public SeasonsService(){
        seasonsDao = new SeasonsDao();
    }

    public Sezony getSeason(String name){
        seasonsDao.openCurrentSession();
        Sezony s = seasonsDao.getSeasons(name);
        seasonsDao.closeCurrentSession();
        return s;
    }
    public void getData(String name, String startDate, String endDate){
        seasonsDao.openCurrentSessionwithTransaction();
        seasonsDao.getData(name, startDate, endDate);
        seasonsDao.closeCurrentSessionwithTransaction();
    }
}
