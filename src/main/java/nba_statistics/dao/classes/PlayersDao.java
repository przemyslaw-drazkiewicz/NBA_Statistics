package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayersDao;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;
import nba_statistics.entities.Seasons;
import nba_statistics.entities.Teams;
import nba_statistics.services.PlayerTeamsHistoryService;
import nba_statistics.services.PlayersService;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayersDao extends Dao implements IPlayersDao {


    public PlayersDao(){

    }
    public void persist(Players entity) {
            getCurrentSession().save(entity);
    }

    public int getData(String name, String surname, String date, float height, float weight, String team, String imageURL) {
        try{
            Date date1= Date.valueOf(date);
        }catch(IllegalArgumentException e){
            return 2;
        }

        Players z = new Players(name, surname, date, height, weight,imageURL);
        TeamsService teamsService = new TeamsService();
        Teams d = teamsService.getTeam(team);
        if (d == null)
            return 1;

        z.setTeam(d);
        if (getPlayerByImage(imageURL)!=null)
            return 3;
        persist(z);

        return 0;
    }

    @Override
    public int updatePlayer(String name, String team, String imageURL) {
        String[] splited = name.split("\\s+");
        String n,s,date;
        TeamsService teamsService = new TeamsService();
        Teams d = teamsService.getTeam(team);

        if (d == null)
            return 4;
        int id = d.getId();
        switch (splited.length){
            case 2:
                n = splited[0];
                s = splited[1];
                List<Players> player = getPlayers(n, s);
                if (player.size() == 0)
                    return 2;
                else if (player.size() > 1)
                    return 5;
                if (player.get(0).getTeam().getName().equals(team))
                    return 1;

                getCurrentSession().createQuery("update Players set team_id = :id, imageURL = :imageURL where name = :n and surname = :s")
                        .setParameter("id", id)
                        .setParameter("imageURL",imageURL)
                        .setParameter("n", n)
                        .setParameter("s", s)
                        .executeUpdate();


                return 0;
            case 3:
                n = splited[0];
                s = splited[1];
                date = splited[2];
                try{Date checkDate = Date.valueOf(date);}
                catch(IllegalArgumentException e){return 6;}
                List<Players> player2 = getPlayers(n, s, date);
                if (player2.size() == 0)
                    return 2;
                if (player2.get(0).getTeam().getName().equals(team))
                    return 1;
                getCurrentSession().createQuery("update Players set team_id = :id where name = :n and surname = :s and date_of_birth = :date")
                        .setParameter("id", id)
                        .setParameter("n", n)
                        .setParameter("s", s)
                        .setParameter("date", date)
                        .executeUpdate();
                return 0;
                default:
                    return 3;

        }


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

    public List<Players> getPlayersById(int id){

        Query<Players> theQuery = getCurrentSession().createQuery("from Players where player_id =:id")
                .setParameter("id", id);
        List<Players> players = theQuery.getResultList();
        return players;
    }

    @Override
    public List<Players> getPlayers(String name, String surname, String date) {
        Query<Players> theQuery = getCurrentSession().createQuery("from Players where name =:name and surname = :surname and date_of_birth =:date")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("date", date);
        List<Players> players= theQuery.getResultList();
        return players;
    }

    @Override
    public Players getPlayer(String name, String surname) {
            Query<Players> theQuery = getCurrentSession().createQuery("from Players where name=:name and surname =:surname")
                    .setParameter("name", name)
                    .setParameter("surname", surname);
            return theQuery.getSingleResult();
    }

    @Override
    public Players getPlayer(String name, String surname, String date) {
        Query<Players> theQuery = getCurrentSession().createQuery("from Players where name=:name and surname =:surname and date_of_birth = :date")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("date", date);
        return theQuery.getSingleResult();
    }

    public List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer){
        Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where player_id = :idPlayer")
                .setParameter("idPlayer", idPlayer);
        List<PlayerTeamsHistory> history = theQuery.getResultList();

        return history;
    }
    public Players getPlayerByImage(String image){
        Query<Players> theQuery = getCurrentSession().createQuery("from Players where imageURL=:image")
                .setParameter("image", image);
        try{
            return theQuery.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<String> getAll() {
        Query<Players> theQuery = getCurrentSession().createQuery("from Players");
        List<Players> playerList = theQuery.getResultList();
        List<String> getAll = new ArrayList<>();
        int i =0;
        int ii = playerList.size();
        while(i!=ii){
            Players p = playerList.get(i);
            List<Players> l = getPlayers(p.getName(), p.getSurname());
            if (l.size() == 1){
                String player = p.getName() + " " + p.getSurname();
                getAll.add(player);
                i++;
            }else{
                for (Players pp : l){
                    getAll.add(pp.getName() + " " + pp.getSurname() + " " + pp.getDateOfBirth());
                    playerList.remove(pp); ii--;
                }
            }
        }
        return getAll;

    }

    public List<PlayerTeamsHistory> getPlayersInTeam(int season, int team) {

        Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where season_id = :season and team_id = :team")
                .setParameter("team", team)
                .setParameter("season", season);
        List<PlayerTeamsHistory> list = theQuery.getResultList();
        return list;
    }
}