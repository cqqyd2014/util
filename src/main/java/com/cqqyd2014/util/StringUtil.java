package com.cqqyd2014.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.cqqyd2014.util.message.StringUtilIndexesOfChar;

import sun.misc.BASE64Encoder;



public class StringUtil {
	
	//String变为InputStream对象
	public static InputStream toInputStream(String str) throws UnsupportedEncodingException{
		return new   ByteArrayInputStream(str.getBytes("UTF-8"));
	}
	
	
	public java.util.ArrayList<StringUtilIndexesOfChar> getIndexesOfChar(String source,String flag){
		java.util.ArrayList<StringUtilIndexesOfChar> list=new java.util.ArrayList<>();

		for (int i=0;i<source.length()-flag.length()+1;i++){
			String sub_string=source.substring(i, flag.length()+i);
			//System.out.println(sub_string);
				if (sub_string.equals(flag)){
					list.add(new StringUtilIndexesOfChar(flag,i));
					//System.out.println(i);
					
				}
			}
		
		return list;
	}
	
	public StringUtilIndexesOfChar whoIsFirst(String source,String[] flags){
		if (source.length()==0||flags.length==0){
			return null;
		}
		StringUtilIndexesOfChar msg=new StringUtilIndexesOfChar();
		java.util.ArrayList<java.util.ArrayList<StringUtilIndexesOfChar>> lists=new java.util.ArrayList<>();
		//得到每个flag的indexes列表
		for (int i=0;i<flags.length;i++){
			java.util.ArrayList<StringUtilIndexesOfChar> list=getIndexesOfChar(source,flags[i]);
			if (list.size()>0){
				lists.add(list);
			}
			
		}
		if (lists.size()==0){
			//一个都没找到
			return null;
		}
		else{
			//比较哪一个最先出现
			//取到第一个作为初始值
			java.util.ArrayList<StringUtilIndexesOfChar> first_list=lists.get(0);
			msg=first_list.get(0);
			
			for (int i=1;i<lists.size();i++){
				int value=lists.get(i).get(0).getIndex();
				if (value<msg.getIndex()){
					msg=lists.get(i).get(0);
					
				}
			}
		}
		
		
		return msg;
	}

	public static String getLogisticsMemo(String str){
		str=str.replaceAll("序号：", "（");
		str=str.replaceAll(";型号:","）");
		str=str.replaceAll(";数量：", "*");
		str=str.replaceAll("厨刀", "厨具");
		return str;
	}
	
	
	 public static String base64Encode(byte[] data){  
         
	        return Base64.encodeBase64String(data);  
	    }  
	 public static String base64Encode(String data){  
         
	        return Base64.encodeBase64String(data.getBytes());  
	    }  
	      
	    public static String base64Decode(String data){  
	    	String str="";  
	    	try{
	        str= new String(Base64.decodeBase64(data.getBytes()),"UTF-8");  
	          }catch(Exception e){
	        	  System.out.println(e.toString());
	          }
	          return str;
	    }  
	    public static String md5(String data) {
	    	return DigestUtils.md5Hex(data).toUpperCase();
	    }
	      
	      

	      
	    public static String sha256Hex(String data) {  
	          
	        return DigestUtils.sha256Hex(data);  
	    }  
	
	
	//用jdom4j的document生产字符串
	
		public static String jdom4jFormat(Document document) {
			StringWriter writer = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			// 设置换行
			format.setNewlines(true);
			// 生成缩进
			format.setIndent(true);
			// 使用2个空格进行缩进, 可以兼容文本编辑器
			format.setIndent("  ");
			// This will set whether the XML declaration (<?xml version="1.0"
			// encoding="UTF-8"?>) is included or not. It is common to suppress this
			// in protocols such as WML and SOAP.
			format.setSuppressDeclaration(false);
			format.setExpandEmptyElements(false);
			XMLWriter xmlwriter = new XMLWriter(writer, format);
			try {
				xmlwriter.write(document);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return writer.toString();
		}
	
	// 计算两个字符串的差异值
	public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
		if (s == null || t == null) {
			// 容错，抛出的这个异常是表明在传参的时候，传递了一个不合法或不正确的参数。
			// 好像都这样用，illegal:非法。Argument:参数，证据。
			throw new IllegalArgumentException("Strings must not be null");
		}
		// 计算传入的两个字符串长度
		int n = s.length();
		int m = t.length();
		// 容错，直接返回结果。这个处理不错
		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		}
		// 这一步是根据字符串长短处理，处理后t为长字符串，s为短字符串，方便后面处理
		if (n > m) {
			CharSequence tmp = s;
			s = t;
			t = tmp;
			n = m;
			m = t.length();
		}

