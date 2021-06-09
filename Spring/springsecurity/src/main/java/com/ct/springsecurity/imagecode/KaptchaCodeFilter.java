package com.ct.springsecurity.imagecode;

import com.ct.springsecurity.config.MyAuthenticationFailureHandler;
import com.ct.springsecurity.utils.MyDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Component
public class KaptchaCodeFilter extends OncePerRequestFilter {
    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 过滤登录请求。判断当前请求是不是登录请求，并且是不是"POST"请求
        if(StringUtils.equals("/login",request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            try{
                // 验证用户输入的验证码是否正确
                validate(new ServletWebRequest(request));
            }catch (AuthenticationException exception){
                // 如果校验失败
                myAuthenticationFailureHandler.onAuthenticationFailure(request,response,exception);
                // 当验证码校验失败以后，后面的过滤器链中的过滤器就不再执行了
                return;
            }
        }
        // 如果验证都通过，让过滤器链继续执行
        filterChain.doFilter(request,response);
    }

    // 创建一个方法，用于校验用户输入的验证码是否正确
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 先获取Session
        HttpSession session = request.getRequest().getSession();

        // 1.获取用户输入的验证码并校验。"kaptchacode"是前端验证码输入框的id值
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"kaptchacode");
        if(StringUtils.isEmpty(codeInRequest)){
            throw new SessionAuthenticationException("验证码不能为空");
        }

        // 2.从服务器Session池中取出生成的验证码文本并校验
        KaptchaImageCode codeInSession = (KaptchaImageCode) session.getAttribute(MyDefinition.KAPTCHA_SESSION_KEY);
        if(Objects.isNull(codeInSession)){
            throw new SessionAuthenticationException("验证码不存在");
        }

        // 3.校验服务器session池中的验证码是否过期。isExpiration()方法是KaptchaImageVO中自定义的方法
        if(codeInSession.isExpiration()){
            // 如果过期，从session中移除验证码对象
            session.removeAttribute(MyDefinition.KAPTCHA_SESSION_KEY);
            throw new SessionAuthenticationException("验证码已过期");
        }

        // 4.进行验证码校验
        if(! StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new SessionAuthenticationException("验证码输入有误");
        }
    }
}
