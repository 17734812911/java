package com.ct.springsecurity.smscode.filters;

import com.ct.springsecurity.config.MyAuthenticationFailureHandler;
import com.ct.springsecurity.mapper.MyUserDetailsServiceMapper;
import com.ct.springsecurity.smscode.utils.SmsCode;
import com.ct.springsecurity.utils.MyDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
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

/**
 *  短信验证码校验的过滤器
 */
@Component
public class SmsCodeValidateFilter extends OncePerRequestFilter {

    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 过滤登录请求。判断当前请求是不是登录请求，并且是不是"POST"请求
        if(StringUtils.equals("/smslogin",request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
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

    // 验证规则
    // 创建一个方法，用于校验用户输入的验证码是否正确
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 先获取Session
        HttpSession session = request.getRequest().getSession();
        SmsCode codeInSession = (SmsCode) session.getAttribute(MyDefinition.SMS_SESSION_KEY);
        // 取出前端传过来的手机号、验证码等信息
        String mobileInRequest = request.getParameter("mobile");
        String smscodeInRequest = request.getParameter("smscode");

        // 1.校验手机号是否为空
        if(StringUtils.isEmpty(mobileInRequest)){
            throw new SessionAuthenticationException("手机号不能为空");
        }

        // 2.校验验证码是否为空
        if(StringUtils.isEmpty(smscodeInRequest)){
            throw new SessionAuthenticationException("短信验证码不能为空");
        }

        // 3.校验Session中是否存在短信验证码
        if(Objects.isNull(codeInSession)){
            throw new SessionAuthenticationException("短信验证码不存在");
        }

        // 4.校验服务器session池中的短信验证码是否过期。isExpiration()方法是SmsCode中自定义的方法
        if(codeInSession.isExpiration()){
            // 如果过期，从session中移除短信验证码对象
            session.removeAttribute(MyDefinition.SMS_SESSION_KEY);
            throw new SessionAuthenticationException("短信验证码已过期");
        }

        // 5.校验Session中的短信验证码是否和用户输入的一致
        if(codeInSession.getCode().equalsIgnoreCase(smscodeInRequest)){
            throw new SessionAuthenticationException("输入的短信验证码有误");
        }

        // 6.校验手机号
        if(codeInSession.getMobile().equalsIgnoreCase(mobileInRequest)){
            throw new SessionAuthenticationException("接收短信验证码的手机号与当前输入的手机号不一致");
        }

        // 7.校验输入的手机号是否已经注册
        UserDetails userDetails = myUserDetailsServiceMapper.findByUserName(mobileInRequest);
        if(Objects.isNull(userDetails)){
            throw new SessionAuthenticationException("输入的手机号为注册");
        }

        // 8.如果验证码过期，在Session中移除验证码
        if(codeInSession.isExpiration()){
            session.removeAttribute(MyDefinition.SMS_SESSION_KEY);
        }
    }

}
