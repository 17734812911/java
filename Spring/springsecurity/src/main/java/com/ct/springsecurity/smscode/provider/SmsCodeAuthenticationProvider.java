package com.ct.springsecurity.smscode.provider;


import com.ct.springsecurity.smscode.token.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *  用于手机验证码登录的Provider
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 登录认证之前的Token,里面有手机号
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        // 从authenticationToken中拿到手机号,再通过手机号查询用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if(userDetails == null){
            throw new InternalAuthenticationServiceException("无法根据手机号获取用户信息");
        }
        // 登录认证之后的Token,里面存放登录认证的主体信息
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 根据传入的Token，用相应的Filter去做登录验证
        // 根据返回的结果去决定当前的Provider是否能做短信验证码的登录验证
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
