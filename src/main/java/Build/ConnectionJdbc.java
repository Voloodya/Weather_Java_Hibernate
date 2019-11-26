package Build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionJdbc {

    public ConnectionJdbc() {
    }

    public Connection getConnection(){ //Create connection
        Connection connection=null;
        //Регистрация драйвера в драйвере менеджере
        try {
            Class.forName("com.mysql.jdbc.Driver");//Передача полного пути кдрайверу
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/climate","root","12345");
            System.out.println("Connection created");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection not created");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection not created");
        }
        return connection;
    }
}
