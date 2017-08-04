package com.java.spring.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.java.spring.entity.Userinfo;
import com.java.spring.service.UserService;
import com.java.spring.utils.ResultModel;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger=Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	/**
	 * 返回用户页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list.html",method=RequestMethod.GET)
	public String UserList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "userList";
	}
	
	@RequestMapping(value="/queryList.action",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> QueryUserList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String currentpage=request.getParameter("page");
		int page=Integer.parseInt((currentpage==null || currentpage=="0")?"1":currentpage);
		String pagesize=request.getParameter("rows");
		int rows=Integer.parseInt((pagesize==null || pagesize=="0")?"10":pagesize);
		Map<String, Object> result=new HashMap<>();
		Map<String,String[]>param=request.getParameterMap();
		/*List<Userinfo> list=this.userService.getUserList(page, rows);
		int total=this.userService.getUserTotal();
		result.put("rows", list);
		result.put("total", total);*/
		result=this.userService.queryUsers(param,page, rows);
		return result;
	}
	@RequestMapping(value="/popwindow.html",method=RequestMethod.GET)
	public String AddUserHtml(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		model.addAttribute("flag", "modify");
		String uid=request.getParameter("uid");
		model.addAttribute("uid", uid);
		return "popwindow";
	}
	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	public void AddUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Userinfo user=new Userinfo();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		ResultModel result=new ResultModel();
		response.setContentType("text/json;charset=utf-8");
		for(String key: request.getParameterMap().keySet()){
			String value=request.getParameterMap().get(key)[0];
			switch (key) {
			case "name":
				user.setName(value);
				break;
			case "age":
				user.setAge(Integer.parseInt(value));
				break;
			case "birthday":
				user.setBirthday(format.parse(value));
				break;
			case "deptId":
				user.setDeptId(Integer.parseInt(value));
				break;
			}
		}
		int num=this.userService.AddUser(user);
		if(num==1){
			result.setMessage("添加用户成功");
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
			result.setMessage("添加用户失败");
		}
		String json=JSON.toJSONString(result);
		response.getWriter().write(json);
	}
	
	@RequestMapping(value="/delete.action",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> DeleteUser(@RequestParam("uid") List<Integer> uid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> result=new HashMap<>();
		try {
			boolean falg=this.userService.delete(uid);
			if(falg){
				result.put("message", "删除成功");
			}else{
				result.put("message", "删除失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("删除用户异常"+e.getMessage(), e);
			result.put("message", "删除异常");
			throw e;
		}
		return result;
	}
	
	@RequestMapping(value="/modify.action",method=RequestMethod.POST)
	public void ModifyUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		ResultModel result=new ResultModel();
		try {
			String uid=request.getParameter("uid");
			int id=Integer.parseInt(uid);
			Map<String, String[]>param=request.getParameterMap();
			boolean flag=this.userService.modify(param,id);
			if(flag){
				result.setSuccess(true);
				result.setMessage("修改成功");
			}else{
				result.setMessage("修改失败");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("更新用户失败"+e.getMessage(), e);
			result.setMessage("更新用户异常");
			throw e;
		}
		String json=JSON.toJSONString(result);
		response.getWriter().write(json);
	}
}
