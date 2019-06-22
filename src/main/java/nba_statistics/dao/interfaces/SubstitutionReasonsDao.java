package nba_statistics.dao.interfaces;

import nba_statistics.entities.SubstitutionReasons;

import java.util.ArrayList;

public interface SubstitutionReasonsDao {
    SubstitutionReasons getSubstitutionReason(String name);
    ArrayList<String> findAllSubstitutionReasonsName();
}
