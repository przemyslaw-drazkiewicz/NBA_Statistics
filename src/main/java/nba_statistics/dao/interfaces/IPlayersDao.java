package nba_statistics.dao.interfaces;

import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;

import java.util.List;

public interface IPlayersDao {
    int getData(String name, String surname, String date, float height, float weight, String team);
    int updatePlayer(String name, String surname,String team);
    List<Players> getPlayers(String name, String surname);
    List<Players> getPlayers(int id);
    List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer);
}
