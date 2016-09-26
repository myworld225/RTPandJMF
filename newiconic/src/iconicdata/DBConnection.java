package iconicdata;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 2016-09-25.
 * db 연결을 위한 클래스
 */
public class DBConnection {

    private Connection DBConnection;

    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conncetion Success");
        } catch(ClassNotFoundException e) {
            System.out.println("Connection Fail" + e);
        }

        String url = "jdbc:mysql://localhost:3306/javaapp";

        try{
            DBConnection = (Connection)DriverManager.getConnection(url,"root","qkrrl1223");
            System.out.println("Database Connected");
        } catch(SQLException e){
            System.out.println("No Database" + e);
        }

        return DBConnection;
    }

}
