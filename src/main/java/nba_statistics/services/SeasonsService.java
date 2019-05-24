package nba_statistics.services;

import nba_statistics.dao.classes.SeasonsDao;
import nba_statistics.entities.Sezony;

import java.util.List;

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
    public int getData(String name, String startDate, String endDate){
        seasonsDao.openCurrentSession();
        int i = seasonsDao.getData(name, startDate, endDate);
        seasonsDao.closeCurrentSession();
        return i;
    }
    public boolean checkSeason(String name){
        seasonsDao.openCurrentSession();
        boolean i = seasonsDao.checkSeason(name);
        seasonsDao.closeCurrentSession();
        return i;
    }
}
