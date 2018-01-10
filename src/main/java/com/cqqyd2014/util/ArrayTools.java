package com.cqqyd2014.util;

public class ArrayTools {
	
	

	/**
	 * @Title: 数组变为数组链表
	 * @Description: “[a,b,c,d]”变为ArrayList
	 * 
	 * @return java.util.Array<String>
	 * @param str
	 *            传入的源数组对象
	 * 
	 */

	

	public static java.util.ArrayList<? extends Object> convertArrayToArrayList(Object[] str) {
		java.util.ArrayList<? super  Object> str_array=new java.util.ArrayList<Object>();
		for (int i=0;i<str.length;i++) {
			str_array.add(str[i]);
		}

		return str_array;
	}
	
	/**
	 * @Title: 数组变为字符串
	 * @Description: “[a,b,c,d]”变为字符串
	 * 
	 * @return String
	 * @param Object[] os
	 *            传入的源数组对象
	 * 
	 */
	
	public static String toString(Object[] os) {
		int i = os.length;
		if (i == 0) {
			return "[]";
		}
		String rs = "[";

		Object o = os[0];
		Class<? extends Object> clazz = o.getClass();
		String class_name = clazz.getName();
		for (int j=0;j<i;j++){
			Object o_item=os[j];
			switch (class_name)

			{

			case "java.lang.String":

				rs=rs+(String)o_item+",";

				break;

			case "java.math.BigDecimal":

				java.math.BigDecimal dec=(java.math.BigDecimal)o_item;
				rs=rs+dec.toString()+",";

				break;

			default:

				System.out.println("拆分数组为字符串时，遇见不可分析的类型："+class_name);

				break;

			}
		}

		rs=rs.substring(0, rs.length()-1);

		

		rs = rs + "]";
		return rs;

	}
	
	
	public static String convertFieldToArrayString(java.util.ArrayList<? extends Object> list, String get_field_method,Class<? extends Object> clazz){
		Object[] array=(Object[])com.cqqyd2014.util.ArrayListTools.convertFieldsToArray(list, get_field_method, clazz);
		return toString(array);
 	}

}
