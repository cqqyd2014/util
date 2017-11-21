package com.cqqyd2014.util.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapToolsCompareResult {
	/*
	 * Map<String,java.math.BigDecimal>两个对比结果
	 * 
	 * 
	 * 
	 */
	
	//flag有三个值，true为两个HashMap完全一致，false为有区别，此时ABCompare结果不为空
	boolean flag;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public HashMap<String, java.math.BigDecimal> getABCompare() {
		return ABCompare;
	}
	public void setABCompare(HashMap<String, java.math.BigDecimal> aBCompare) {
		ABCompare = aBCompare;
	}
	//AB对比结果，以A为标准，如果B的值大于A，则java.math.BigDecimal为负数，反之为正数。且包含B有，而A没有之情况
	HashMap<String,java.math.BigDecimal> ABCompare;
	public String toCompareString(String str1,String str2) {
		String rs="";
		for (Map.Entry<String, java.math.BigDecimal> entry : ABCompare.entrySet()) {  
			  
		   // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			String key=entry.getKey();
			java.math.BigDecimal value=entry.getValue();
			if (value.compareTo(new java.math.BigDecimal(0))==0) {
				//跳过
			}
			if (value.compareTo(new java.math.BigDecimal(0))==1) {
				rs=rs+key+str1+"比"+str2+"多了"+value.toString()+"/";
			}
			if (value.compareTo(new java.math.BigDecimal(0))==-1) {
				rs=rs+key+str1+"比"+str2+"少了"+value.toString()+"/";
			}
			
		  
		} 
		rs=rs.substring(0, rs.length()-1);
		
		return rs;
	}
	

}
