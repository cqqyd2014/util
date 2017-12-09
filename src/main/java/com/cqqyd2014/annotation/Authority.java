package com.cqqyd2014.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
/**
 * 定义一个注解，权限配置
 * 
 * @author wangli
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
 /** 模块 */
 String module();
 
 /** 权限值 */
 String privilege();
 
 /** 错误返回地址 */
 String error_url();
}