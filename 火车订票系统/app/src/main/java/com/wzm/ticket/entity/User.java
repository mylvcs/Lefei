package com.wzm.ticket.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String id;
	private String username;
	private String password;
	private String email;
	private long telNumber;
	private String realname;
	private long idCard;//…Ì∑›÷§
	public User(String username, String password, String email, long telNumber,
			String realname, long idCard) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.telNumber = telNumber;
		this.realname = realname;
		this.idCard = idCard;
	}
	public User() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public long getIdCard() {
		return idCard;
	}
	public void setIdCard(long idCard) {
		this.idCard = idCard;
	}
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public long getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(long telNumber) {
		this.telNumber = telNumber;
	}

}
