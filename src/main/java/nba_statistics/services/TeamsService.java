package nba_statistics.services;

import nba_statistics.dao.classes.TeamsDao;
import nba_statistics.entities.Teams;

public class TeamsService {
    private static TeamsDao teamsDao;

    public TeamsService(){
        teamsDao = new TeamsDao();
    }

    public Teams getTeam(String name){
        teamsDao.openCurrentSession();
        Teams d = teamsDao.getTeam(name);
        teamsDao.closeCurrentSession();
        return d;
    }

    public Teams getTeam(int id){
        teamsDao.openCurrentSession();
        Teams d = teamsDao.getTeam(id);
        teamsDao.closeCurrentSession();
        return d;
    }

    public void getData(String division, String conference, String name, String location){
        teamsDao.openCurrentSessionwithTransaction();
        teamsDao.getData(division, conference, name,location);
        teamsDao.closeCurrentSessionwithTransaction();
    }
    public boolean checkTeam(String team){
        teamsDao.openCurrentSession();
        boolean i = teamsDao.checkTeam(team);
        teamsDao.closeCurrentSession();
        return i;
    }
}