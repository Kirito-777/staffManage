package com.staff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staff.bean.User;
import com.staff.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	public List<User> getAll(){
		return userMapper.selectByExample(null);
	}
	
	public long isexist(String username) {
		return userMapper.isexistByUsername(username);
	}
}
