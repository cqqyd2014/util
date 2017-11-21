package com.cqqyd2014.util;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.cqqyd2014.util.exception.HttpExcpetion;





public class HttpRequest {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 * @throws HttpExcpetion 
	 * @throws SuccessMessageException 
	 */
	public static String sendGet(String url, String param) throws HttpExcpetion  {
		String result = "";
		URLConnection connection;
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			connection= realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpExcpetion("",url,"推送信息错误"+e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpExcpetion("",url,"推送信息错误"+e.toString());
		} 
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}



	
	/**
	 * @param args
	 * @throws HttpExcpetion 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws UnsupportedEncodingException
	 *             post中多个参数，以HashMap方式保存
	 
	public static String sendPost(String urlStr, java.util.HashMap<String, String> pars,java.util.HashMap<String, String> heads) throws HttpExcpetion
			 {
		// TODO Auto-generated method stub
		// String url="http://localhost/newspaper/test/1.php";
		String rs = null;
		// POST的URL
		HttpPost httppost = new HttpPost(urlStr);
		// 建立HttpPost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 建立一个NameValuePair数组，用于存储欲传送的参数
		Iterator it = pars.keySet().iterator();  
        while(it.hasNext()) {  
            String key = (String)it.next();  
            params.add(new BasicNameValuePair(key, pars.get(key)));
            
           
        }  
        try{
		// 添加参数
        	UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
        	
		httppost.setEntity(entity);
		//设置head参数
		
				Set set = heads.keySet(); 
			    for(Iterator itr=set.iterator();itr.hasNext();){ 
			      String key =(String) itr.next(); 
			      String value = (String)heads.get(key); 
			      //response.setHeader(key,value);
			      httppost.setHeader(key, value);
			      
			     } 
			    DefaultHttpClient httpClient = new DefaultHttpClient();  
		// 设置编码
			    HttpResponse response = httpClient.execute(httppost);  
		//HttpResponse response = new DefaultHttpClient().execute(httppost);
		// 发送Post,并返回一个HttpResponse对象
		
		
	    
	    
		//Header header = response.getFirstHeader("Content-Length");
		// String Length=header.getValue();
		// 上面两行可以得到指定的Header
		//System.out.println(response.getStatusLine().getStatusCode());
	    int sc=response.getStatusLine().getStatusCode();
		if (sc == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(response.getEntity());
			// 得到返回的字符串
			rs = result;
			//System.out.println(rs);
			// 打印输出
			// 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
		}
		else{
			System.out.println("http协议返回错误，错误代码："+sc+",地址："+urlStr);
			HttpExcpetion he=new HttpExcpetion();
			he.setCode(String.valueOf(sc));
			he.setUrl(urlStr);
			he.setMessage("http协议返回错误");
			throw he;
		}
        }
        catch(HttpExcpetion e){
        	throw e;
        }
        
        catch( UnsupportedEncodingException e){
        	HttpExcpetion he=new HttpExcpetion();
        	he.setUrl(urlStr);
    		he.setMessage("http参数编码为UTF8错误");
    		throw he;
        }
        catch( IOException e){
        	HttpExcpetion he=new HttpExcpetion();
        	he.setUrl(urlStr);
    		he.setMessage("IO通信错误");
    		throw he;
        }
        
		return rs;
	}
	
	*/
	/**
	 * @param args
	 * @throws HttpExcpetion 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	 *  List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("参数队列头部", 调用参数));
	 */
	
	public static String sendPost(String serverURL,java.util.LinkedHashMap<String, String> map) throws HttpExcpetion {
		String rs="";
		//serverURL url地址 
		HttpPost httpPost = new HttpPost(serverURL);
		CloseableHttpResponse response=null;
		CloseableHttpClient httpclient=null;
		try{
			
			//设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                nvps.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
		
			if(nvps.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps,HTTP.UTF_8);  
                httpPost.setEntity(entity);  
            }  
		 
		
		httpclient = HttpClients.createDefault(); 
		 response = httpclient.execute(httpPost);
		 
		
	    int sc=response.getStatusLine().getStatusCode();
		if (sc == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(response.getEntity());
			// 得到返回的字符串
			rs = result;
			//System.out.println(rs);
			// 打印输出
			// 如果是下载文件,可以用response.getEntity().getContent()返回InputStream
		}
		else{
			System.out.println("http协议返回错误，错误代码："+sc+",地址："+serverURL);
		}
        }
        catch(Exception e){
        	//System.out.println("推送信息错误"+e.toString());
        	throw new HttpExcpetion("",serverURL,"推送信息错误"+e.toString());
        }
		finally {
			if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpclient != null){
                try {
                	httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	    }
		return rs;
	}
}