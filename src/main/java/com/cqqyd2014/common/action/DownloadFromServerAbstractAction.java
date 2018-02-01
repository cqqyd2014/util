package com.cqqyd2014.common.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class DownloadFromServerAbstractAction  extends ActionSupport{
	/**
	 * 
	 */
	public String dept_id;
	public String ip_addr;
	public String user_id;
	public String user_name;
	public String user_login;
	public Map<String,Object> session_http;
	
	
	public Map<String, Object> getSession_http() {
		return session_http;
	}
	public void setSession_http(Map<String, Object> session_http) {
		this.session_http = session_http;
	}
	public void init(){
		session_http= ActionContext.getContext().getSession();

		dept_id=(String)session_http.get("dept_id");
		user_login = (String) session_http.get("user_login");
		user_name = (String) session_http.get("user_name");
		user_id = (String) session_http.get("user_id");
		
		HttpServletRequest
		 request = ServletActionContext.getRequest();
		ip_addr=com.cqqyd2014.util.IPUtil.getIpAddr(request);
	}
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	private static final long serialVersionUID = 5002348476458830762L;
	
	public abstract String setDownloadFileName();

	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode(setDownloadFileName(), "UTF-8") ;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	InputStream inputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}



	public abstract byte[] setByteDate();


	String file_name;
	String content_type;
	public abstract String setContentType();

	     public String getContent_type() {
		return setContentType();
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

		/**  
	      * 获取下载流
	      * 对应 annotation 注解里面的 "inputName", "inputStream"
	      * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
	      * @return InputStream  
	      */  
	     public InputStream getInputStream()  { 
          
	    	
        	 
        	 inputStream = new ByteArrayInputStream(setByteDate());
	             

	         return inputStream;   
	     }   

	 	public abstract String execute_download();
}