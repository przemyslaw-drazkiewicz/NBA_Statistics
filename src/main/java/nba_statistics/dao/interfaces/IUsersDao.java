package nba_statistics.dao.interfaces;

import nba_statistics.entities.Roles;
import nba_statistics.entities.Users;

public interface IUsersDao {
    String addUser(String login,String password, String password2);
    Users getUser(String login);
    Roles authorize(String login, String password); //Returns role if authorization is successful, null if failure
}
