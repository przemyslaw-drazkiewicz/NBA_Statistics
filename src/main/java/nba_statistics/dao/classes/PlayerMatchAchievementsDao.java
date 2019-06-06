package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayerMatchAchievementsDao;
import nba_statistics.dao.interfaces.IPlayerTeamsHistoryDao;
import nba_statistics.entities.PlayerMatchAchievements;

import java.util.ArrayList;

public class PlayerMatchAchievementsDao extends Dao implements IPlayerMatchAchievementsDao {

    public PlayerMatchAchievementsDao(){}

    public void persist(PlayerMatchAchievements entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void save(ArrayList<PlayerMatchAchievements>playerAchievements) {
        for (PlayerMatchAchievements p : playerAchievements){
            persist(p);
        }

    }
}
