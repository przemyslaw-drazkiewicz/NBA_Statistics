package nba_statistics.dao.interfaces;

import nba_statistics.entities.Roles;
import nba_statistics.entities.Users;

import java.util.ArrayList;
import java.util.List;

public interface IUsersDao {
    String addUser(String login, String password, String password2);

    Users getUser(String login);

    Roles authorize(String login, String password); //Returns role if authorization is successful, null if failure

    List<Users> findAll();

    ArrayList<String> findAllLogin();

    void updateUser(String userLogin, String roleName);
}