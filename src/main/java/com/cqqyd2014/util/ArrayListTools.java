package com.cqqyd2014.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;



public class ArrayListTools {
	
	
	
	
	

	
	
	/**
	 * @Title: a数组是否包含b数组
	 * @Description: “[a,b,c,d]”为输入A，[a,c]为输入B，结果是boolean
	 * 
	 * @return boolean
	 * @param array_a
	 *            传入的源数组a
	 * @param array_b
	 *            传入的源数组b
	 * 
	 */
	public boolean if_array_a_contains_array_b(java.util.ArrayList<String> array_a,java.util.ArrayList<String> array_b) {
		com.cqqyd2014.util.array_list.ArrayListToolsCompareResult rs=compare(array_a,array_b);
		if (rs.isFlag()) {
			return true;
		}
		else {
			if (rs.getB_more_a().size()==0) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 * @Title: 数组比较，类似map比较
	 * @Description: “[a,b,c,d]”为输入A，[a,c]为输入B，结果是一个特殊结构
	 * 
	 * @return ArrayListToolsCompareResult
	 * @param array_a
	 *            传入的源数组a
	 * @param array_b
	 *            传入的源数组b
	 * 
	 */
	
	public static com.cqqyd2014.util.array_list.ArrayListToolsCompareResult compare(java.util.ArrayList<String> array_a,java.util.ArrayList<String> array_b){
		com.cqqyd2014.util.array_list.ArrayListToolsCompareResult rs=new com.cqqyd2014.util.array_list.ArrayListToolsCompareResult();
		rs.setFlag(true);
		
		
		//遍历a，寻找a中有，b中没有的，放入a_more_b
				
		java.util.ArrayList<String> temp_a=array_a;
		temp_a.removeAll(array_b);
		if (temp_a.size()>0) {
			rs.setFlag(false);
		}
		
		rs.setA_more_b(temp_a);
		//遍历b,寻找b中有，a中没有的，放入 b_more_a
		java.util.ArrayList<String> temp_b=array_b;
		temp_b.removeAll(array_a);
		if (temp_b.size()>0) {
			rs.setFlag(false);
		}
		rs.setB_more_a(temp_b);
		
		
		return rs;
	}
	
	
	/**
	 * @Title: 数组里面寻找某个值
	 * @Description: array_list中寻找是否有某个值，如果有，返回序号id，如果没有，返回null
	 * 
	 * @return Integer
	 * @param array_list 给定的array_list
	 * @param value 给定的值
	 * 
	 */
	public static java.lang.Integer getIndexOfArrayStirng(java.util.ArrayList<String> array_list,String value){
		
		for (int i=0;i<array_list.size();i++) {
			String value2=array_list.get(i);
			if (value2.equals(value)) {
				return java.lang.Integer.valueOf(i);
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	/**
	 * @Title: 数组变为数组链表
	 * @Description: “[a,b,c,d]”变为ArrayList
	 * 
	 * @return java.util.Array<String>
	 * @param str
	 *            传入的源数组对象
	 * 
	 */

	

	public static java.util.ArrayList<String> convertArrayToArrayList(String[] str) {
		java.util.ArrayList<String> str_array=new java.util.ArrayList<String>();
		for (int i=0;i<str.length;i++) {
			str_array.add(str[i]);
		}

		return str_array;
	}
	
	
	

	/**
	 * @Title: 数组对象提取属性为数组
	 * @Description: 将数组对象的某一个属性列提取为数组，比如“[a,b,c,d]”
	 * 
	 * @return String
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"
	 */

	// 返回制定文本列的数组形式，比如“[a,b,c,d]”

	public static String convertFieldsToArray(Object[] list, String get_field_method) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String rs = "[";
		for (int i = 0; i < list.length; i++) {
			Object o = list[i];
			Class<? extends Object> clazz = o.getClass();
			Method m1 = clazz.getDeclaredMethod(get_field_method);
			rs = rs + (String) m1.invoke(o) + ",";
		}
		rs = rs.substring(0, rs.length() - 1);

		rs = rs + "]";

		return rs;
	}
	
	
	
	/**
	 * @Title: 数组对象根据某个属性分类计数
	 * @Description: 讲某个属性为相同的对象作为某个key的数量
	 * 
	 * @return java.util.LinkedHashMap<String, java.math.BigDecimal>
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"
	 */
	
	

	// 返回主键的计数
	public static java.util.LinkedHashMap<String, java.math.BigDecimal> getKeyCount(java.util.ArrayList<? extends Object> list,
			String get_key_method) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		java.util.LinkedHashMap<String, java.util.ArrayList<Object>> map_temp = convertArrayListToMapArrayList(list, get_key_method);
		java.util.LinkedHashMap<String, java.math.BigDecimal> map = new java.util.LinkedHashMap<>();
		for (Map.Entry<String, java.util.ArrayList<Object>> entry : map_temp.entrySet()) {

			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());
			map.put(entry.getKey(), new java.math.BigDecimal(entry.getValue().size()));

		}
		return map;
	}
	/**
	 * @Title: 数组对象根据字段过滤(布尔)
	 * * @Description: 提取该数组对象中，字段等于某一值
	 * 
	 * @return java.util.ArrayList<? extends Object>
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"
	 * @param value
	 * 				具体的值是多少
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static java.util.ArrayList<? extends Object> getArrayListSearchBooleanField
	(java.util.ArrayList<? extends Object> list, String get_field_method,
			boolean value)  {
		java.util.ArrayList<? extends Object> os = list;
		try {
			Boolean value_o = new Boolean(value);
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class<? extends Object> clazz = o.getClass();
			Method m1 = clazz.getDeclaredMethod(get_field_method);
			boolean field_value = (boolean) m1.invoke(o);
			Boolean field_o = new Boolean(field_value);
			if (field_o.compareTo(value_o) != 0) {
				os.remove(o);
			}
		}
		}
		catch (NoSuchMethodException ex) {
			System.out.println("出错在ArrayListTools的getArrayListSearchBooleanField，不能得到方法："+ex.toString());
		}
		catch (SecurityException ex) {
			System.out.println("出错在ArrayListTools的getArrayListSearchBooleanField，SecurityException"+ex.toString());
		}
		catch (IllegalAccessException ex) {
			System.out.println("出错在ArrayListTools的getArrayListSearchBooleanField，IllegalAccessException"+ex.toString());
		}
		catch (IllegalArgumentException ex) {
			System.out.println("出错在ArrayListTools的getArrayListSearchBooleanField，IllegalArgumentException"+ex.toString());
		}
		catch (InvocationTargetException  ex) {
			System.out.println("出错在ArrayListTools的getArrayListSearchBooleanField，InvocationTargetException"+ex.toString());
		}
		
		return os;
	}

	
	/**
	 * @Title: 数组对象根据字段过滤(字符串)
	 * * @Description: 提取该数组对象中，字段等于某一值
	 * 
	 * @return java.util.ArrayList<? extends Object>
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"
	 * @param value
	 * 				具体的值是多少
	 */
	public static java.util.ArrayList<? extends Object> searchStringField(ArrayList<? extends Object> list, String get_field_method, String value)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		java.util.ArrayList<? extends Object> os =list;
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class<? extends Object> clazz = o.getClass();
			Method m1 = clazz.getDeclaredMethod(get_field_method);
			String field_value = (String) m1.invoke(o);
			if (!field_value.equals(value)) {
				os.remove(o);
			}
		}
		return os;
	}
	
	
	
	
	/**
	 * @Title: 数组对象根据字段求和
	 * * @Description: 某一字段必须为java.math.BigDecimal，再求和
	 * 
	 * @return java.math.BigDecimal
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"

	 */

