package com.xtw.dao;

import java.util.List;

import com.xtw.entity.User;

public interface UserDao {

	//查询所有用户信息
	public List<User> queryAllUsers();
	
}
