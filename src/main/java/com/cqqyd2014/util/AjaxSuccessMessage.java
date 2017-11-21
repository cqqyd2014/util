package com.cqqyd2014.util;

public class AjaxSuccessMessage {

	boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	String body;
	String sound;
	Object o;
	Object o2;
	Object o3;
	Object o4;
	Object o5;
	public Object getO5() {
		return o5;
	}
	public void setO5(Object o5) {
		this.o5 = o5;
	}
	public Object getO4() {
		return o4;
	}
	public void setO4(Object o4) {
		this.o4 = o4;
	}
	public Object getO2() {
		return o2;
	}
	public void setO2(Object o2) {
		this.o2 = o2;
	}
	public Object getO3() {
		return o3;
	}
	public void setO3(Object o3) {
		this.o3 = o3;
	}
	public Object getO() {
		return o;
	}
	public void setO(Object o) {
		this.o = o;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public java.util.HashMap<String, Object> toMap(){
		java.util.HashMap<String , Object> map=new java.util.HashMap<String, Object>();
		map.put("msg", this);
		return map;
	}
		
		
	
}