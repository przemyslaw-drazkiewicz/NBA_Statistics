package nba_statistics.dao.interfaces;

import nba_statistics.entities.Teams;

public interface ITeamsDao {
    Teams getTeam(String name);
    Teams getTeam(int id);
    void getData(String division, String conference, String name, String location);
    boolean checkTeam(String team);

}
