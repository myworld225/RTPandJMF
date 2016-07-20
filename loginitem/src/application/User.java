package application;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	//아래 변수는 추후 db로 교체 될 수 있다.
	private static final Map<String, User> USERS = new HashMap<>();
	
	
	private String id; //user id
	private String email = "";
	private String phone = "";
	private boolean subscribed;
	private String address = "";
	
	
	
	
	/*회원의 존재 여부 확인*/
	public static User of(String id){
		User user = USERS.get(id);
		if (user == null) {
			user = new User(id);
			USERS.put(id, user);
		}
		return user;
	}
	
	private User(String id){
		this.id = id;
	}
	/**
	 * 
	 * @return id
	 */
	public String getId(){
		return id;
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	/**
	 * 
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 * 
	 * @return phone
	 */
	public String getPhone(){
		return phone;
	}
	/**
	 * 
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	/**
	 * @return subscribed(boolean)
	 */
	public boolean isSubscribed(){
		return subscribed;
	}
	/**
	 * 
	 */
	public void setSubscribed(boolean subscribed){
		this.subscribed = subscribed;
	}
	/**
	 * @return address
	 */
	public String getAddress(){
		return address;
	}
	/**
	 * 
	 */
	public void setAddress(String address){
		this.address = address;
	}
}
//@../../resources/fxmldemoImages/LoginLogo.png