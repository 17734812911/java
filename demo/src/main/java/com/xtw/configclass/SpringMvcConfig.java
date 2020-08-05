package com.xtw.configclass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xtw.handlerinterceptor.LoginHandlerInterceptor;

/**
 * 	实现WebMvcConfigurer接口来配置和扩展SpringMVC中的功能。这个类相当于之前SpringMVC的配置文件
 * @author Mr.Chen
 * 2020年7月28日
 */

//@EnableWebMvc  全面接管SpringMVC的自动配置，所有的自动配置都会失效。
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer{
	//MyEclipse中用Ctrl + / 来调出所有可以重写的方法
	
	//所有的webMvcConfigurer组件组件会一起起作用
	@Bean  //将配置注册到容器中才能生效
	public WebMvcConfigurer webMvcConfigurer() {
		WebMvcConfigurer configurer = new WebMvcConfigurer() {
			
			//设置默认首页
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				//前一个参数是访问路径(相当于@RequestMapping中的参数)，后一个是要跳转的页面(相当于返回值)
				registry.addViewController("/").setViewName("/user/login");  //直接输入localhost:8088跳转到登录界面  
				//registry.addViewController("toForget").setViewName("/user/forget");
				//registry.addViewController("toRegister").setViewName("/user/reg");
			}
			
			//注册拦截器，使之生效
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				
				 List<String> exclude = new ArrayList<>();  //不拦截的请求列表
				 //静态资源
				 exclude.add("/style/**");
				 exclude.add("/layui/**");
				 exclude.add("/modules/**");
				 exclude.add("/lib/**");
				 exclude.add("/json/**");
				 exclude.add("/config.js");
				 exclude.add("/favicon.ico");
				 exclude.add("/error");
				 //请求
				 exclude.add("/");
				 exclude.add("/toForget");
				 exclude.add("/toRegister");
				 exclude.add("/toLogin");
				 exclude.add("/login");
				 exclude.add("/imageGenerate");  //验证码
				 
				 
				 
				//登录拦截器
				//  /**是拦截任意多层路径下的任意请求
				//  excludePathPatterns()是不拦截哪些请求
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
			}
			
		};
		return configurer;
	}
	
}
