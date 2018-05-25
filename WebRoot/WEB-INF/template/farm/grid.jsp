<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/green/easyui.css?t=34355">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/farm.css">
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js?346t"></script> 
</head>
<style>
	html,body{
       margin: 0px;
	   border:none;
	   width:100%;
	   height:100%;
    }
    body{
       margin: 0px;
      ackground-image:url(images/welcome.png);
       background-size:100% 100%;
       background-repeat:no-repeat;
       background-color: transparent;
		border:none;
        }
</style>
<body>
    <div id="windowContainer" class="easyui-window" title="用户选择" style="width:35%;height:40%;" closed="false">
		<form style="width:100%;">
			<p>当前用户</p>
			<input id="userId" class="easyui-combobox" name="id" style="width:100%"
				data-options="
							url: '<%=basePath%>user/gridDataALL',
							method: 'post',
							valueField: 'id',
							textField:'username',
							panelWidth: '35%',
							panelHeight: 'auto',
							formatter: formatItem" />
			<p>请在下拉列表框中选择用户昵称，并点击确认按钮设置当前用户信息</p>
			<button style="width:20%;border-radius:10px;margin-left:40%;margin-top:10%" type="button" onclick="javascript:changeUser()">确认</button>
		</form>
	</div>
    <div id="msgBox"></div> 
</body>
<script>
var recordGlobal=null;
$(function(){
	$('#userId').combobox({
		onSelect: function(record){
			recordGlobal=record;
		}
	});
})
function formatItem(row){
	return '<img style="height:30px;" src="<%=basePath%>images/headImages/'+row.heads+'"/>'+'|'
			+row.username+'|'
			+'经验:'+row.exp+'|'
			+'金币:'+row.price+'|'
			+'积分:'+row.score;
}
function changeUser(){
	$.ajax({
		contentType:"application/json",
		url:'<%=basePath%>farm/setCurUser',
		type:'post',
		dataType:'json',
		data:JSON.stringify(recordGlobal),
		success:function(data){
			var ms="";
			if(data.code==0){
				ms="切换"+recordGlobal.username+"成功";
				parent[0].changeInfo(recordGlobal);
			}
			else ms="切换失败，请重新切换"
			$.messager.show({
                title: "消息",
                msg: ms
            });
		}
	})
}
</script>
</html>