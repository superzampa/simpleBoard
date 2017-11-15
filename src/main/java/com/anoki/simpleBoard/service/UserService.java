package com.anoki.simpleBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.models.User;
import com.anoki.simpleBoard.dao.UserDao;

@Service("UserService")
public class UserService{
	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	public List<User> findAll() {
		return userDao.findAll();
	}
	
}