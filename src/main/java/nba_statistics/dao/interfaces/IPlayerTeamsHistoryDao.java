package nba_statistics.dao.interfaces;


public interface IPlayerTeamsHistoryDao {
    boolean savePlayerTeamsHistory(String playerName , String teamName , String seasonName);
}
