package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayerMatchPositionsDao;
import nba_statistics.entities.*;

public class PlayerMatchPositionsDao extends Dao implements IPlayerMatchPositionsDao {

    public void persist(PlayerMatchPositions entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public int getData(Players player, Matches match, Positions position){
        if(player == null)
        {
            return 1;
        }
        if(match==null)
        {
            return 2;
        }
        if(position==null)
        {
            return 3;
        }
        //PlayerMatchPositions playerMatchPositions = new PlayerMatchPositions(player,match,position);

        PlayerMatchPositions playerMatchPositions = new PlayerMatchPositions();
        playerMatchPositions.setPlayer(player);
        playerMatchPositions.setMatch(match);
        playerMatchPositions.setPosition(position);
        playerMatchPositions.setId(new PlayerMatchPositionsId(player.getId(),match.getId(),position.getId()));
        persist(playerMatchPositions);
        return 0;
    }
}
