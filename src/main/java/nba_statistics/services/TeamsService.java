package nba_statistics.services;

import nba_statistics.dao.classes.TeamsDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;

import java.util.List;

public class TeamsService {
    private static TeamsDao teamsDao;

    public TeamsService(){
        teamsDao = new TeamsDao();
    }

    public Druzyny getTeam(String name){
        teamsDao.openCurrentSession();
        Druzyny d = teamsDao.getTeam(name);
        teamsDao.closeCurrentSession();
        return d;
    }
    public void getData(String division, String conference, String name, String location){
        teamsDao.openCurrentSessionwithTransaction();
        teamsDao.getData(division, conference, name,location);
        teamsDao.closeCurrentSessionwithTransaction();
    }
}
