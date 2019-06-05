package nba_statistics.services;

import nba_statistics.dao.classes.PlayerTeamsHistoryDao;

public class PlayerTeamsHistoryService {

    private static PlayerTeamsHistoryDao playerTeamsHistoryDao;

    public PlayerTeamsHistoryService(){
        playerTeamsHistoryDao = new PlayerTeamsHistoryDao();
    }

    public boolean savePlayerTeamsHistory(String player, String team, String season){
        playerTeamsHistoryDao.openCurrentSessionwithTransaction();
        boolean i = playerTeamsHistoryDao.savePlayerTeamsHistory(player, team, season);
        playerTeamsHistoryDao.closeCurrentSessionwithTransaction();
        return i;
    }
}
