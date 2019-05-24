package nba_statistics.dao.interfaces;

import nba_statistics.entities.Sezony;

import java.util.List;

public interface ISeasonsDao {
    Sezony getSeasons(String name);
    int getData(String name, String startDate, String endDate);
    boolean checkSeason(String name);
}
