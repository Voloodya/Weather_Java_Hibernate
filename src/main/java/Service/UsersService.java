package Service;

import Build.SessionFactory;
import InterfaceAll.InterfaceUsers;
import Tables.UsersEnity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;


public class UsersService extends SessionFactory implements InterfaceUsers {
    public void addInformationUsers(UsersEnity Users) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session=getSession();
        session.save(Users);

        //close session with a transaction
        closeTransactionSession();
    }

    public List<UsersEnity> getAllUsers() throws SQLException {
        //open session with a transaction
        Session session=openTransactionSession();

        String sqlSelect="SELECT * FROM users";
        //В createSQLQuery создаётся сам SQL запрос,с помощью addEntity указывается, какая сущность ожидается в результате.
        SQLQuery query=session.createSQLQuery(sqlSelect).addEntity(UsersEnity.class);
        List<UsersEnity> Users=query.list();

        //close session with a transaction
        closeTransactionSession();
        return Users;
    }

    public UsersEnity getUsersId(int id) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM UsersEnity WHERE idUsers=:id";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("id",id);
        List Users=query.list();
        UsersEnity UsersId=(UsersEnity)Users.get(0);
        closeTransactionSession();
        return UsersId;
    }
    public UsersEnity getUsersLoginPasw(String login,String password) throws SQLException {
        Session session=openTransactionSession();

        String hqlSelect="FROM UsersEnity WHERE Login=:login AND Password=:password";
        Query query=session.createQuery(hqlSelect);
        query.setParameter("login",login);
        query.setParameter("password",password);
        List<UsersEnity> Users;
        UsersEnity User=null;
        for (UsersEnity usersEnity : Users = query.list()) {
            User=usersEnity;
        }
        closeTransactionSession();
        return User;
    }

    public void updateUsers(UsersEnity Users) throws SQLException {

    }

    public void deleteInformationUsers(int idDel) throws SQLException {
        Session session=openTransactionSession();

        String hqlDelete="DELETE FROM UsersEnity WHERE idUsers= :idDel";
        Query query=session.createQuery(hqlDelete);
        query.setParameter("idDel",idDel);

        int result = query.executeUpdate();
        System.out.println("Rows affected delete: " + result);

        closeTransactionSession();
    }
}
