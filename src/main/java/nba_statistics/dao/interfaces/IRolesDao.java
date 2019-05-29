package nba_statistics.dao.interfaces;

import nba_statistics.entities.Roles;

import java.util.ArrayList;

public interface IRolesDao {
    Roles getRole(String name);
    ArrayList<String> findAllRoleName();
}
