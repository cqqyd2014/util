package com.cqqyd2014.common.print.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;

public abstract class PdfPrintAbstractAction extends ActionSupport implements ServletResponseAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServletContext servletContext;
	public HttpServletResponse response;
	InputStream inputStream;
	
	
	public abstract String setDownloadFileName();
	
	public String getFile_name() throws UnsupportedEncodingException {
		return URLEncoder.encode(setDownloadFileName(),
				"UTF-8") + ".pdf";
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	String file_name;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public abstract String setJasperPath();
	public abstract String setJasperFileName();
	public abstract String setImagesPath();
	
	public InputStream getInputStream() {

		

		try {

			String reportDestination = null;

			reportDestination = servletContext.getRealPath(setJasperPath()+setJasperFileName());

			// 获得jasper报表文件的输入流
			InputStream inputStreamJasper = new FileInputStream(reportDestination);

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// parameters.put("param_deliverbill", bill);
			parameters.put("SUBREPORT_DIR", servletContext.getRealPath(setJasperPath()));

			parameters.put("IMAGES_DIR",setImagesPath());
			
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(initializeBeanArray());
			byte[] bytes=null;
			try {
			bytes=JasperRunManager.runReportToPdf(inputStreamJasper, parameters, dataSource);
			
			}
			catch(JRFontNotFoundException e) {
				System.out.println("Jasper模板找不到字体错误"+e.toString());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}

			inputStream = new ByteArrayInputStream(bytes);

		}

		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			System.out.println("不能读取Jasper模板的配置文件");
		}

		
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	public abstract String execute_pdf_print();



	public abstract java.util.ArrayList<? extends Object> initializeBeanArray();

}