package com.cqqyd2014.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cqqyd2014.util.hashmap.HashMapToolsCompareResult;
import com.cqqyd2014.util.hashmap.ListItemStringN;

public class HashMapTools {
	
	public static java.util.ArrayList<ListItemStringN> conertToArrayListStringN(java.util.LinkedHashMap<String, java.math.BigDecimal> map){
		java.util.ArrayList<ListItemStringN> list=new java.util.ArrayList<>();
		for (Map.Entry<String, java.math.BigDecimal> entry : map.entrySet()) {  
			  
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
			ListItemStringN item=new ListItemStringN();
			item.setName(entry.getKey());
			item.setNum(entry.getValue());
			list.add(item);
		  
		} 
		return list;
	}
	
	
	//ABcompare的结果分析是否存在，A》=B的情况，比如A的订单，B是发货数据。第一，A的每一项都要大于等于B，另外不得有B比A多的。
	public static boolean check_if_contains(java.util.LinkedHashMap<String, java.math.BigDecimal> a_map,
			java.util.LinkedHashMap<String, java.math.BigDecimal> b_map) {
		HashMapToolsCompareResult rs=compare(a_map,b_map);
		if (rs.isFlag()) {
			return true;
		}
		for(Map.Entry<String, java.math.BigDecimal> entry:rs.getABCompare().entrySet()){
			//String  key=entry.getKey();
			java.math.BigDecimal value=entry.getValue();
			if (value.compareTo(new java.math.BigDecimal(0))==-1) {
				return false;
			}
			
			
         }
		return true;
		
	}
	//将链表变为map,结构为<String ,java.match.BigDecimal>
	public static java.util.LinkedHashMap<String, java.math.BigDecimal> convertArrayListStringNToMap(java.util.ArrayList<? extends Object> array_list,String getIndexMethod,String getValueMethod) {
		java.util.LinkedHashMap<String, java.math.BigDecimal> map=new java.util.LinkedHashMap<>();
		try{
			for (int i=0;i<array_list.size();i++) {
			Object o=array_list.get(i);
			Class<? extends Object> clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(getIndexMethod);
		        String index = (String) m1.invoke(o); 
		    Method m2=clazz.getDeclaredMethod(getValueMethod);
		    java.math.BigDecimal value=(java.math.BigDecimal)m2.invoke(o);
		    map.put(index, value);
			
		}
		}
		catch (NoSuchMethodException ex) {
			System.out.println("出错在HashMapTools的convertArrayListStringNToMap，不能得到方法："+ex.toString());
			
			
		}
		catch (SecurityException ex) {
			System.out.println("出错在HashMapTools的convertArrayListStringNToMap，SecurityException："+ex.toString());
		}
		catch (IllegalAccessException ex) {
			System.out.println("出错在HashMapTools的convertArrayListStringNToMap，IllegalAccessException："+ex.toString());
		}
		catch (IllegalArgumentException ex) {
			System.out.println("出错在HashMapTools的convertArrayListStringNToMap，IllegalArgumentException："+ex.toString());
		}
		catch (InvocationTargetException ex) {
			System.out.println("出错在HashMapTools的convertArrayListStringNToMap，InvocationTargetException："+ex.toString());
		} 
		
		return map;
	}
	
	
	/**
	 * @Title: 链表对象变为map
	 * @Description: 根据index和value，将链表提取为map，当index重复时，只取第一个
	 * 
	 * @return java.util.LinkedHashMap<String,String>
	 * @param array_list
	 *            传入的源数组对象
	 * @param getIndexMethod
	 *            用于得到Index字段的方法，反射机制使用，一般为"getA"
	 * @param getValueMethod 用于得到value字段的方法
	 */
	
	
	
	//根据数组中有多少个需要取得的对象统计数量
	public static java.util.LinkedHashMap<String, java.math.BigDecimal> convertArrayListToHashMapCount(java.util.ArrayList<? extends Object> array_list,String getIndexMethod)  {

		
		LinkedHashMap<String,java.math.BigDecimal> map=new LinkedHashMap<String,java.math.BigDecimal>();
		try{
		for (int i=0;i<array_list.size();i++){
			Object o=array_list.get(i);
			Class<? extends Object> clazz = o.getClass();
			 Method m1 = clazz.getDeclaredMethod(getIndexMethod);
		        String index = (String) m1.invoke(o); 
		        java.math.BigDecimal count=map.get(index);
		        if (count==null) {
		        	map.put(index, new java.math.BigDecimal(1));
		        }
		        else {
		        	map.put(index, count.add(new java.math.BigDecimal(1)));
		        }
		}
		}
		catch (NoSuchMethodException ex) {
			System.out.println("出错在HashMapTools的convertArrayListToHashMapCount，不能得到方法："+ex.toString());
			
			
		}
		catch (SecurityException ex) {
			System.out.println("出错在HashMapTools的convertArrayListToHashMapCount，SecurityException："+ex.toString());
		}
		catch (IllegalAccessException ex) {
			System.out.println("出错在HashMapTools的convertArrayListToHashMapCount，IllegalAccessException："+ex.toString());
		}
		catch (IllegalArgumentException ex) {
			System.out.println("出错在HashMapTools的convertArrayListToHashMapCount，IllegalArgumentException："+ex.toString());
		}
		catch (InvocationTargetException ex) {
			System.out.println("出错在HashMapTools的convertArrayListToHashMapCount，InvocationTargetException："+ex.toString());
		} 
		        
		
       
		return map;
	}
	
	
	
