package com.xtw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class JumpController {
	
	//跳转到找回密码页面
	@RequestMapping("toForget")
	public String toForget() {
		return "user/forget";
	}
	
	//跳转到注册页面
	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/reg";
	}
	
	//去登录界面
	@RequestMapping("toLogin")
	public String toLogin() {
		return "user/login";
	}
	
	//到首页
	@RequestMapping("toIndex")
	public String toIndex() {
		//默认classpath:templates/index.html
		return "index";
	}
	
	//去控制台
	@RequestMapping("toConsole")
	public String toConsole() {
		return "home/console";
	}
	
	//主页一
	@RequestMapping("toHomePage1")
	public String toHomePage1() {
		return "home/homepage1";
	}
	
	//主页二
	@RequestMapping("toHomePage2")
	public String toHomePage2() {
		return "home/homepage2";
	}
	
	//搜索
	@RequestMapping("toSearch")
	public String toSearch() {  //@RequestParam("data") String data
		return "template/search";
	}
	
	//消息中心
	@RequestMapping("toMessageCenter")
	public String toMessageCenter() {
		return "app/message/index";
	}
	
	//用户基本资料
	@RequestMapping("toUserInfo")
	public String toUserInfo() {
		return "set/user/info";
	}
	
	//修改密码
	@RequestMapping("toEditPassWord")
	public String toEditPassWord() {
		return "set/user/password";
	}
	
	//等比例列表排列
	@RequestMapping("toList")
	public String tolist() {
		return "component/grid/list";
	}
	
	//按移动端排列
	@RequestMapping("toMobile")
	public String tomobile() {
		return "component/grid/mobile";
	}
	
	//网站用户列表
	@RequestMapping("toUserList")
	public String toUserList() {
		return "user/user/list";
	}
	
	//后台管理员列表
	@RequestMapping("toAdminList")
	public String toAdminList() {
		return "user/administrators/list";
	}
	
	//后台管理员列表
	@RequestMapping("toRoleAdmin")
	public String toRoleAdmin() {
		return "user/administrators/role";
	}
	
	//用户信息
	@RequestMapping("toUserForm")
	public String toUserForm() {
		return "user/user/userform";
	}
	
	//管理员信息
	@RequestMapping("toAdminForm")
	public String toAdminForm() {
		return "user/administrators/adminform";
	}
	
	//管理员信息
	@RequestMapping("toRoleForm")
	public String toRoleForm() {
		return "user/administrators/roleform";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
