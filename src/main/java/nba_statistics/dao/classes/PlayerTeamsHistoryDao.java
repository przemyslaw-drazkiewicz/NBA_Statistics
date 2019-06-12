package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayerTeamsHistoryDao;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;
import nba_statistics.entities.Seasons;
import nba_statistics.entities.Teams;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

public class PlayerTeamsHistoryDao extends Dao implements IPlayerTeamsHistoryDao
{

    public void persist(PlayerTeamsHistory  entity) {

        getCurrentSession().save(entity);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean savePlayerTeamsHistory(String playerName, String teamName, String seasonName ) {

        SeasonsService seasonsService = new SeasonsService();
        Seasons currSeason = seasonsService.getSeason(seasonName);
        TeamsService teamsService = new TeamsService();
        Teams currTeam = teamsService.getTeam(teamName);
        PlayersService playersService = new PlayersService();
        String[] splited = playerName.split("\\s+");
        Players currPlayer;
        if (splited.length == 2){
            currPlayer = playersService.getPlayer(splited[0],splited[1]);
        }else{ //=3
            currPlayer = playersService.getPlayer(splited[0], splited[1],splited[2]);
        }
        if (isRepeatTransferInSeason(currPlayer.getId(),currSeason.getId()))
            return false;

        PlayerTeamsHistory playerTeamsHistory = new PlayerTeamsHistory();
        playerTeamsHistory.setPlayer(currPlayer);
        playerTeamsHistory.setSeason(currSeason);
        playerTeamsHistory.setTeam(currTeam);
        persist(playerTeamsHistory);
        return true;
    }
    private boolean isRepeatTransferInSeason(int idPlayer, int idSeason){
            Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where player_id=:idPlayer and season_id =:idSeason")
                    .setParameter("idPlayer", idPlayer)
                    .setParameter("idSeason", idSeason);
            if (theQuery.getResultList().size() == 0)
                return false;
            else
                return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void saveNewPlayerTeamsHistory(String playerName, String teamName, String seasonName){
        SeasonsService seasonsService = new SeasonsService();
        Seasons currSeason = seasonsService.getSeason(seasonName);

        TeamsService teamsService = new TeamsService();
        Teams currTeam = teamsService.getTeam(teamName);

        PlayersService playersService = new PlayersService();
        String[] splited = playerName.split("\\s+");
        Players currPlayer = playersService.getPlayer(splited[0], splited[1],splited[2]);
        PlayerTeamsHistory playerTeamsHistory = new PlayerTeamsHistory();
        playerTeamsHistory.setPlayer(currPlayer);
        playerTeamsHistory.setSeason(currSeason);
        playerTeamsHistory.setTeam(currTeam);
        persist(playerTeamsHistory);
    }


}
