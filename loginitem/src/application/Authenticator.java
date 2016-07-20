package application;

import java.util.HashMap;
import java.util.Map;

/**
 * 간단한 암호 인증 클래스
 * @author user
 * 
 */
public class Authenticator {
	public static final Map<String, String> USERS = new HashMap<>();
	//스태틱 블락은 그 spring 책을 참조하자
	static{
		USERS.put("demo", "demo");
	}
	public static boolean validate(String user, String password){
		String validUserPassword = USERS.get(user);
		return validUserPassword != null && validUserPassword.equals(password);
	}
}
