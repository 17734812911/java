package com.xtw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.xtw.util.ImageGenerate;

/**
 * 	登录控制
 * @author Mr.Chen
 * 2020年8月3日
 */
@Controller
public class LoginController {
	
	//验证码
	@ResponseBody
	@RequestMapping("imageGenerate")
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 1.生成验证码
         * 2.把验证码上的文本存在session中
         * 3.把验证码图片发送给客户端
         */
		ImageGenerate ig = new ImageGenerate();    //用我们的验证码类，生成验证码类对象
        BufferedImage image = ig.getImage();  //获取验证码
        request.getSession().setAttribute("imageText", ig.getText()); //将验证码的文本存在session中
        ImageGenerate.output(image, response.getOutputStream()); // 将验证码图片响应给客户端
    }
	
	//登录验证
	@ResponseBody
	@RequestMapping("login")
	public String login(@RequestParam("username") String userName,
						@RequestParam("password") String passWord,
						@RequestParam("vercode") String verCode,  //验证码
						@RequestParam("remflag") String remflag,  //是否记住密码
						HttpSession session,
						HttpServletResponse response) {
		
		String imgCode = (String)session.getAttribute("imageText");  //系统生成的验证码内容

		//连接数据库以后，可以将查到的用户信息放在session中，下次如果session中不存在才查询数据库
		
		
		
		
		//登录逻辑判断
		if(! StringUtils.isEmpty(userName) && "112233".equals(passWord) && ! imgCode.equalsIgnoreCase(verCode)) {
			session.setAttribute("imgCodeMsg", "验证码错误");
			return "1";
		}else if(! StringUtils.isEmpty(userName) && "112233".equals(passWord) && imgCode.equalsIgnoreCase(verCode)) {
			//记住密码，将账号密码放入cookie
			if("true".equals(remflag)) {
				String loginInfo = userName + ":" + passWord;
	            Cookie userCookie=new Cookie("loginInfo",loginInfo);

	            userCookie.setMaxAge(60*60);   //存活期为一个月 30*24*60*60
	            userCookie.setPath("/");  //可在同一应用服务器内共享
	            response.addCookie(userCookie);  //将cookie对象加入response响应
			}
			//登录成功，就将用户信息放入session中
			session.setAttribute("loginUser", userName);
			return "0";
		}else {
			return "2";
		}
	}
	
	
	//找回密码
	@ResponseBody
	@RequestMapping("forGet")
	public String forGet(@RequestParam("cellphone") String phone,
						 @RequestParam("vercode") String verCode,
						 @RequestParam("vercode2") String vercode_2, //短信验证码
						 HttpSession session) {
		String imgCode = (String)session.getAttribute("imageText");  //系统生成的验证码内容
		if(!imgCode.equalsIgnoreCase(verCode)) {
			session.setAttribute("imgCodeMsg", "图片验证码错误");
			return "1";
		}else if(1==1) {
			//1、用户输入的手机号要存在2、短信验证码要正确3、
		}
		
		return null;
		
	}
}
