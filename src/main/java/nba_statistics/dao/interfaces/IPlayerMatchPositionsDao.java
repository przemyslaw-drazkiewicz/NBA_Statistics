package nba_statistics.dao.interfaces;

import nba_statistics.entities.Matches;
import nba_statistics.entities.Players;
import nba_statistics.entities.Positions;

public interface IPlayerMatchPositionsDao {
    int getData(Players player,Matches match, Positions position);
}
