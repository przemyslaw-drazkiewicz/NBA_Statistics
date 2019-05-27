package nba_statistics.dao.interfaces;

import nba_statistics.entities.Seasons;

import java.util.List;

public interface ISeasonsDao {
    Seasons getSeasons(String name);
    int getData(String name, String startDate, String endDate);
    boolean checkSeason(String name);
    List<Seasons> getAllSeasons();
}
