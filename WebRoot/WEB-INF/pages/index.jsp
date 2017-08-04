<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="include/path_lib.jsp"%>
<%@ include file="include/easyui_lib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${title}</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${path }/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.north_title{
			background: #f1e5e5;
			display: block;
		}
		.title{
			margin: 10px 10px 10px 130px;
			display: block;
			height: 30px;
		}
		.title span{
			text-align: left;
		}
		.showname{
			text-align: right;
			margin-right: 30px;
			height: 20px;
			padding: 10px 0 0;
			font-size: 16px;
			font-weight: bold;
		}
		.south_footer{
			height: 60px;
			text-align: center;
			background: #f1e5e5;
		}
		.south_footer small{
			margin-top: 10px;
			display: inline-block;
			font-size: 16px;
		}
	</style>
  </head>
  
  <body class="easyui-layout" id="mainbody">
    	<div region="north" split="false" class="north_title" style="height: 100px;text-align:center;" border="false">
    		<div class="title">
    			<span><h1>xxx管理系统</h1></span>
    		</div>
    		<div class="showname">
   				<span>欢迎: ${username}</span>&emsp;
   				<a href="javascript:;" onclick="layout();">退出</a>
    		</div>
    	</div>
    	<div region="west" class="menudiv" split="true" title="系统菜单" style="width:200px;overflow: hidden;">
    		<div id="menuDIv" class="easyui-accordion" fit="false" border="false" animate="false">
    			<div title="用户管理" style="overflow: hidden;">
    				<ul>
    					<li id="rightList11" style="cursor: pointer;" onclick="setmain('用户管理','${path}/user/list.html')">用户管理</li>
    				</ul>
    				<ul>
    					<li id="rightList12" style="cursor: pointer;" onclick="setmain('部门管理','${path}/dept/list.html')">部门管理</li>
    				</ul>
    				<ul>
    					<li id="rightList13" style="cursor: pointer;" onclick="setmain('角色管理','${path}/user/list.html')">角色管理</li>
    				</ul>
    			</div>
    			<div title="部门管理" style="overflow: hidden;">
    				<ul>
    					<li id="rightList21">部门管理</li>
    					<li id="rightList22">部门管理</li>
    				</ul>
    			</div>
    			<div title="xxx管理" style="overflow: hidden;">
    				<ul>
    					<li id="rightList31">xxx管理</li>
    					<li id="rightList32">xxx管理</li>
    				</ul>
    			</div>
    			<div title="xxx管理" style="overflow: hidden;">
    				<ul>
    					<li id="rightList41">xxx管理</li>
    					<li id="rightList42">xxx管理</li>
    				</ul>
    			</div>
    		</div>
    	</div>
    	<div region="center" class="maindiv" title="所在位置:首页" style="overflow: auto;padding: 0px;" href="${path }/welcome.html"></div>
    	<div region="south" class="south_footer" style="overflow: hidden;padding: 0px;" split="false" border="false">
    		<small>@copyright by lailai</small><br/>
    		<small>地址:xxx大厦</small>
    	</div>
    	<div id="mypopwindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsibale="false"
	    	resizable="false" style="margin: 0px; padding: 0px;overflow: auto;">
	    </div>
	    <script type="text/javascript">
	    	$.ajaxSetup({
	    		cache: false
	    	});//ajax不缓存
	    	$(function($){
	    		
	    	});
	    	var options;
	    	//弹出窗口
	    	function showwindow(){
	    		$("#mypopwindow").window(options);
	    	}
	    	//关闭窗口
	    	function closewindow(){
	    		$("#mypopwindow").window('close');
	    	}
	    	//菜单点击
	    	function setmain(title,url){
	    		//$(".combo-panel").parent(".panel").remove();//清楚所有class为combo-panel的class为panel的父元素，解决combobox在页面缓存的问题
	    		$("body").layout('panel','center').panel({
	    			title: "所在位置:"+title,
	    			href: url
	    		});
	    		//$("body").layout('panel','center').panel('refresh');//center区域刷新
	    		return false;
	    	}
	    </script>
  </body>
</html>
