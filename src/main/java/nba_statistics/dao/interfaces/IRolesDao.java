package nba_statistics.dao.interfaces;

import nba_statistics.entities.Roles;

public interface IRolesDao {
    Roles getRole(String name);
}
