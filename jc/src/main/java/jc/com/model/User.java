package jc.com.model;

public class User {
	private String id;
	private String name;
	private String loginName;
	private String password;
	private String salt;//加密 盐
	public User() {
	// TODO Auto-generated constructor stub
	}
	public User(String name,String username,String password,String salt) {
		this.loginName = name;
		this.loginName = username;
		this.password = password;
		this.salt = salt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
