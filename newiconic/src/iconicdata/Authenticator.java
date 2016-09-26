package iconicdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by user on 2016-09-25.
 * 해야할 것 ? db 접속 후 id 와 name 인 결과가 있는지 false or true 반환
 */
public class Authenticator {
    //db connection 획득 -- static으로 conn을 얻었기 때문에 추후 문제가 발생할 여지가 있다 (해결방안은 static을 안쓰고 할때마다 연결을 얻고 해제하는것이다)
    private static final Connection conn;
    static {
        conn = new DBConnection().connect();
    }

    public static boolean validate(String name, String password){
        String sql = "select * from user where username=? and password=?";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("있는 레코드");
                return true;
            } else {
                System.out.println("없는 레코드");
                return false;
            }
        }catch(Exception ex){
            Logger.getLogger(Authenticator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
