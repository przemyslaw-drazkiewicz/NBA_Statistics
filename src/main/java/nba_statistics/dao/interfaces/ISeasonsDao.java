package nba_statistics.dao.interfaces;

import nba_statistics.entities.Sezony;

public interface ISeasonsDao {
    Sezony getSeasons(String name);
}
