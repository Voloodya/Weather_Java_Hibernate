package Tables;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "climate", catalog = "")
public class UsersEnity {
    private int idUsers;
    private String Login;
    private String Password;

    public UsersEnity(int idUsers, String login, String password) {
        this.idUsers = idUsers;
        Login = login;
        Password = password;
    }

    public UsersEnity() {
    }

    @Id
    @Column(name = "id_users", nullable = false)
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Column(name = "Login", nullable = false, length = 20)
    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 10)
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String toString(){
        return "idUsers: "+idUsers+" Login: "+Login+" Password: "+ Password;
    }

}