	// 返回制定数值的累计数，数值列为java.math.BigDecimal

	public static java.math.BigDecimal sumFields(java.util.ArrayList<? extends Object> list, String get_field_method)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		java.math.BigDecimal rs = new java.math.BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class<? extends Object> clazz = o.getClass();
			Method m1 = clazz.getDeclaredMethod(get_field_method);
			rs = rs.add((java.math.BigDecimal) m1.invoke(o));
		}

		return rs;
	}

	
	
	
	
	/**
	 * @Title: 数组对象根据某个属性分组
	 * @Description: 讲某个属性为相同的对象作为某个key下面的数组
	 * 
	 * @return String
	 * @param list
	 *            传入的源数组对象
	 * @param get_field_method
	 *            用于得到字段的方法，反射机制使用，一般为"getA"
	 */
	
	// 将商品ArrayList按照制定列分组，成为map

	public static java.util.LinkedHashMap<String, java.util.ArrayList<Object>> convertArrayListToMapArrayList(java.util.ArrayList<? extends Object> list,
			String get_key_method) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		java.util.LinkedHashMap<String, java.util.ArrayList<Object>> map = new java.util.LinkedHashMap<>();

		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class<? extends Object> clazz = o.getClass();
			Method m1 = clazz.getDeclaredMethod(get_key_method);
			String key = (String) m1.invoke(o);
			java.util.ArrayList<Object> sub_list = map.get(key);
			if (sub_list == null) {
				sub_list = new java.util.ArrayList<>();
			}

			sub_list.add(o);
			map.put(key, sub_list);

		}
		return map;
	}

}
