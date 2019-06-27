package nba_statistics.services;

import nba_statistics.dao.classes.HelpDao;

public class HelpService {

    private static HelpDao helpDao;

    public HelpService(){
        helpDao = new HelpDao();
    }

    public String getText(String id){
        helpDao.openCurrentSession();
        String text = helpDao.getText(id);
        helpDao.closeCurrentSession();
        return text;

    }

}
