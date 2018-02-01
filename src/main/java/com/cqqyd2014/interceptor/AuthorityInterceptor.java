package com.cqqyd2014.interceptor;

import java.lang.reflect.Method;



import org.apache.struts2.ServletActionContext;

import com.cqqyd2014.annotation.Authority;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends AbstractInterceptor {


	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName = invocation.getProxy().getMethod();
		@SuppressWarnings("rawtypes")
		Class clazz = invocation.getAction().getClass(); // 获取类对象
		@SuppressWarnings("unchecked")
		Method currentMethod = clazz.getMethod(methodName);
		
		// 检查Action类AnnotationTest是否含有@Authority注解
		// 有注解，必定有session中的user_id等，如果取不到，那么肯定没有登录
		if (currentMethod.isAnnotationPresent(Authority.class)) {
			// 取得权限验证的注解
						Authority authority = currentMethod.getAnnotation(Authority.class);
						// 取得当前请求的注解的action
						String module = authority.module();
						//System.out.println(module);
						// 取得当前请求需要的权限
						String privilege = authority.privilege();
//			取得错误返回地址
					String error_url=authority.error_url();
			// 从session里取得当前的用户
			String user_id = (String) ServletActionContext.getRequest().getSession().getAttribute("user_id");
			if (user_id == null||user_id .equals("")) {
				//System.out.println("fanhui1");
				
				return error_url;
			}
			// 取到权限

			@SuppressWarnings("unchecked")
			java.util.ArrayList<String> menu_array = (java.util.ArrayList<String>) ServletActionContext.getRequest()
					.getSession().getAttribute("menu_array");
			
			
			// 如果权限为"*"，则不需要任何权限,只需要登录就行
			if (privilege.equals("*")) {
				return invocation.invoke();
			}
			// 将权限变为数组
			@SuppressWarnings("unchecked")
			java.util.ArrayList<String> privilege_array = (java.util.ArrayList<String>)com.cqqyd2014.util.ArrayTools
					.convertArrayToArrayList(com.cqqyd2014.util.StringUtil.convertToArray(privilege));
			// 两个ArrayList取交集
			privilege_array.retainAll(menu_array);
			if (privilege_array.size() > 0) {
				/*
				String log="用户[" + user_id + "]在" + new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss").format(new Date())
						+ "调用了[" + clazz.getName() + "]类的[" + methodName + "]方法，所在模块[" + module + "]，拥有权限[" + privilege
						+ "]。";
				System.out.println(log);
				logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				logger.info(log);
				logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				*/
				//System.out.println("ok");
				return invocation.invoke();
			} else {
				System.out.println("fanhui2");
				
				return error_url;
			}

			/**
			 * 然后可以在此判断当前用户是否拥有对应的权限，如果没有可以跳到指定的无权限提示页面， 如果拥有则可以 继续往下执行。 if (拥有对应的权限) ｛
			 * return invocation.invoke(); } else { return "无权限"; }
			 */

		}
		return invocation.invoke();
	}

}
