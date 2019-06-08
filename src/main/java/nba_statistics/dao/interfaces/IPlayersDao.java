package nba_statistics.dao.interfaces;

import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;

import java.util.List;

public interface IPlayersDao {
    int getData(String name, String surname, String date, float height, float weight, String team, String imageURL);
    int updatePlayer(String name,String team, String imageURL);
    List<Players> getPlayers(String name, String surname);
    List<Players> getPlayers(int id);
    List<Players> getPlayers(String name, String surname, String date);
    Players getPlayer(String name, String surname);
    Players getPlayer(String name, String surname, String date);
    List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer);
    List <String> getAll();
    List<Players> getPlayersById(int id);
}
