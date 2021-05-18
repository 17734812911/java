package com.ct.springsecurity.config;

import com.ct.springsecurity.myexception.ResponseFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 继承SavedRequestAwareAuthenticationSuccessHandler而不是实现AuthenticationSuccessHandler接口，是因为这个类也是这个接口的实现类，并且有一些自己的实现
 * 可以少写点代码
 * SavedRequestAwareAuthenticationSuccessHandler可以记住上次请求的资源路径
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    // ObjectMapper是SpringBoot默认集成的Json处理框架 Jackson 的一个类
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.security.logintype}")      // spring将配置属性绑定到成员变量
    private String loginType;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if(loginType.equalsIgnoreCase("JSON")){     // 如果是JSON格式的响应
            // 将数据写回给客户端
            response.setContentType("application/json;charset=UTF-8");

            // 用ObjectMapper类的writeValueAsString()方法将返回的字符串转换为JSON对象
            response.getWriter().write(objectMapper.writeValueAsString(ResponseFormat.success()));
        }else{
            // 调用父类的方法，跳转到上一次请求的页面中
            super.onAuthenticationSuccess(request,response,authentication);
        }
    }
}
