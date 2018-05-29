<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>我的农场</title>
    <style>
        body {
            margin: 0px;           
        }
    </style>
</head>
<frameset id="framesets" name="framesets" rows="60,*,50" border="0">
	<frame name="bar" src="menu.jsp" scrolling="no">
	<frame name="workspace" src="welcome.jsp" scrolling="no">
	<frame id="tools" name="tools" src="tools.jsp" scrolling="no"> 		
</frameset>
</html>
