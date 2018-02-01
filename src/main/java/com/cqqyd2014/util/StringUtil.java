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





@SuppressWarnings("restriction")
public final class StringUtil {
	
	
	
	
	
	//数组变ArrayList
	public static java.util.ArrayList<String> ArrayToArrayList(String[] str){
		java.util.ArrayList<String> strs=new java.util.ArrayList<String>();
		for (int i=0;i<str.length;i++) {
			strs.add(str[i]);
		}
		return strs;
	}
	
	//单个字符变为数组
	public static java.util.ArrayList<String> toArrayList(String str){
		java.util.ArrayList<String> strs=new java.util.ArrayList<String>();
		strs.add(str);
		return strs;
	}
	
	
	//根据hiernate的实体类属性得到get方法
	public static String getMethod(String field){
		if (field==null|| "".equals(field.trim())){
			System.out.println("属性为空，不能得到get方法名称");
			return null;
		}
		String first=field.substring(0,1);
		return "get"+first.toUpperCase()+field.substring(1,field.length());
	}
	
	
	public static final char UNDERLINE = '_';

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * 
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
	
	
	//清理字符串，银行用得多
	public static String cleanToString(String str){
		if (str==null){
			return "";
		}
		str=str.trim();
		if (str.equals("NULL")){
			return "";
		}
		if (str.equals("null")){
			return "";
		}
		return str;
	}
	//清理字符串为数字
	public static java.math.BigDecimal cleanToDec(String str){
		str=cleanToString(str);
		if (str.equals("")){
			return new java.math.BigDecimal(0);
		}
		else{
			return new java.math.BigDecimal(str);
		}
	}
	//清理字符串为日期型
	public static java.util.Date cleanToDate(String str){
		//System.out.println(str);
		if (str.equals(" 00:00:00")){
			return com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1");
		}
		str=cleanToString(str);
		if (str.equals("")){
			return com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1");
		}
		else{
			return com.cqqyd2014.util.DateUtil.FullStringToJDate(str);
		}
	}
	
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

	
	public static String base64encode(String str) {
		byte[] b = Base64.encodeBase64(str.getBytes(), true); 
        String str1 = new String(b); 
        return str1;
	}
	public static String base64decode(String str) {
		byte[] b1 = Base64.decodeBase64(str); 
        return (new String(b1)); 
	}
	/**
	 * @Title: 以数组方式存储的字符串变为字符串数组
	 * @Description: “[a,b,c,d]”变为String[]
	 * 
	 * @return String[]
	 * @param str
	 *            传入的源数组对象
	 * 
	 */

	

	public static String[] convertToArray(String str) {
		//是否以“[]”开头结尾
		if (str.length()<2) {
			return null;
		}
		
		String head=str.substring(0, 1);
		String tail=str.substring(str.length()-1, str.length());
		if (head.equals("[")&&tail.equals("]")) {
			if (str.length()==2) {
				return null;
			}
			else {
				return str.substring(1,str.length()-1).split(",");
			}
		}
		else {
			return null;
		}
		
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

