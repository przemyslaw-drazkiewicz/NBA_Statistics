package nba_statistics.dao.interfaces;

import nba_statistics.entities.Zawodnicy;

public interface IPlayersDao {
    void getData(String name, String surname, String date, float height, float weight, String team);
    void updatePlayer(String name, String surname,String team);
}
