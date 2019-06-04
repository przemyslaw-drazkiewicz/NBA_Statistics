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

    public int updatePlayer(String name, String surname,String team){
        playersDao.openCurrentSessionwithTransaction();
        int tmp = 0;
        tmp = playersDao.updatePlayer(name, surname,team);
        playersDao.closeCurrentSessionwithTransaction();
        return tmp;
    }

/*    public int updatePlayer2(String name, String surname, String team, String date){
        playersDao.openCurrentSessionwithTransaction();
        int tmp = playersDao.updatePlayer2(name, surname,team,date);
        playersDao.closeCurrentSessionwithTransaction();
        return tmp;
    }*/
    public int getData(String name, String surname, String date, float height, float weight, String team){
        playersDao.openCurrentSessionwithTransaction();
        int tmp = playersDao.getData(name, surname, date,height, weight, team);
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

/*    public Players getPlayers(String name, String surname, String date){
        playersDao.openCurrentSession();
        Players player = playersDao.getPlayers(name, surname, date);
        playersDao.closeCurrentSession();
        return player;
    }*/
    public List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer){
        playersDao.openCurrentSessionwithTransaction();
        List<PlayerTeamsHistory> history = playersDao.getPlayerTeamsHistory(idPlayer);
        playersDao.openCurrentSessionwithTransaction();
        return history;
    }

    public List<String> getAll(){
        playersDao.openCurrentSession();
        List<String> list = playersDao.getAll();
        playersDao.closeCurrentSession();
        return list;
    }

    public int updatePlayer(String name,String team){
        playersDao.openCurrentSessionwithTransaction();
        int i = playersDao.updatePlayer(name, team);
        playersDao.closeCurrentSessionwithTransaction();
        return i;
    }



}