<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="include/path_lib.jsp"%>
<%@ include file="include/easyui_lib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
    <div style="margin-top: 30px;padding: 10px;">
    	<form id="deptadd_form" method="post">
    		<p>部门名称:&emsp;<input name="name" class="easyui-validatebox" style="width: 300px;height: 25px;" data-options="required:true,missingMassage:'请输入部门名'"></p>
    		<p>部门代码:&emsp;<input name="code" class="easyui-validatebox" style="width: 300px;height: 25px;" data-options="required:true,missingMassage:'请输入部门代码'"></p>
    		<p style="text-align: center;"><a href="#" onclick="adddept();" class="easyui-linkbutton" iconCls="icon-ok">确定</a></p>
    	</form>
    </div>
    <script type="text/javascript">
    	function adddept(){
    		$("#deptadd_form").form('submit',{
    			url:"${path}/dept/adddept.action",
    			success:function(data){
    				var json=$.parseJSON(data);
    				if(json.success){
    					$.messager.alert("提示",json.message);
    					closewindow();
    					$("body").layout('panel','center').panel('refresh');//center区域刷新
    					$("#deptadd_form").form('clear');
    				}else{
    					$.messager.alert("提示",json.message);
    				}
    			}
    		});
    	}
    	$(function(){
    		$("#deptadd_form input").on("keyup",function(event){
    			var e=event || window.event || arguments.callee.caller.arguments[0];//兼容
    			if(e.keyCode=="13"){
    				adddept();
    			}
    		});
    	});
    </script>
  </body>
</html>
