package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayersDao;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;
import nba_statistics.entities.Teams;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class PlayersDao extends Dao implements IPlayersDao {


    public PlayersDao(){

    }
    public void persist(Players entity) {
        getCurrentSession().save(entity);
    }

    public int getData(String name, String surname, String date, float height, float weight, String team) {
        try{
            Date date1= Date.valueOf(date);
        }catch(IllegalArgumentException e){
            return 2;
        }
        Players z = new Players(name, surname, date, height, weight);
        TeamsService teamsService = new TeamsService();
        Teams d = teamsService.getTeam(team);
        if (d == null)
            return 1;

        z.setTeam(d);
        persist(z);
        return 0;
    }

    public int updatePlayer(String name, String surname, String team){
        //Query<Players> theQuery = getCurrentSession().createQuery("from Players where imie = :name and nazwisko = :surname",Players.class);
        TeamsService teamsService = new TeamsService();
        Teams d = teamsService.getTeam(team);
        if (d == null)
            return 1;

        int id = d.getId();

        getCurrentSession().createQuery("update Players set team_id = :id  where name = :name and surname = :surname")
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .executeUpdate();
        return 0;


    }

    public List<Players> getPlayers(String name, String surname){

        Query<Players> theQuery = getCurrentSession().createQuery("from Players where name =:name and surname = :surname")
                .setParameter("name", name)
                .setParameter("surname", surname);
        List<Players> players = theQuery.getResultList();

        return players;
    }

    public List<Players> getPlayers(int id){

        Query<Players> theQuery = getCurrentSession().createQuery("from Players where team_id =:id")
                .setParameter("id", id);
        List<Players> players = theQuery.getResultList();
        return players;
    }

    public List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer){
        Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where player_id = :idPlayer")
                .setParameter("idPlayer", idPlayer);
        List<PlayerTeamsHistory> history = theQuery.getResultList();

        return history;
    }
}