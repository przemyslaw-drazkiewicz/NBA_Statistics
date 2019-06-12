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

    public void updateMatch(Matches entity) {getCurrentSession().update(entity);}

    public void update(Matches match) {
        updateMatch(match);
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

       // System.out.println(matches.size());

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

    public List<PlayerMatchAchievements> getAchievementPlayerInSeason(int idSeason) {

        List<PlayerMatchAchievements> achievements = new ArrayList<>();
        List<PlayerMatchAchievements> tmp = new ArrayList<>();
/*        List<PlayerMatchAchievements> achievements = new ArrayList<>();
        Query <Matches> theQuery = getCurrentSession().createQuery("from Matches where season_id = : idSeason").setParameter("idSeason", idSeason);
        List <Matches> matchesList = theQuery.getResultList();
        int idMatch = -1;

        for(int i =0; i<matchesList.size();i++){
            idMatch = matchesList.get(i).getId();
            Query <PlayerMatchAchievements> query2 = getCurrentSession().createQuery("select player_id, sum(scored_points) as suma from  PlayerMatchAchievements where match_id =: idMatch group by player_id order by suma desc").setParameter("idMatch", idMatch);
          //  if(query2.getResultList().size() != 0) achievements.add(query2.getResultList());
            achievements.add((PlayerMatchAchievements) query2.getResultList());
            System.out.println(achievements);
        }*/


        Query <Matches> theQuery = getCurrentSession().createQuery("from Matches where season_id = : idSeason").setParameter("idSeason", idSeason);
        List <Matches> matchesList = theQuery.getResultList();
        List<Integer> idMatches = new ArrayList<>();

        for(int i =0; i<matchesList.size(); i++){
            idMatches.add(i,matchesList.get(i).getId() );// = Collections.singletonList(matchesList.get(i).getId());
            System.out.println(idMatches);
        }

        int sizeAchiev = 0;
        Query<PlayerMatchAchievements> theQuery2;
        for(int i =0; i<idMatches.size(); i++){
            theQuery2 = getCurrentSession().createQuery("from PlayerMatchAchievements where match_id = :idMatches").setParameter("idMatches", idMatches.get(i));
            tmp=theQuery2.getResultList();

            sizeAchiev = 0;
            for(int j = 0; j<tmp.size(); j++){
                sizeAchiev = achievements.size();
                achievements.add(sizeAchiev, tmp.get(j));

            }
        }




        /*
    Query<PlayerMatchAchievements> theQuery = getCurrentSession().createQuery("select p from PlayerMatchAchievements p left JOIN fetch" +
            " p.Matches m  where p.match_id = m.match_id and m.season_id = 7", PlayerMatchAchievements.class);//.setParameter("idSeason", idSeason);
    achievements = theQuery.getResultList();*/
        return achievements;
    }
}
