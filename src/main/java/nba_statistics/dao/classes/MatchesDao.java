package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IMatchesDao;
import nba_statistics.entities.*;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.util.*;

public class MatchesDao extends Dao implements IMatchesDao{



    public List<Mecze> findAll() {
        // List<Mecze> matches = (List<Mecze>) getCurrentSession().createQuery("from Mecze").list();
        Query<Mecze> theQuery = getCurrentSession().createQuery("from Mecze", Mecze.class);
        List<Mecze> matches = theQuery.getResultList();

        return matches;
    }

    public void persist(Mecze entity) {
        getCurrentSession().save(entity);
    }

    public void getData(String home, String away, String date, String season){
        TeamsService teamsService = new TeamsService();
        SeasonsService seasonsService = new SeasonsService();
        Druzyny teamHome = teamsService.getTeam(home);
        Druzyny teamAway = teamsService.getTeam(away);
        Sezony s = seasonsService.getSeason(season);
        Mecze mecz = new Mecze();
        mecz.setData(date);
        mecz.setDruzGosp(teamHome);
        mecz.setDruzGosc(teamAway);
        mecz.setSezon(s);
        persist(mecz);
    }


    public List<OsiagnieciaZawWMeczu> getAchievementPlayerInMatch(int idPlayer, int idSeason, int idTeam) {
        Query<Mecze> theQuery = getCurrentSession().createQuery
                ("from Mecze where id_sezonu =: idSeason and (id_druzyny_gospodarza =: idTeam or id_druzyny_goscia =: idTeam)")
                .setParameter("idSeason", idSeason)
                .setParameter("idTeam", idTeam)
                .setParameter("idTeam", idTeam);
        List<Mecze> matches = theQuery.getResultList();
        int idMatch = -1;



        List<OsiagnieciaZawWMeczu> tmp = new ArrayList<>();
        List<OsiagnieciaZawWMeczu> achievements = new ArrayList<>();

        System.out.println(matches.size());

        for(int i =0; i<matches.size(); i++){
            idMatch = matches.get(i).getId();
            Query <OsiagnieciaZawWMeczu> query2 = getCurrentSession().createQuery
                    ("from OsiagnieciaZawWMeczu where id_meczu =: idMatch and id_zawodnika =: idPlayer")
                    .setParameter("idMatch", idMatch)
                    .setParameter("idPlayer", idPlayer);

            if(query2.getResultList().size() != 0) achievements.add( query2.getSingleResult());
            System.out.println(achievements);
        }

        return achievements;

    }
}
