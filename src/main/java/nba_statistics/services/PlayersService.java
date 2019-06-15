package nba_statistics.services;

import nba_statistics.dao.classes.PlayersDao;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;

import java.util.List;

public class PlayersService {
    private static PlayersDao playersDao;

    public PlayersService(){
        playersDao = new PlayersDao();
    }

    public int getData(String name, String surname, String date, float height, float weight, String team, String imageURL){
        playersDao.openCurrentSessionwithTransaction();
        int tmp = playersDao.getData(name, surname, date,height, weight, team, imageURL);
        playersDao.closeCurrentSessionwithTransaction();
        return tmp;
    }

    public List<Players> getPlayers(String name, String surname){
        playersDao.openCurrentSessionwithTransaction();
        List<Players> players = playersDao.getPlayers(name, surname);
        playersDao.closeCurrentSessionwithTransaction();
        return players;
    }

    public List<Players> getPlayers(int id){
        playersDao.openCurrentSessionwithTransaction();
        List<Players> players = playersDao.getPlayers(id);
        playersDao.closeCurrentSessionwithTransaction();
        return players;
    }

    public List<Players> getPlayersById(int id){
        playersDao.openCurrentSessionwithTransaction();
        List<Players> players = playersDao.getPlayersById(id);
        playersDao.closeCurrentSessionwithTransaction();
        return players;
    }

    public List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer){
        playersDao.openCurrentSessionwithTransaction();
        List<PlayerTeamsHistory> history = playersDao.getPlayerTeamsHistory(idPlayer);
        playersDao.closeCurrentSessionwithTransaction();
        return history;
    }

    public List<String> getAll(){
        playersDao.openCurrentSession();
        List<String> list = playersDao.getAll();
        playersDao.closeCurrentSession();
        return list;
    }

    public int updatePlayer(Players name,String team, String imageURL, String height, String weight, int seasonId){
        playersDao.openCurrentSessionwithTransaction();
        int i = playersDao.updatePlayer(name, team,imageURL, height, weight, seasonId);
        playersDao.closeCurrentSessionwithTransaction();
        return i;
    }
    public Players getPlayer(String name, String surname){
        playersDao.openCurrentSessionwithTransaction();
        Players player = playersDao.getPlayer(name, surname);
        playersDao.closeCurrentSessionwithTransaction();
        return player;
    }
    public Players getPlayer(String name, String surname, String date){
        playersDao.openCurrentSessionwithTransaction();
        Players player = playersDao.getPlayer(name, surname, date);
        playersDao.closeCurrentSessionwithTransaction();
        return player;
    }


    public List<PlayerTeamsHistory> getPlayersInTeam(int season, int team) {
        playersDao.openCurrentSessionwithTransaction();
        List<PlayerTeamsHistory> listPlayers = playersDao.getPlayersInTeam(season, team);
        playersDao.closeCurrentSessionwithTransaction();
        return listPlayers;
    }

    public Players getPlayerFromAutoCompleteField(String value) {
        playersDao.openCurrentSessionwithTransaction();
        Players p = playersDao.getPlayerFromAutoCompleteField(value);
        playersDao.closeCurrentSessionwithTransaction();
        return p;
    }
}