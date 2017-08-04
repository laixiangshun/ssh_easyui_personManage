package com.java.spring.service;

import java.util.List;
import java.util.Map;

import com.java.spring.entity.Userinfo;

public interface UserService {

	public List<Userinfo> getUserList(int page,int rows);
	
	public int getUserTotal();
	
	public Map<String,Object> queryUsers(Map<String,String[]>param,int page,int rows);
	
	public int AddUser(Userinfo user);
	
	public boolean delete(List<Integer> uid);
	
	public boolean modify(Map<String, String[]>param,int id);
}
