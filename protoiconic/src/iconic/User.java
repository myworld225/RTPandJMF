package iconic;

/**
 * Created by user on 2016-08-12.
 */

import java.util.HashMap;
import java.util.Map;
public class User {

    private static final Map<String, User> USERS = new HashMap<>();

    private String id;
    private String email = "";
    //기타 개인정보가 추가될 수 있다.

    public static User of(String id){
        User user = USERS.get(id);
        if(user == null){
            user = new User(id);
            USERS.put(id, user);
        }
        return user;
    }

    private User(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
