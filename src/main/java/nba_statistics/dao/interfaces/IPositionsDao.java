package nba_statistics.dao.interfaces;

import nba_statistics.entities.Positions;

import java.util.ArrayList;

public interface IPositionsDao {

    Positions getPosition(String name);
    ArrayList<String> findAllPositionsName();

}
