package iconic;

/**
 * Created by user on 2016-08-12.
 */

import java.util.HashMap;
import java.util.Map;

public class Authenticator {

    public static final Map<String, String> USERS = new HashMap<>();

    static{
        USERS.put("demo","demo");
    }

    public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }
}
