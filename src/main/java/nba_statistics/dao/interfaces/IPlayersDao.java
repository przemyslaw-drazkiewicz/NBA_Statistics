package nba_statistics.dao.interfaces;

import nba_statistics.entities.HistoriaDruzynZawodnika;
import nba_statistics.entities.Zawodnicy;

import java.util.List;

public interface IPlayersDao {
    int getData(String name, String surname, String date, float height, float weight, String team);
    int updatePlayer(String name, String surname,String team);
    List<Zawodnicy> getPlayers(String name, String surname);
    List<HistoriaDruzynZawodnika> getPlayerTeamsHistory(int idPlayer);
}
