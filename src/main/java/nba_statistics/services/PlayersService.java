package nba_statistics.services;

import nba_statistics.dao.classes.PlayersDao;
import nba_statistics.entities.Zawodnicy;

import java.util.List;

public class PlayersService {
    private static PlayersDao playersDao;

    public PlayersService(){
        playersDao = new PlayersDao();
    }

    public void updatePlayer(String name, String surname,String team){
        playersDao.openCurrentSessionwithTransaction();
        playersDao.updatePlayer(name, surname,team);
        playersDao.closeCurrentSessionwithTransaction();
    }
    public void getData(String name, String surname, String date, float height, float weight, String team){
        playersDao.openCurrentSessionwithTransaction();
        playersDao.getData(name, surname, date,height, weight, team);
        playersDao.closeCurrentSessionwithTransaction();
    }

    public List<Zawodnicy> getPlayers(String surname){
        playersDao.openCurrentSessionwithTransaction();
        List<Zawodnicy> players = playersDao.getPlayers(surname);
        playersDao.closeCurrentSessionwithTransaction();
        return players;
    }
}