package com.ct.springsecurity.config;

import com.ct.springsecurity.myexception.CustomException;
import com.ct.springsecurity.myexception.CustomExceptionType;
import com.ct.springsecurity.myexception.ResponseFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  SimpleUrlAuthenticationFailureHandler 是 AuthenticationFailureHandler接口的实现类，它默认实现了登录失败的跳转逻辑
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Value("${spring.security.logintype}")
    private String loginType;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "用户名或密码错误！";
        if(exception instanceof SessionAuthenticationException){
            // 取出异常信息
            errorMsg = exception.getMessage();
        }

        if(loginType.equalsIgnoreCase("JSON")){
            response.setContentType("application/json;charset=UTF-8");

            // 用ObjectMapper类的writeValueAsString()方法将返回的异常信息字符串转换为JSON对象
            response.getWriter().write(objectMapper.writeValueAsString(ResponseFormat.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,errorMsg))));
        }else{
            response.setContentType("text/html;charset=UTF-8");
            // 跳转到登录页面
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
