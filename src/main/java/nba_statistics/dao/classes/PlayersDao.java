package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IPlayersDao;
import nba_statistics.entities.PlayerTeamsHistory;
import nba_statistics.entities.Players;
import nba_statistics.entities.Teams;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PlayersDao extends Dao implements IPlayersDao {


    public PlayersDao(){

    }
    public void persist(Players entity) {
            getCurrentSession().save(entity);
    }

    public void updatePlayerToDatabase(Players entity) {getCurrentSession().update(entity);}

    public int getData(String name, String surname, String date, float height, float weight, String team, String imageURL) {
        try{
            Date date1= Date.valueOf(date);
        }catch(IllegalArgumentException e){
            return 2;
        }
        if (height <= 0 || weight <=0)
            return 5;
        if (imageURL.equals(""))
            return 4;
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
    public int updatePlayer(Players player, String team, String imageURL, String height, String weight, int currSeason) {

        boolean isChangedTeam = false;

        if ((isTransferRepeat(player.getId(), currSeason))) {
            return 80; //only one update in the season
        }
        else
        {
            TeamsService teamsService = new TeamsService();
            if (team.equals("RETIRED")){
                player.setTeam(null);
                updatePlayerToDatabase(player);
                return 0;
            }else {
                Teams d = teamsService.getTeam(team); //not null for sure
                if (!(d.equals(player.getTeam()))) //changed team
                    isChangedTeam = true;
                player.setTeam(d);
            }
        }

        if (!(imageURL.equals(""))) //if equal ="" mean no update image
        {
            if (getPlayerByImage(imageURL)!=null )
                return 8; //not unique
            else
                player.setImageURL(imageURL);
        }
        else { //no update image but...
            if (isChangedTeam) //...must changed image if changed team
                return 90;
        }

        try{
            float weightFloat = Float.parseFloat(weight);
            float heightFloat = Float.parseFloat(height);
            if (weightFloat <= 0 || heightFloat <= 0 )
                return 70;
            else{
                player.setWeight(weightFloat);
                player.setHeight(heightFloat);
            }

        }catch(NumberFormatException exc){
            return 60;
        }
        updatePlayerToDatabase(player);
        return 0;

    }

    private boolean isTransferRepeat(int idPlayer, int idSeason){
        Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where player_id=:idPlayer and season_id =:idSeason")
                .setParameter("idPlayer", idPlayer)
                .setParameter("idSeason", idSeason);
        if (theQuery.getResultList().size() == 0)
            return false;
        else
            return true;
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
        try{
            return theQuery.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Players getPlayer(String name, String surname, String date) {
        Query<Players> theQuery = getCurrentSession().createQuery("from Players where name=:name and surname =:surname and date_of_birth = :date")
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("date", date);
        try{
            return theQuery.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    public List<PlayerTeamsHistory> getPlayerTeamsHistory(int idPlayer){
        Query<PlayerTeamsHistory> theQuery = getCurrentSession().createQuery("from PlayerTeamsHistory where player_id = :idPlayer and team_id != null")
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

    @Override
    public Players getPlayerFromAutoCompleteField(String value) {
        String[] splited = value.split("\\s+");

        if (splited.length != 2 && splited.length !=3)
            return null;
        if (splited.length == 2){
            return getPlayer(splited[0],splited[1]);
        }else{ //=3
            return getPlayer(splited[0], splited[1],splited[2]);
        }
    }
}