package nba_statistics.services;

import nba_statistics.dao.classes.PositionsDao;
import nba_statistics.entities.Positions;

import java.util.ArrayList;

public class PositionsService {
    private static PositionsDao positionsDao;

    public PositionsService(){positionsDao=new PositionsDao();};

    public Positions getPosition (String name){

        positionsDao.openCurrentSession();
        Positions position = positionsDao.getPosition(name);
        positionsDao.closeCurrentSession();
        return position;
    }

    public ArrayList<String> findAllPositionsName(){
        positionsDao.openCurrentSession();
        ArrayList<String> positionsNameList = positionsDao.findAllPositionsName();
        positionsDao.closeCurrentSession();
        return positionsNameList;
    }
}
