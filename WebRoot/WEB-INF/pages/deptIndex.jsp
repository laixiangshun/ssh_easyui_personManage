<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="include/path_lib.jsp"%>
<%@ include file="include/easyui_lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  </head>
  
  <body>
    	<form id="dept_form" style="margin: 10px;text-align: center;">
    		<table  width="50%">
  				<tr>
  					<td>部门名称:<input name="name" style="width: 200px;"/></td>
  					<td>部门代码:<input name="code" style="width: 200px;"/></td>
  					<td align="center"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-reload">清空</a></td>
  					<td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
  				</tr>
  			</table>
    	</form>
    	<div style="padding: 10px;" id="tab_div">
    		<table id="tab_dept"></table>
    	</div>
    	<script type="text/javascript">
    		$(function(){
    			$("#tab_dept").datagrid({
    				title:"部门列表",
    				method:"post",
    				iconCls: "icon-edit",
    				singleSelect: false,//多选
    				height: 500,
	  				fitColumns: true,//自动调整各列
	  				stripted: true,//奇偶行颜色不同
	  				collapsible: false,//可折叠
	  				nowrap: true,//设置为true，在数据长度超过列宽时自动截取
	  				url: "${path}/dept/queryAllDept.action",
	  				//sortName: "user.id",
	  				//sortOrder: "desc",
	  				remoteSort: false,//服务器端排序
	  				idField: "dept.id",//主键字段
	  				queryParams: {},//查询条件
	  				pagination: true,//显示分页
	  				rownumbers: true,//显示行号
	  				frozenColumns: [[
	  					{field: "ck",checkbox: true,width:'2%'},//显示复选框
	  					{field:"dept.name",title:"部门名称",width:'20%',sortable:true,
	  						formatter:function(value,row,index){return row.dept.name;}//需要formatter一下才能显示正确的数据
	  					},
	  					{field:"dept.code",title:"部门代码",width:'15%',sortable:true,
	  						formatter:function(value,rows,index){return rows.dept.code;}
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
	  					$("#tab_dept").datagrid('clearSelections');//清除之前选中的行
	  				}
	  			});
    		});
    		function addrow(){
    			options={
  					title:"增加部门信息",
  					href:"${path}/dept/popwindow.html",
  					width: 450,
  					height: 250,
  					onLoad: function(){
  						$("#dept_form").form('clear');
  					}
  				};
  				showwindow(options);
  				options=null;
    		}
    		function updaterow(){
    		}
    		function deleterow(){
    		
    		}
    	</script>
  </body>
</html>
