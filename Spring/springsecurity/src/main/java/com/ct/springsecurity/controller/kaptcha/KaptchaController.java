package com.ct.springsecurity.controller.kaptcha;

import com.ct.springsecurity.imagecode.KaptchaImageCode;
import com.ct.springsecurity.utils.MyDefinition;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

// 验证码
@RestController
public class KaptchaController {

    @Resource
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha")
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        // 设置请求头
        // 通过请求头的方式告诉浏览器图片的格式、是否能使用缓存等信息（固定的一些写法）
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");

        // 1.生成验证码文本
        String captchaText = defaultKaptcha.createText();
        // 2.将验证码及其有效期放入Session中。验证码的key在MyDefinition中定义了，因为经常用
        session.setAttribute(MyDefinition.KAPTCHA_SESSION_KEY,new KaptchaImageCode(captchaText,2*60));
        // 3.根据验证码文本生成验证码图片
        BufferedImage bufferedImage =  defaultKaptcha.createImage(captchaText);
        // 4.将验证码图片返回给浏览器
        // 创建写出流
        ServletOutputStream out =  response.getOutputStream();
        // 参数分别为生成的图片、图片格式、使用哪个流写出去
        ImageIO.write(bufferedImage,"jpg",out);
        out.flush();
    }

}
