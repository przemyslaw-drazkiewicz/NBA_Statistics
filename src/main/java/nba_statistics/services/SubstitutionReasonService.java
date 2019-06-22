package nba_statistics.services;

import nba_statistics.dao.classes.SubstitutionReasonsDao;
import nba_statistics.entities.SubstitutionReasons;

import java.util.ArrayList;

public class SubstitutionReasonService {
    private static SubstitutionReasonsDao substitutionReasonsDao;

    public SubstitutionReasonService(){substitutionReasonsDao=new SubstitutionReasonsDao();};

    public SubstitutionReasons getSubstitutionReason (String name){

        substitutionReasonsDao.openCurrentSession();
        SubstitutionReasons substitutionReason= substitutionReasonsDao.getSubstitutionReason(name);
        substitutionReasonsDao.closeCurrentSession();
        return substitutionReason;
    }

    public ArrayList<String> findAllSubstitutionReasonsName(){
        substitutionReasonsDao.openCurrentSession();
        ArrayList<String> substitutionReasonsNameList = substitutionReasonsDao.findAllSubstitutionReasonsName();
        substitutionReasonsDao.closeCurrentSession();
        return substitutionReasonsNameList;
    }
}
