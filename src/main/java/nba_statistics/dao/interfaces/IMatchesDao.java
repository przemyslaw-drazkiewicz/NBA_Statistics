package nba_statistics.dao.interfaces;

import nba_statistics.entities.Matches;
import nba_statistics.entities.PlayerMatchAchievements;

import java.util.List;

public interface IMatchesDao {
    List<Matches> findAll();
    List<Matches> findAllAtDate(String date);
    int getData(String home, String away, String date, String season);
    void persist(Matches matches);
    List<PlayerMatchAchievements> getAchievementPlayerInMatch(int idPlayer, int idSeason, int idTeam);
    List<Matches> getMatches(String team, String date);
}
