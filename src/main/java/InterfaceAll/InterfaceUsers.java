package InterfaceAll;

import Tables.UsersEnity;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceUsers {

    public void addInformationUsers (UsersEnity Users) throws SQLException;
    public List<UsersEnity> getAllUsers() throws SQLException;
    public UsersEnity getUsersId(int id) throws SQLException;
    public void updateUsers(UsersEnity Users) throws SQLException;
    public void deleteInformationUsers(int idDel) throws SQLException;
}
