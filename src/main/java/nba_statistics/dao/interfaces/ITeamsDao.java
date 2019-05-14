package nba_statistics.dao.interfaces;

import nba_statistics.entities.Druzyny;

public interface ITeamsDao {
    Druzyny getTeam(String name);

}
