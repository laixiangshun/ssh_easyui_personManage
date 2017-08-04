package com.java.spring.serverImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.spring.dao.DeptDao;
import com.java.spring.entity.Dept;
import com.java.spring.service.DeptService;

@Service
@Transactional
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptDao deptDao;
	@Override
	public List<Dept> queryDept() {
		// TODO Auto-generated method stub
		return deptDao.queryDept();
	}
	@Override
	public Map<String, Object> queryDeptByCondition(int page, int rows) {
		// TODO Auto-generated method stub
		return deptDao.queryDeptByCondition(page,rows);
	}
	@Override
	public int addDept(Dept dept) {
		// TODO Auto-generated method stub
		int num=this.deptDao.addDept(dept);
		if(num!=1){
			throw new RuntimeException("添加部门异常");
		}
		return num;
	}

}
