package com.archer.model;

import java.util.Date;

public class MessageBean {
	private String name;
	private String msg;
	private Type type;
	private Date date;
	public MessageBean(){
		
	}
	public MessageBean(String msg, Date date, Type type){
		this.msg=msg;
		this.date=date;
		this.type=type;
	}
	public String getName() {
		return name;
	}
	public enum Type{
		INCOMING,OUTCOMING
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
