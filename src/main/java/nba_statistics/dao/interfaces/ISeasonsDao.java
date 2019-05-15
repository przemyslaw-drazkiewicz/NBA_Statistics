package nba_statistics.dao.interfaces;

import nba_statistics.entities.Sezony;

public interface ISeasonsDao {
    Sezony getSeasons(String name);
    void getData(String name, String startDate, String endDate);
}
