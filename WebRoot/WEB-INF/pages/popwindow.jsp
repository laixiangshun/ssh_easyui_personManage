<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="include/path_lib.jsp"%>
<%@ include file="include/easyui_lib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		#reg_form{
			width: 100%;
			font-size: 18px;
			font-weight: bolder;
			text-align: center;
			display: block;
			margin-left: 20px;
		}
		#reg_form p input{
			width: 400px;
			display: block;
		}
		#reg_form.btn_add{
			text-align: center;
			width: 300px;
		}
	</style>
  </head>
  
  <body>
  		<input type="hidden" value="${flag}" id="modifyFlag">
  		<input type="hidden" value="${uid}" id="uid">
   		<div>
   			<form id="reg_form" method="post">
   				<p>用户名:<input name="name" class="easyui-validatebox" style="width: 350px;height: 25px;" data-options="required:true,missingMassage:'请输入用户名'"></p>
   				<p>年&emsp;龄:<input name="age" class="easyui-numberspinner" style="width:350px;height: 25px;" min="1" max="120" increment="1"/></p>
   				<p>生&emsp;日:<input name="birthday" class="Wdate" style="width: 350px;height: 25px;" onclick="WdatePicker();"></p>
   				<p>部&emsp;门:<input id="dept_Combo" name="deptId" style="width: 350px;height: 25px;" data-options="required:true,missingMassage:'请选择部门'"></p>
   				<div class="btn_add" style="width: 300px;text-align: center;">
   					<a href="#" onclick="addUser();" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
   				</div>
   			</form>
   		</div>
   		<script type="text/javascript">
   			$(function(){
   				$("#dept_Combo").combogrid({
   					idField:'id',
   					textField:'name',
   					url:"${path}/dept/queryAll.action",
   					fitColumns: true,
   					striped: true,
   					editable: false,
   					frozenColumns:[[
	  					{field: 'code',title: '编号',width:100},
	  					{field: 'name',title: '名称',width: 150}
	  				]]
   				});
   				$("#reg_form input").on("keyup",function(e){
   					if(e.keyCode=="13"){
   						//$("#reg_form").submit();
   						addUser();
   					}
   				});
   			});
   			function addUser(){
   				var modifyflag=$("#modifyFlag").val();
   				if(modifyflag=="modify"){
   					var uid=$("#uid").val();
   					$("#reg_form").form('submit',{
	   					url: "${path}/user/modify.action",
	   					onSubmit:function(param){
	   						param.uid=uid;//传递form表单外的额外参数uid
	   					},
	   					success: function(data){
	   						var json=$.parseJSON(data);
	   						if(json.success){
	   							$.messager.alert({
	   								title:"提示",
	   								msg:json.message
	   							});
	   							$("#mypopwindow").window('close');
	   							$("body").layout('panel','center').panel('refresh');
	   						}else{
	   							$.messager.alert({
	   								title:"提示",
	   								msg:json.message
	   							});
	   						}
	   					}
	   				});
   				}else{
   					$("#reg_form").form('submit',{
	   					url: "${path}/user/add.action",
	   					success: function(data){
	   						var json=$.parseJSON(data);
	   						if(json.success){
	   							$.messager.alert({
	   								title:"提示",
	   								msg:json.message
	   							});
	   							$("#mypopwindow").window('close');
	   							$("body").layout('panel','center').panel('refresh');
	   						}else{
	   							$.messager.alert({
	   								title:"提示",
	   								msg:json.message
	   							});
	   						}
	   					}
	   				});
   				}
   			}
   		</script>
  </body>
</html>
