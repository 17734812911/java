package com.xtw.handlerinterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 	登录拦截器，进行登录检查，没有登录状态时要先登录
 * @author Mr.Chen
 * 2020年7月30日
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
	//如果没有提示，按alt + / 添加以下方法
	
	//目标方法执行之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object loginUser = request.getSession().getAttribute("loginUser");
//		if(loginUser != null) {
//			System.out.println("当前登录用户为   " + loginUser.toString());
//		}
		if(loginUser == null) {
			switch(request.getRequestURI()) {
				case "/toForget":
					request.getRequestDispatcher("toForget").forward(request, response);
					break;
				case "/toRegister":
					request.getRequestDispatcher("toRegister").forward(request, response);
					break;
				default:
					request.getRequestDispatcher("/").forward(request, response);
					break;
			}
			return false;
		}else {
			return true;
		}
		
		
		
//		if(loginUser == null) {
//			//未登录,将请求转发到登录页面
//			//request.setAttribute("msg", "请先登录！");
//			System.out.println("请先登录"+request.getRequestURI());
//			request.getRequestDispatcher("/").forward(request, response);
//			return false;
//		}else{
//			//已登录，放行请求
//			return true;
//		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
