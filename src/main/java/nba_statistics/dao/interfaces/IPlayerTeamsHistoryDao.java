package nba_statistics.dao.interfaces;


import nba_statistics.entities.PlayerTeamsHistory;

public interface IPlayerTeamsHistoryDao {
    void persist(PlayerTeamsHistory entity);
    boolean savePlayerTeamsHistory(String playerName , String teamName , String seasonName);
}
