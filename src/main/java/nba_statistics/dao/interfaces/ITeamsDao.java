package nba_statistics.dao.interfaces;

import nba_statistics.entities.Teams;

import java.util.ArrayList;
import java.util.List;

public interface ITeamsDao {
    Teams getTeam(String name);
    Teams getTeam(int id);
    void getData(String division, String conference, String name, String location, String imageURL);
    boolean checkTeam(String team);
    ArrayList<String> getAllTeams();
    int getId(String name);

}
