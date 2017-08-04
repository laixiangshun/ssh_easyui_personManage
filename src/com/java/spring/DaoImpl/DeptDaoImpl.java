package com.java.spring.DaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.spring.dao.DeptDao;
import com.java.spring.entity.Dept;

@Repository
public class DeptDaoImpl implements DeptDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> queryDept() {
		Session session=sessionFactory.openSession();
		try {
			Query query=session.createQuery("from Dept where id>0");
			List<Dept> list=new ArrayList<>();
			list=query.list();
			if(list!=null && list.size()>0)
				return list;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(session.isOpen())
				session.close();
		}
	}

	@Override
	public Map<String, Object> queryDeptByCondition(int page, int rows) {
		Session session=this.sessionFactory.openSession();
		try {
			String totalquery="select count(*) from Dept d where d.id>0";
			String query="select new map(d as dept) from Dept d where d.id>0";
			Query q=session.createQuery(totalquery);
			Query qq=session.createQuery(query);
			qq.setFirstResult((page-1)*rows).setMaxResults(rows);
			Map<String, Object> result=new HashMap<String, Object>();
			List list=qq.list();
			int total=((Number)q.uniqueResult()).intValue();
			result.put("total", total);
			result.put("rows", list);
			if(result!=null || result.size()>0)
				return result;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(session.isOpen())
				session.close();
		}
	}

	@Override
	public int addDept(Dept dept) {
		try {
			Session session=this.sessionFactory.openSession();
			session.save(dept);
			session.flush();
			session.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}
