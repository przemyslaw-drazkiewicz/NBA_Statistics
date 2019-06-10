package nba_statistics.services;


import nba_statistics.dao.classes.PlayerMatchPositionsDao;
import nba_statistics.entities.Matches;
import nba_statistics.entities.Players;
import nba_statistics.entities.Positions;

public class PlayerMatchPositionsService {
    private PlayerMatchPositionsDao playerMatchPositionsDao;

    public PlayerMatchPositionsService(){playerMatchPositionsDao = new PlayerMatchPositionsDao();}

    public int getData(Players player, Matches match, Positions position)
    {
        playerMatchPositionsDao.openCurrentSession();
        int tmp = playerMatchPositionsDao.getData(player,match,position);
        playerMatchPositionsDao.closeCurrentSession();
        return tmp;
    }
}
