package nba_statistics.services;

import nba_statistics.dao.classes.MatchesDao;
import nba_statistics.entities.Matches;
import nba_statistics.entities.PlayerMatchAchievements;

import java.util.List;

public class MatchesService{
    private static MatchesDao matchesDao;

    public MatchesService(){
        matchesDao = new MatchesDao();
    }



    public List<Matches> findAll(){
        matchesDao.openCurrentSession();
        List<Matches> matches = matchesDao.findAll();
        matchesDao.closeCurrentSession();
        return matches;
    }

    public List<Matches> findAllAtDate(String date){
        matchesDao.openCurrentSession();
        List<Matches> matches = matchesDao.findAllAtDate(date);
        matchesDao.closeCurrentSession();
        return matches;
    }

    public int getData(String home, String away, String date, String season){
        matchesDao.openCurrentSession();
        int tmp = matchesDao.getData(home, away, date,season);
        matchesDao.closeCurrentSession();
        return tmp;
    }

    public void persist(Matches entity) {
        matchesDao.openCurrentSessionwithTransaction();
        matchesDao.persist(entity);
        matchesDao.closeCurrentSessionwithTransaction();
    }


    public List<PlayerMatchAchievements> getAchievementPlayerInMatch(int idPlayer, int idSeason, int idTeam) {
        matchesDao.openCurrentSessionwithTransaction();
        List<PlayerMatchAchievements> achievements = matchesDao.getAchievementPlayerInMatch(idPlayer, idSeason,idTeam);
        matchesDao.closeCurrentSessionwithTransaction();
        return achievements;
    }
    public List<Matches> getMatches(String team, String date){
        matchesDao.openCurrentSession();
        List<Matches> matchesList = matchesDao.getMatches(team,date);
        matchesDao.closeCurrentSession();
        return matchesList;
    }

    public List<PlayerMatchAchievements> getAchievementsPlayersInSeason(int nameSeason) {
        matchesDao.openCurrentSessionwithTransaction();
        List<PlayerMatchAchievements> achievements = matchesDao.getAchievementPlayerInSeason(nameSeason);
        matchesDao.closeCurrentSessionwithTransaction();
        return achievements;
    }
}
