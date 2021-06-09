package com.ct.springsecurity.smscode.utils;

import java.time.LocalDateTime;

/**
 * 短信验证码的封装
 */
public class SmsCode {

    // 短信验证码文本
    private String code;
    // 验证码过期时间
    private LocalDateTime expirationTime;
    // 手机号
    private String mobile;

    public SmsCode(String code, int expirationAfterSeconds, String mobile){
        this.code = code;
        // 验证码过期时间等于当前时间加上多少秒
        this.expirationTime = LocalDateTime.now().plusSeconds(expirationAfterSeconds);
        this.mobile = mobile;
    }

    // 判断验证码是否过期
    public boolean isExpiration(){
        // 如果当前时间在过期时间之后，就是过期了，返回true
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public String getCode() {
        return code;
    }

    public String getMobile() {
        return mobile;
    }
}
