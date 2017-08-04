package com.java.spring.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.java.spring.entity.Dept;
import com.java.spring.service.DeptService;
import com.java.spring.utils.ResultModel;

@Controller
@RequestMapping("/dept")
public class DeptController {
	Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="/queryAll.action",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> QueryDeptList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result=new HashedMap();
		List<Dept> list=deptService.queryDept();
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value="/queryAllDept.action",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> QueryDeptListByCondition(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> result=new HashedMap();
		String currentpage=request.getParameter("page");
		int page=Integer.parseInt((currentpage==null || currentpage=="0")?"1":currentpage);
		String size=request.getParameter("rows");
		int pagesize=Integer.parseInt((size==null || size=="0")?"10":size);
		result=deptService.queryDeptByCondition(page,pagesize);
		return result;
	}
	@RequestMapping(value="/list.html",method=RequestMethod.GET)
	public String DeptHtml(Model model)throws Exception{
		model.addAttribute("title", "部门管理");
		return "deptIndex";
	}
	@RequestMapping(value="/popwindow.html",method=RequestMethod.GET)
	public String AdddeptHtml(HttpServletRequest request)throws Exception{
		return "popDept";
	}
	
	@RequestMapping(value="/adddept.action",method=RequestMethod.POST)
	public void AddDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Dept dept=new Dept();
		response.setContentType("text/json;charset=utf-8");
		ResultModel result=new ResultModel();
		try {
			for(String key:request.getParameterMap().keySet()){
				String value=request.getParameterMap().get(key)[0];
				switch(key){
				case "name":
					dept.setName(value);
					break;
				case "code":
					dept.setCode(value);
					break;
				}
			}
			int num=this.deptService.addDept(dept);
			if(num==1){
				result.setMessage("添加部门成功");
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
				result.setMessage("添加失败");
			}
		} catch (Exception e) {
			logger.error("添加部门"+e.getMessage(), e);
		}
		String json=JSON.toJSONString(result);
		response.getWriter().write(json);
	}
}
