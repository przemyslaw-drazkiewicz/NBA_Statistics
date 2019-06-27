package nba_statistics.services;

import nba_statistics.dao.classes.HelpDao;

public class HelpService {

    private static HelpDao helpDao;

    public HelpService(){
        helpDao = new HelpDao();
    }

    public String getText(String view){
        helpDao.openCurrentSession();
        String text = helpDao.getText(view);
        helpDao.closeCurrentSession();
        return text;

    }

}
