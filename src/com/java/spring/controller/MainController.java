package com.java.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value="/welcome.html",method=RequestMethod.GET)
	public String welcomeHtml(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "welcome";
	}
	@RequestMapping(value="/index.html",method=RequestMethod.GET)
	public String IndexHtml(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		model.addAttribute("title", "主页");
		return "index";
	}
}
