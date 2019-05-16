package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IMatchesDao;
import nba_statistics.entities.Druzyny;
import nba_statistics.entities.Mecze;
import nba_statistics.entities.Sezony;
import nba_statistics.services.SeasonsService;
import nba_statistics.services.TeamsService;
import org.hibernate.query.Query;

import java.util.List;

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


}
