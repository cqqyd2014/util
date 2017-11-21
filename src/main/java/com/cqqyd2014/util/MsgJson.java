package com.cqqyd2014.util;

public class MsgJson {
	static java.util.HashMap<String , Object> map;
	
	public static java.util.HashMap<String, Object> ObjectToMsgJson(Object o){
		map=new java.util.HashMap<String, Object>();
		map.put("msg", o);
		return map;
	}

}
