<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="include/path_lib.jsp" %>
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
	<script type="text/javascript" src="${path }/My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
	</style>
  </head>
  
  <body>
  		<form id="queryForm" style="margin: 10px;text-align: center;">
  			<table  width="50%">
  				<tr>
  					<td>名字:<input name="name" style="width: 200px;"/></td>
  					<td>年龄:<input name="age" class="easyui-numberspinner" min="1" max="120" increment="1" style="width: 200px;"/></td>
  					<td align="center"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-reload">清空</a></td>
  				</tr>
  				<tr>
  					<td>生日:<input name="birthday" style="width: 200px;" class="Wdate" onclick="WdatePicker();"></td>
  					<td>部门:<input id="deptCombo" name="deptId" style="width: 200px;"></td>
  					<td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
  				</tr>
  			</table>
  		</form>
  		<div style="padding: 10px;" id="tabdiv">
  			<table id="userTable"></table>
  		</div>
  		<script type="text/javascript">
  		
  			$(function(){
  				//下拉列表
  				$("#deptCombo").combogrid({
	  				idField: 'id',//id字段
	  				textField: 'name',//显示的字段
	  				url: "${path}/dept/queryAll.action",
	  				fitColumns: true,
	  				striped: true,
	  				editable: false,
	  				frozenColumns:[[
	  					{field: 'code',title: '编号',width:100},
	  					{field: 'name',title: '名称',width: 150}
	  				]]
	  			});
	  			$("#userTable").datagrid({
	  				title: "用户列表",
	  				method: "post",
	  				iconCls: "icon-edit",
	  				singleSelect: false,//多选
	  				height: 460,
	  				fitColumns: true,//自动调整各列
	  				stripted: true,//奇偶行颜色不同
	  				collapsible: true,//可折叠
	  				nowrap: true,//设置为true，在数据长度超过列宽时自动截取
	  				url: "${path}/user/queryList.action",
	  				sortName: "u.id",
	  				sortOrder: "desc",
	  				remoteSort: true,//服务器端排序
	  				idField: "uid",//主键字段
	  				queryParams: {},//查询条件
	  				pagination: true,//显示分页
	  				rownumbers: true,//显示行号
	  				frozenColumns: [[
	  					{field: "ck",checkbox: true,width:'2%'},//显示复选框
	  					{field:"row.name",title:"名字",width:'20%',sortable:true,
	  						formatter:function(value,row,index){return row.name;}//需要formatter一下才能显示正确的数据
	  					},
	  					{field:"row.age",title:"年龄",width:'15%',sortable:true,formatter:function(value,rows,index){
	  						return rows.age;}
  						},
  						{field:"row.birthday",title:"生日",width:'30%',sortable:true,
  							formatter:function(value,rows,index){return rows.birthday;}
  						},
  						{field:"row.deptId",title:"部门",width:'30%',sortable:true,
  							formatter:function(value,rows,index){return rows.deptName;}//该列的值是deptId，显示的是deptName
  						}
	  				]],
	  				toolbar:[{
	  					text:"新增",
	  					iconCls:"icon-add",
	  					handler:function(){
	  						addrow();
	  					}
	  				},"-",{
	  					text:"更新",
	  					iconCls:"icon-edit",
	  					handler:function(){
	  						updaterow();
	  					}
	  				},"-",{
	  					text:"删除",
	  					iconCls:"icon-remove",
	  					handler:function(){
	  						deleterow();
	  					}
	  				},"-"],
	  				onLoadSuccess:function(){
	  					$("#userTable").datagrid('clearSelections');//清除之前选中的行
	  				}
	  			});
  			});
  			function addrow(){
  				options={
  					title:"增加用户信息",
  					href:"${path}/user/popwindow.html",
  					width: 450,
  					height: 250,
  					onLoad: function(){
  						$("#userForm").form('clear');
  					}
  				};
  				showwindow(options);
  				/* showwindow({
  					title:"增加用户信息",
  					href:"${path}/user/popwindow.html",
  					width: 300,
  					height: 250,
  					onLoad: function(){
  						$("#userForm").form('clear');
  					}
  				}); */
  			}
  			function updaterow(){
  				var rows=$("#userTable").datagrid("getSelections");
  				if(rows.length!=1){
  					$.messager.alert("提示","请选择一位用户进行更新","info");
  					return false;
  				}
  				options={
  					title:"更新用户信息",
  					href:"${path}/user/popwindow.html?uid="+rows[0].uid,
  					width:450,
  					heigth: 250,
  					onLoad:function(){
  						$("#reg_form").form("load",rows[0]);
  					}
  				};
  				showwindow(options);
  			}
  			function deleterow(){
  				$.messager.confirm("提示","确定要删除吗？",function(result){
  					if(result){
  						var rows=$("#userTable").datagrid("getSelections");
  						var ps="";
  						$.each(rows,function(i,n){
  							if(i==0){
  								ps+="?uid="+n.uid;
  							}else{
  								ps+="&uid="+n.uid;
  							}
  						});
  						$.post("${path}/user/delete.action"+ps,function(data){
  							$("#userTable").datagrid("reload");
  							$.messager.alert("提示",data.message,"info");
  						});
  					}
  				});
  			}
  			function searchUser(){
  				var params=$("#userTable").datagrid('options').queryParams;//取得datagrid的查询参数
  				var fields=$("#queryForm").serializeArray();//自动序列化表单元素为json对象
  				$.each(fields,function(i,field){
  					params[field.name]=field.value;//设置参数
  				});
  				$("#userTable").datagrid("reload");//设置好查询参数，reload
  			}
  			function clearForm(){
  				$("#queryForm").form("clear");
  				searchUser();
  			}
  		</script>
  </body>
</html>
