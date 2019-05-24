package nba_statistics.services;

import nba_statistics.dao.classes.PlayersDao;
import nba_statistics.entities.HistoriaDruzynZawodnika;
import nba_statistics.entities.Zawodnicy;

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
    public int getData(String name, String surname, String date, float height, float weight, String team){
        playersDao.openCurrentSessionwithTransaction();
        int tmp = playersDao.getData(name, surname, date,height, weight, team);
        playersDao.closeCurrentSessionwithTransaction();
        return tmp;
    }

    public List<Zawodnicy> getPlayers(String name, String surname){
        playersDao.openCurrentSessionwithTransaction();
        List<Zawodnicy> players = playersDao.getPlayers(name, surname);
        playersDao.closeCurrentSessionwithTransaction();
        return players;
    }

    public List<HistoriaDruzynZawodnika> getPlayerTeamsHistory(int idPlayer){
        playersDao.openCurrentSessionwithTransaction();
        List<HistoriaDruzynZawodnika> history = playersDao.getPlayerTeamsHistory(idPlayer);
        playersDao.openCurrentSessionwithTransaction();
        return history;
    }



}