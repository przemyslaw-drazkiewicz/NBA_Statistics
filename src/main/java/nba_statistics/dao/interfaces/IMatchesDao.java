package nba_statistics.dao.interfaces;

import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import nba_statistics.entities.OsiagnieciaZawWMeczu;

import java.util.List;

public interface IMatchesDao {
    List<Mecze> findAll();

    void getData(String home, String away, String date, String season);
    void persist(Mecze mecze);
    List<OsiagnieciaZawWMeczu> getAchievementPlayerInMatch(int idPlayer, int idSeason, int idTeam);
}
