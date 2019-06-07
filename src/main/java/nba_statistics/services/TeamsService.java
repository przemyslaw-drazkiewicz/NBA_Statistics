package nba_statistics.services;

import nba_statistics.dao.classes.TeamsDao;
import nba_statistics.entities.Teams;

import java.util.ArrayList;
import java.util.List;

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

    public boolean getData(String division, String conference, String name, String location, String imageURL){
        teamsDao.openCurrentSessionwithTransaction();
        boolean b= teamsDao.getData(division, conference, name,location,imageURL);
        teamsDao.closeCurrentSessionwithTransaction();
        return b;
    }
    public boolean checkTeam(String team){
        teamsDao.openCurrentSession();
        boolean i = teamsDao.checkTeam(team);
        teamsDao.closeCurrentSession();
        return i;
    }

    public ArrayList<String> getAllTeams(){
        teamsDao.openCurrentSession();
        ArrayList<String> teamsList = teamsDao.getAllTeams();
        teamsDao.closeCurrentSession();
        return teamsList;
    }
    public int getId(String name){
        teamsDao.openCurrentSession();
        int id = teamsDao.getId(name);
        teamsDao.closeCurrentSession();
        return id;
    }
}