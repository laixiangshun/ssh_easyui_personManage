package com.java.spring.DaoImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.spring.dao.UserDao;
import com.java.spring.entity.Userinfo;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Userinfo> getUserList(int page, int rows) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			Query query=session.createQuery("from Userinfo where id>0");
			List<Userinfo> users=query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
			if(users!=null && users.size()>0)
				return users;
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
	public int getUserTotal() {
		return this.sessionFactory.getCurrentSession().createQuery("from Userinfo where id>0").list().size();
	}

	@Override
	public Map<String, Object> queryUsers(Map<String,String[]>param,int page, int rows) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			Map<String,Object>result=new HashMap<String, Object>();
			String countquery="select count(*) from userinfo u left join dept d on u.deptId=d.id where u.id>0";
			String fullquery="select new map(u.id as id,u.name as name,u.age as age,u.birthday as birthday,u.deptId as deptId,"
					+ "u.id as uid,d.name as deptName) from Userinfo u,Dept d where u.deptId=d.id";
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Map<String,Object> params=new HashMap<>();
			StringBuffer buffer=new StringBuffer();
			for(String key:param.keySet()){
				String value=param.get(key)[0];
				if(value!=null && !value.equals("")&& !key.equals("page")&& !key.equals("rows")){
					switch (key) {
					case "name":
						buffer.append(" and u.name like :name");
						params.put("name", value);
						break;
					case "age":
						buffer.append(" and u.age=:age");
						params.put("age", Integer.parseInt(value));
						break;
					case "birthday":
						buffer.append(" and u.birthday like :birthday");
						params.put("birthday", format.parse(value));
						break;
					case "deptId":
						buffer.append(" and d.id=:deptId");
						params.put("deptId", Integer.parseInt(value));
						break;
					case "sort":
						buffer.append(" order by "+value);
						break;
					case "order":
						buffer.append(" "+value);
						break;
				}
			}
		}
			SQLQuery querytotal=session.createSQLQuery(countquery+buffer.toString());
			Query queryList=session.createQuery(fullquery+buffer.toString());
				queryList.setFirstResult((page-1)*rows).setMaxResults(rows);
			if(params!=null && !params.isEmpty()){
				Iterator<String> it=params.keySet().iterator();
				while(it.hasNext()){
					String key=it.next();
					querytotal.setParameter(key, params.get(key));
					queryList.setParameter(key, params.get(key));
				}
			}
			int total=((Number)querytotal.uniqueResult()).intValue();
			List list=queryList.list();//使用new map(),结果list中，每条记录对应一个map，map中key为hql语句中的别名
			result.put("rows", list);
			result.put("total", total);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(session.isOpen())
				session.close();
		}
	}

	@Override
	public int AddUser(Userinfo user) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			session.save(user);
			session.flush();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally{
			if(session.isOpen())
				session.close();
		}
	}

	@Override
	public boolean delete(List<Integer> uid) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		try {
			Query query=session.createQuery("delete from Userinfo where id in :ids");
			query.setParameterList("ids", uid);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(session.isOpen()){
				session.clear();
				session.close();
			}
		}
	}

	@Override
	public boolean modify(Map<String, String[]> param, int id) {
		Session session=this.sessionFactory.getCurrentSession();
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer buffer=new StringBuffer();
			buffer.append("update Userinfo u set u.name=:name,u.age=:age,u.birthday=:birthday,u.deptId=:deptId where u.id=:id");
			Query query=session.createQuery(buffer.toString());
			Iterator<String> it=param.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				String value=param.get(key)[0];
				switch (key) {
				case "name":
					query.setParameter(key, value);
					break;
				case "age":
					query.setParameter(key, Integer.parseInt(value));
					break;
				case "birthday":
					query.setParameter(key, format.parse(value));
					break;
				case "deptId":
					query.setParameter(key, Integer.parseInt(value));
					break;
				}
			}
			query.setParameter("id", id);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(session.isOpen()){
				session.flush();
				session.clear();
				session.close();
			}
		}
	}

}
