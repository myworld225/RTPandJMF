package iconicdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by user on 2016-09-25.
 */
public class TestDB {
    public static void main(String[] args){
        Connection conn = new DBConnection().connect();
        String sql = "select * from user where username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "mucky");
            ResultSet rs = ps.executeQuery();
            //User u1 =  new User(rs.getInt("id"),rs.getString("name"),rs.getString("password"));//요기서 잘못된듯
            //System.out.println(u1.getName());
            User u1 = User.of("mucky");
            System.out.println(u1.getId());
        } catch (Exception ex){
            System.out.println("oops error!");
        }
    }
}
