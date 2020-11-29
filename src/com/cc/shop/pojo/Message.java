package com.cc.shop.pojo;


public class Message {
	private Integer messageid;
	private Integer oid;
	private String message;
	private User user;
	private String messagedate;
	
	public Integer getMessageid() {
		return messageid;
	}
	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessagedate() {
		return messagedate;
	}
	public void setMessagedate(String messagedate) {
		this.messagedate = messagedate;
	}
}
