package com.cqqyd2014.common.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.opensymphony.xwork2.ActionSupport;

public abstract class DownloadFromServerAbstractAction  extends ActionSupport{
	/**
	 * 
	 */
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