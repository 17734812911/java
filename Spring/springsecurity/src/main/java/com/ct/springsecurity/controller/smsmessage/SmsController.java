package com.ct.springsecurity.controller.smsmessage;

import com.ct.springsecurity.mapper.MyUserDetailsServiceMapper;
import com.ct.springsecurity.myexception.CustomException;
import com.ct.springsecurity.myexception.CustomExceptionType;
import com.ct.springsecurity.myexception.ResponseFormat;
import com.ct.springsecurity.pojo.MyUserDetails;
import com.ct.springsecurity.smscode.utils.SmsCode;
import com.ct.springsecurity.utils.MyDefinition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 发送短信验证码
 */
@Slf4j
@RestController
public class SmsController {

    @Resource
    MyUserDetailsServiceMapper myUserDetailsServiceMapper;

    // 根据手机号获取验证码
    @GetMapping("/smscode")
    public ResponseFormat sms(@RequestParam String mobile, HttpSession session){
        //根据手机号查询用户是否存在
        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUserName(mobile);
        if(myUserDetails == null){
            return ResponseFormat.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"输入的手机号未注册！"));
        }

        // 随机生成4位的验证码
        SmsCode smsCode = new SmsCode(RandomStringUtils.randomNumeric(4),60,mobile);
        // 调用短信服务提供商的接口，给用户发送短信(这里模拟打印一下)
        log.info(smsCode.getCode() + "--->" + mobile);
        //将验证码放到Session中
        session.setAttribute(MyDefinition.SMS_SESSION_KEY,smsCode);

        return ResponseFormat.success("短信验证码已经发送");

    }
}
