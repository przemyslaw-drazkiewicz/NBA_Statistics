package nba_statistics.services;

import nba_statistics.dao.classes.SeasonsDao;
import nba_statistics.entities.Seasons;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

public class SeasonsService {
    private static SeasonsDao seasonsDao;
    public SeasonsService(){
        seasonsDao = new SeasonsDao();
    }


    public Seasons getSeason(String name){
        seasonsDao.openCurrentSession();
        Seasons s = seasonsDao.getSeasons(name);
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

    public List <Seasons> getAllSeasons(){
        seasonsDao.openCurrentSession();
        List <Seasons> s = seasonsDao.getAllSeasons();
        seasonsDao.closeCurrentSession();
        return s;
    }

    public ArrayList<String>getAllSeasonsName(){
        seasonsDao.openCurrentSession();
        ArrayList<String> arrayList = seasonsDao.getAllSeasonsName();
        seasonsDao.closeCurrentSession();
        return arrayList;
    }
    public int getId(String name){
        seasonsDao.openCurrentSession();
        int id = seasonsDao.getId(name);
        seasonsDao.closeCurrentSession();
        return id;
    }
}