	/**
	 * @Title: 链表对象变为map
	 * @Description: 根据index和value，将链表提取为map，当index重复时，只取第一个
	 * 
	 * @return java.util.LinkedHashMap<String,String>
	 * @param array_list
	 *            传入的源数组对象
	 * @param getIndexMethod
	 *            用于得到Index字段的方法，反射机制使用，一般为"getA"
	 * @param getValueMethod 用于得到value字段的方法
	 */
	
	
	//把数组链表变为map
	public static java.util.LinkedHashMap<String,String> convertArrayListToHashMap(java.util.ArrayList<? extends Object> array_list,String getIndexMethod,String getValueMethod)   {
		
		
		LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();

try{
	for (int i=0;i<array_list.size();i++){
		Object o=array_list.get(i);
		Class<? extends Object> clazz = o.getClass();
		 Method m1 = clazz.getDeclaredMethod(getIndexMethod);
	        String index = (String) m1.invoke(o); 
	        Method m2 = clazz.getDeclaredMethod(getValueMethod);
	        String value = (String) m2.invoke(o); 
	        if (map.get(index)!=null){
	        	//throw new Exception("ArrayList中Index的值重复");
	        }
	        map.put(index, value);
	}
}
catch (NoSuchMethodException ex) {
	System.out.println("出错在HashMapTools的convertArrayListToHashMap，不能得到方法："+ex.toString());
	
	
}
catch (SecurityException ex) {
	System.out.println("出错在HashMapTools的convertArrayListToHashMap，SecurityException："+ex.toString());
}
catch (IllegalAccessException ex) {
	System.out.println("出错在HashMapTools的convertArrayListToHashMap，IllegalAccessException："+ex.toString());
}
catch (IllegalArgumentException ex) {
	System.out.println("出错在HashMapTools的convertArrayListToHashMap，IllegalArgumentException："+ex.toString());
}
catch (InvocationTargetException ex) {
	System.out.println("出错在HashMapTools的convertArrayListToHashMap，InvocationTargetException："+ex.toString());
}
			
		
		
		
       
		return map;
	}
	
	public static HashMapToolsCompareResult compare(HashMap<String,java.math.BigDecimal> a,HashMap<String,java.math.BigDecimal> b){
		HashMapToolsCompareResult rs=new HashMapToolsCompareResult();
		HashMap<String,java.math.BigDecimal> abCompare =new HashMap<String,java.math.BigDecimal>();
		rs.setABCompare(abCompare);
		rs.setFlag(true);
		//先对比b中存在，而a中没有的元素，这些都记为结果的负数
		for(Map.Entry<String, java.math.BigDecimal> entry1:b.entrySet()){
			String  b_id=entry1.getKey();
			if (a.get(b_id)==null){
				
				//这些都是b中有而a中没有的元素
				abCompare.put(b_id, entry1.getValue().multiply(new java.math.BigDecimal("-1")));
				rs.setFlag(false);
			}
			
			
         }
		//从a开始遍历，是否都相同，如果a大于b记为正数，如果b大于a记为负数
		for(Map.Entry<String, java.math.BigDecimal> entry1:a.entrySet()){
			String a_id=entry1.getKey();
			java.math.BigDecimal b_num=b.get(a_id);
			java.math.BigDecimal a_num=a.get(a_id);
			if (b_num==null){
				//a中有，而b中没有
				abCompare.put(a_id, a_num);
				rs.setFlag(false);
			}
			else {
				if (b_num.compareTo(a_num)==-1){
					 //a比b更大
					abCompare.put(a_id,a_num.subtract(b_num));
					rs.setFlag(false);
				}
					
				
				if (b_num.compareTo(a_num)==1){
					
					//a比b小
					abCompare.put(a_id,a_num.subtract(b_num));
					rs.setFlag(false);
				}
				
				
			}
			
			
			
			
		
		
		
	}
		return rs;

}
}