package com.ct.springsecurity.imagecode;

import java.time.LocalDateTime;

/**
 * 图片验证码的封装
 */
public class KaptchaImageCode {

    // 验证码文本
    private String code;
    // 验证码过期时间
    private LocalDateTime expirationTime;

    public KaptchaImageCode(String code, int expirationAfterSeconds){
        this.code = code;
        // 验证码过期时间等于当前时间加上多少秒
        this.expirationTime = LocalDateTime.now().plusSeconds(expirationAfterSeconds);
    }

    // 判断验证码是否过期
    public boolean isExpiration(){
        // 如果当前时间在过期时间之后，就是过期了，返回true
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public String getCode() {
        return code;
    }
}
