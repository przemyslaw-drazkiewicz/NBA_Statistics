package nba_statistics.services;

import nba_statistics.dao.classes.PlayerMatchAchievementsDao;
import nba_statistics.entities.PlayerMatchAchievements;

import java.util.ArrayList;

public class PlayerMatchAchievementsService {
    private static PlayerMatchAchievementsDao playerMatchAchievementsDao;

    public PlayerMatchAchievementsService(){
        playerMatchAchievementsDao = new PlayerMatchAchievementsDao();
    }

    public void save(ArrayList<PlayerMatchAchievements> playerAchievements){
        playerMatchAchievementsDao.openCurrentSessionwithTransaction();
        playerMatchAchievementsDao.save(playerAchievements);
        playerMatchAchievementsDao.closeCurrentSessionwithTransaction();
    }
}
