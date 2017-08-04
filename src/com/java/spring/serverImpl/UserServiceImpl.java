package com.java.spring.serverImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.spring.dao.UserDao;
import com.java.spring.entity.Userinfo;
import com.java.spring.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Userinfo> getUserList(int page, int rows) {
		// TODO Auto-generated method stub
		return userDao.getUserList(page, rows);
	}

	@Override
	public int getUserTotal() {
		// TODO Auto-generated method stub
		return userDao.getUserTotal();
	}

	@Override
	public Map<String, Object> queryUsers(Map<String,String[]>param,int page, int rows) {
		// TODO Auto-generated method stub
		return userDao.queryUsers(param,page, rows);
	}

	@Override
	public int AddUser(Userinfo user) {
		// TODO Auto-generated method stub
		return this.userDao.AddUser(user);
	}

	@Override
	public boolean delete(List<Integer> uid) {
		// TODO Auto-generated method stub
		boolean flag=this.userDao.delete(uid);
		if(!flag){
			throw new RuntimeException("删除用户失败");
		}
		return flag;
	}

	@Override
	public boolean modify(Map<String, String[]> param, int id) {
		// TODO Auto-generated method stub
		boolean flag=this.userDao.modify(param,id);
		if(!flag){
			throw new RuntimeException("修改用户失败");
		}
		return flag;
	}

}
