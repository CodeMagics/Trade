package com.skyfin.bean;
/*
 * uername 
 */

public class User {

	private String username;
	private String passwd;
	@Override
	public String toString() {
		return "User [username=" + username + ", passwd=" + passwd + "]";
	}
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(String username, String passwd) {
		super();
		this.username = username;
		this.passwd = passwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
