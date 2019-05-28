package nba_statistics.services;

import nba_statistics.dao.classes.RolesDao;
import nba_statistics.entities.Roles;

public class RolesService {
    private static RolesDao rolesDao;

    public RolesService(){rolesDao=new RolesDao();}

    public Roles getRole(String name){
        rolesDao.openCurrentSession();
        Roles role = rolesDao.getRole(name);
        rolesDao.closeCurrentSession();
        return role;
    }
}
