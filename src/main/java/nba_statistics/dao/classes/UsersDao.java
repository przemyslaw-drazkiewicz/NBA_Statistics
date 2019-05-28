package nba_statistics.dao.classes;

import nba_statistics.dao.interfaces.IUsersDao;
import nba_statistics.entities.Roles;
import nba_statistics.entities.Users;
import nba_statistics.services.RolesService;
import org.hibernate.query.Query;

import javax.management.relation.Role;

public class UsersDao extends Dao implements IUsersDao {
    public void persist(Users entity) {
        getCurrentSession().save(entity);
    }
    public Users getUser(String login){
        Query<Users> theQuery = getCurrentSession().createQuery("from Users where login = :login", Users.class);
        theQuery.setParameter("login", login);
        //theQuery.executeUpdate();
        return theQuery.setMaxResults(1).uniqueResult();
    }

    public String addUser(String login,String password, String password2){
            if (!password.equals(password2)){
                return "Passwords must be equal!";
            }else if (getUser(login)==null){
                Roles role = new RolesService().getRole("VIEWER");
                persist(new Users(login,password,role));
                return "Correct";
            }
            return "User is already registered!";
    }
    public Roles authorize(String login, String password){
        Users user = getUser(login);
        if (user!=null)
            if(user.getPassword().equals(password))
                return user.getRole();
         return null;
    }
}
