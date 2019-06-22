package nba_statistics.dao.classes;


import nba_statistics.entities.SubstitutionReasons;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class SubstitutionReasonsDao extends Dao implements nba_statistics.dao.interfaces.SubstitutionReasonsDao {
    @Override
    public SubstitutionReasons getSubstitutionReason(String name)
    {
        Query<SubstitutionReasons> theQuery = getCurrentSession().createQuery("from SubstitutionReasons where name = :name", SubstitutionReasons.class);
        theQuery.setParameter("name",name);
        return theQuery.getSingleResult();
    }

    @Override
    public ArrayList<String> findAllSubstitutionReasonsName()
    {
        Query<String> theQuery = getCurrentSession().createQuery("select name from SubstitutionReasons ", String.class);
        ArrayList<String> substitutionReasonNameList = new ArrayList<String>(theQuery.getResultList());
        return substitutionReasonNameList;
    }
}