		// 开辟一个字符数组，这个n是短字符串的长度
		int p[] = new int[n + 1];
		int d[] = new int[n + 1];
		// 用于交换p和d的数组
		int _d[];

		int i;
		int j;
		char t_j;
		int cost;
		// 赋初值
		for (i = 0; i <= n; i++) {
			p[i] = i;
		}

		for (j = 1; j <= m; j++) {
			// t是字符串长的那个字符
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++) {
				// 计算两个字符是否一样，一样返回0。
				cost = s.charAt(i - 1) == t_j ? 0 : 1;
				// 可以将d的字符数组全部赋值。
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
			}

			// 交换p和d
			_d = p;
			p = d;
			d = _d;
		}

		// 最后的一个值即为差异值
		return p[n];
	}

	// 将数字精确到小数点两位，并转为文本
	public static String getDToS(BigDecimal scale) {
		//double d = (Math.floor(scale.doubleValue() * 100)) / 100.0;
		DecimalFormat fnum = new DecimalFormat("##0.00");
		return fnum.format(scale);
	}

	

	
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
	//单个字符变为数组
	public static java.util.ArrayList<String> toArrayList(String str){
		java.util.ArrayList<String> strs=new java.util.ArrayList<String>();
		strs.add(str);
		return strs;
	}
	//数组变ArrayList
	public static java.util.ArrayList<String> ArrayToArrayList(String[] str){
		java.util.ArrayList<String> strs=new java.util.ArrayList<String>();
		for (int i=0;i<str.length;i++) {
			strs.add(str[i]);
		}
		return strs;
	}
	public static int indexOfArray(java.util.ArrayList<String> strs,String str) {
		String[] strs2=arrayListToArray(strs);
		
		for (int i=0;i<strs2.length;i++) {
			if (strs2[i].equals(str)) {
				return i;
			}
		}
				return -1;
	}
	public static String[] arrayListToArray(java.util.ArrayList<String> strs) {
		String[] str=new String[strs.size()];
		for (int i=0;i<strs.size();i++) {
			str[i]=strs.get(i);
		}
		
		return str;
	}
	//将字符串链表变为SQL语句的in字符串
	public static String arrayListToSQLInString(ArrayList<String> rs) {
		String str="(";
		for (int i=0;i<rs.size()-1;i++){
			str=str+"\'"+rs.get(i)+"\',";
		}
		str=str+"\'"+rs.get(rs.size()-1)+"\'";
		return str+")";
		
	}
	//将数值链表变为SQL语句的in字符串
		public static String arrayListToSQLInInt(ArrayList<String> rs) {
			String str="(";
			for (int i=0;i<rs.size()-1;i++){
				str=str+rs.get(i)+",";
			}
			str=str+rs.get(rs.size()-1);
			return str+")";
		}
	

	//字符串数组变为逗号分隔符
	public static String arrayListToDotString(ArrayList<String> rs) {
		
		String str="";
		for (int i=0;i<rs.size()-1;i++){
			str=str+rs.get(i)+",";
		}
		str=str+rs.get(rs.size()-1);
		return str;
	}
	
	public static String base64encode(String str) {
		byte[] b = Base64.encodeBase64(str.getBytes(), true); 
        String str1 = new String(b); 
        return str1;
	}
	public static String base64decode(String str) {
		byte[] b1 = Base64.decodeBase64(str); 
        return (new String(b1)); 
	}
	
	//来自顺丰的加密
	public static String md5EncryptAndBase64(String str) {
		return encodeBase64(md5Encrypt(str));
	}

	private static byte[] md5Encrypt(String encryptStr) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(encryptStr.getBytes("utf8"));
			return md5.digest();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String encodeBase64(byte[] b) {
		sun.misc.BASE64Encoder base64Encode = new BASE64Encoder();
		String str = base64Encode.encode(b);
		return str;
	}
}

