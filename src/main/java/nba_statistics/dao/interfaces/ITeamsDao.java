package nba_statistics.dao.interfaces;

import nba_statistics.entities.Druzyny;

public interface ITeamsDao {
    Druzyny getTeam(String name);
    Druzyny getTeam(int id);
    void getData(String division, String conference, String name, String location);
    boolean checkTeam(String team);

}
