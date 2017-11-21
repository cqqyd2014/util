package com.cqqyd2014.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.cqqyd2014.util.hashmap.ListItemStringN;

public class ArrayListTools {
	
	

	
	


	
	//返回制定文本列的数组形式，比如“[a,b,c,d]”
	public static String convertFieldsToArray(Object[] list,String get_field_method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String rs="[";
		for (int i=0;i<list.length;i++) {
			Object o=list[i];
			Class clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(get_field_method);
		        rs =rs+ (String) m1.invoke(o)+","; 
		}
		rs=rs.substring(0, rs.length()-1);
		
		rs=rs+"]";
		
		return rs;
	}
	
	//返回主键的计数
	public static java.util.LinkedHashMap<String, java.math.BigDecimal> getKeyCount (Object[] list,String get_key_method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		java.util.LinkedHashMap<String, java.util.ArrayList<Object>> map_temp=convertArrayToMap(list,get_key_method);
		java.util.LinkedHashMap<String, java.math.BigDecimal> map=new java.util.LinkedHashMap<>();
		for (Map.Entry<String, java.util.ArrayList<Object>> entry : map_temp.entrySet()) {  
			  
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		    map.put(entry.getKey(), new java.math.BigDecimal(entry.getValue().size()));
		  
		}  
		return map;
	}
	//ArrayList检索布尔字段等于值
	
	public static Object[] getArraySearchBooleanField(Object[] list,String get_field_method,boolean value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		java.util.ArrayList<Object> os=new java.util.ArrayList<>();
		Boolean value_o=new Boolean(value);
		for (int i=0;i<list.length;i++) {
			Object o=list[i];
			Class clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(get_field_method);
			 boolean field_value =(boolean) m1.invoke(o); 
			 Boolean field_o=new Boolean(field_value);
		     if (field_o.compareTo(value_o)==0) {
		    	 os.add(o);
		     }
		}
		return os.toArray();
	}
	public static java.util.ArrayList<Object> getArrayListSearchBooleanField(Object[] list,String get_field_method,boolean value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		java.util.ArrayList<Object> os=new java.util.ArrayList<>();
		Boolean value_o=new Boolean(value);
		for (int i=0;i<list.length;i++) {
			Object o=list[i];
			Class clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(get_field_method);
			 boolean field_value =(boolean) m1.invoke(o); 
			 Boolean field_o=new Boolean(field_value);
		     if (field_o.compareTo(value_o)==0) {
		    	 os.add(o);
		     }
		}
		return os;
	}
	
	//ArrayList检索文本字段等于值
	
	public static Object[] searchStringField(Object[] list,String get_field_method,String value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		java.util.ArrayList<Object> os=new java.util.ArrayList<>();
		for (int i=0;i<list.length;i++) {
			Object o=list[i];
			Class clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(get_field_method);
		     String field_value =(String) m1.invoke(o); 
		     if (field_value.equals(value)) {
		    	 os.add(o);
		     }
		}
		return os.toArray();
	}
	
	//返回制定数值的累计数，数值列为java.math.BigDecimal
	
	public static java.math.BigDecimal convertFieldsSumBigDecimal(Object[] list,String get_field_method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		java.math.BigDecimal rs=new java.math.BigDecimal(0);
		for (int i=0;i<list.length;i++) {
			Object o=list[i];
			Class clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(get_field_method);
		        rs =rs.add( (java.math.BigDecimal) m1.invoke(o)); 
		}
		
		
		return rs;
	}
	
	// 将商品ArrayList按照制定列分组，成为map

		public static java.util.LinkedHashMap<String, java.util.ArrayList<Object>> convertArrayToMap(Object[] list,String get_key_method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			java.util.LinkedHashMap<String, java.util.ArrayList<Object>> map = new java.util.LinkedHashMap<>();

			for (int i = 0; i < list.length; i++) {
				Object o=list[i];
				Class clazz = o.getClass();
				 Method m1 = clazz.getDeclaredMethod(get_key_method);
				 String key=(String) m1.invoke(o);
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
