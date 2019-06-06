package nba_statistics.dao.interfaces;

import nba_statistics.entities.PlayerMatchAchievements;

import java.util.ArrayList;

public interface IPlayerMatchAchievementsDao {
    void save(ArrayList<PlayerMatchAchievements> playerAchievements);
}
