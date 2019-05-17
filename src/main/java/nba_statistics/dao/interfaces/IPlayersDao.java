package nba_statistics.dao.interfaces;

import nba_statistics.entities.Zawodnicy;

import java.util.List;

public interface IPlayersDao {
    void getData(String name, String surname, String date, float height, float weight, String team);
    void updatePlayer(String name, String surname,String team);
    List<Zawodnicy> getPlayers(String surname);
}
