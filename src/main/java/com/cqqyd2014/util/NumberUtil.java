package com.cqqyd2014.util;

import java.math.BigDecimal;

public class NumberUtil {
	//是否为整数
			public static boolean isNumeric(String str){  
				  for (int i = str.length();--i>=0;){    
				   if (!Character.isDigit(str.charAt(i))){  
				    return false;  
				   }  
				  }  
				  return true;  
				}  
			
			public static double round(java.math.BigDecimal num ,int scale) {  
		          
		        BigDecimal result = num.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
		        return result.doubleValue();  
		    }  
			public static double round(java.math.BigDecimal num ) {  
				 return round(num ,2);
			}
			public static String s_round(java.math.BigDecimal num ){
				return String.valueOf(round(num ,2));
			}
			
			//返回数字补零的字符串
			
			public static String numToStr0(long l,int length){
				
				
				double d=java.lang.Math.log(l)/java.lang.Math.log(10);
				int num_length=(int)Math.floor(d);
				String num_str=String.valueOf(l);
				
				for (int i=0;i<length-num_length-1;i++){
					num_str="0"+num_str;
				}
				
				return num_str;
			}
}
