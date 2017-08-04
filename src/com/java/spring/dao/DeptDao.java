package com.java.spring.dao;

import java.util.List;
import java.util.Map;

import com.java.spring.entity.Dept;

public interface DeptDao {

	public List<Dept> queryDept();
	
	public Map<String, Object> queryDeptByCondition(int page,int rows);
	
	public int addDept(Dept dept);
}
