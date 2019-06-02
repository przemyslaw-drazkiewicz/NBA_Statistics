package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IMatchesDao;
import nba_statistics.entities.*;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;
import java.sql.Date;

import java.util.*;

public class MatchesDao extends Dao implements IMatchesDao{



    public List<Matches> findAll() {
        // List<Matches> matches = (List<Matches>) getCurrentSession().createQuery("from Matches").list();
        Query<Matches> theQuery = getCurrentSession().createQuery("from Matches", Matches.class);
        List<Matches> matches = theQuery.getResultList();

        return matches;
    }

    public List<Matches> findAllAtDate(String date) {

        Query<Matches> theQuery = getCurrentSession().createQuery("from Matches where date = :date")
                .setParameter("date",date);
        List<Matches> matches = theQuery.getResultList();

        return matches;
    }

    public void persist(Matches entity) {
        getCurrentSession().save(entity);
    }

    public int getData(String home, String away, String date, String season){
        TeamsService teamsService = new TeamsService();
        SeasonsService seasonsService = new SeasonsService();
        Teams teamHome = teamsService.getTeam(home);
        Teams teamAway = teamsService.getTeam(away);
        if (teamHome == null){
            return 1;
        } else if (teamAway == null){
            return 2;
        }else if (home.equals(away)){
            return 4;
        }
        else {
            Seasons s = seasonsService.getSeason(season);
            Matches mecz = new Matches();

            mecz.setHostTeam(teamHome);
            mecz.setGuestTeam(teamAway);

            try{
                Date checkDate = Date.valueOf(date); //'return' IllegalArgumentException exception if wrong date format
                Date startDate = Date.valueOf(s.getStartDate());
                Date endDate = Date.valueOf(s.getEndDate());
                if (startDate.compareTo(checkDate) > 0 || endDate.compareTo(checkDate) < 0)
                    return 5;
                mecz.setDate(date);
                mecz.setSeason(s);
                persist(mecz);
                return 0;
            }
            catch (IllegalArgumentException e)
            {
                return 3;
            }


        }


    }


    public List<PlayerMatchAchievements> getAchievementPlayerInMatch(int idPlayer, int idSeason, int idTeam) {
        Query<Matches> theQuery = getCurrentSession().createQuery
                ("from Matches where season_id =: idSeason and (host_team_id =: idTeam or guest_team_id =: idTeam)")
                .setParameter("idSeason", idSeason)
                .setParameter("idTeam", idTeam)
                .setParameter("idTeam", idTeam);
        List<Matches> matches = theQuery.getResultList();
        int idMatch = -1;



        List<PlayerMatchAchievements> tmp = new ArrayList<>();
        List<PlayerMatchAchievements> achievements = new ArrayList<>();

        System.out.println(matches.size());

        for(int i =0; i<matches.size(); i++){
            idMatch = matches.get(i).getId();
            Query <PlayerMatchAchievements> query2 = getCurrentSession().createQuery
                    ("from PlayerMatchAchievements where match_id =: idMatch and player_id =: idPlayer")
                    .setParameter("idMatch", idMatch)
                    .setParameter("idPlayer", idPlayer);

            if(query2.getResultList().size() != 0) achievements.add( query2.getSingleResult());
            System.out.println(achievements);
        }

        return achievements;

    }

    @Override
    public List<Matches> getMatches(String team, String date) {
        TeamsService teamsService = new TeamsService();
        int id = teamsService.getId(team);
        SeasonsService seasonsService = new SeasonsService();
        int id2 = seasonsService.getId(date);
        Query<Matches> theQuery = getCurrentSession().createQuery("from Matches where (host_team_id = :id OR guest_team_id = :id) AND (season_id = :id2)")
                .setParameter("id", id)
                .setParameter("id", id)
                .setParameter("id2", id2);

        List <Matches> matchesList = theQuery.getResultList();
        return matchesList;
    }
}
