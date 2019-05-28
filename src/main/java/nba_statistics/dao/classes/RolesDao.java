package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IRolesDao;
import nba_statistics.entities.Roles;
import org.hibernate.query.Query;

public class RolesDao extends Dao implements IRolesDao {
    public Roles getRole(String name){
        Query<Roles> theQuery = getCurrentSession().createQuery("from Roles where name = :name", Roles.class);
        theQuery.setParameter("name",name);
        return theQuery.setMaxResults(1).uniqueResult();

    }
}
