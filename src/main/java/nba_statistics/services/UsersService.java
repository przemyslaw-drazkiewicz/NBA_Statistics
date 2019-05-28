package nba_statistics.services;

import nba_statistics.dao.classes.UsersDao;
import nba_statistics.entities.Roles;
import nba_statistics.entities.Users;

public class UsersService {
    private static UsersDao usersDao;

    public UsersService() { usersDao=new UsersDao();}

    public String addUser(String login, String password, String password2){
        usersDao.openCurrentSession();
        String msg = usersDao.addUser(login,password,password2);
        usersDao.closeCurrentSession();
        return msg;
    }

    public Users getUser(String login){
        usersDao.openCurrentSession();
        Users user = usersDao.getUser(login);
        usersDao.closeCurrentSession();
        return user;
    }

    public Roles authorize(String login, String password){ //Returns role if authorization is successful, null if failure
        usersDao.openCurrentSession();
        Roles role = usersDao.authorize(login,password);
        usersDao.closeCurrentSession();
        return role;
    }
}
