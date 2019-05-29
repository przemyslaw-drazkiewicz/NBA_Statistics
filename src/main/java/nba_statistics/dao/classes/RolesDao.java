package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IRolesDao;
import nba_statistics.entities.Roles;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class RolesDao extends Dao implements IRolesDao {
    public Roles getRole(String name){
        Query<Roles> theQuery = getCurrentSession().createQuery("from Roles where name = :name", Roles.class);
        theQuery.setParameter("name",name);
        return theQuery.setMaxResults(1).uniqueResult();
    }

    @Override
    public ArrayList<String> findAllRoleName() {
        Query<String> theQuery = getCurrentSession().createQuery("select name from Roles ", String.class);
        ArrayList<String> roleNameList = new ArrayList<String>(theQuery.getResultList());
        return roleNameList;
    }
}
