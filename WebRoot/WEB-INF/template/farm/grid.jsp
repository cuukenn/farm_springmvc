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
html, body {
	margin: 0px;
	border: none;
	width: 100%;
	height: 100%;
}

body {
	margin: 0px;
	ackground-image: url(<%=basePath%>images/welcome.png);
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-color: transparent;
	border: none;
	overflow-y:scroll;
}

.farm {
	width: 100%;
	position: absolute;
	left: 0px;
	top: -190px;
}

.tools-imagePositioner-display {
	position: absolute;
	width: 200px;
	height: 300px;
}

.tools-imagePositioner-display>img {
	position: absolute;
	width: 200px;
	height: 300px;
}
</style>
<body>
	<div class="farm"></div>
</body>
<script>
	resizeFrame();
	function resizeFrame() {
		window.parent.document.getElementById("tools").src = "tools.jsp";
		window.parent.document.getElementById("framesets").rows = '60,*,50';
	}
	const cols=6;
	const rows=4;

	let temlate='<div class="tools-imagePositioner-display"><img src="<%=basePath%>images/farmLandDefault.png" alt=""></div>';
	temlate=temlate.repeat(cols*rows);

	let offsetX=0;
	let offsetY=0;

	const wid=180;
	const hei=60;
	const rowsHei=80;

	const offTmp=440;
	const offTmpDe=140;
	document.querySelector('.farm').innerHTML=temlate;
	document.querySelector('.farm').style.height=rows*hei+300+'px';
	for(let row=0;row<rows;row++){
		for(let index=1;index<=cols;index++){
			let elmS='.farm>div:nth-child('+(cols*row+index)+')';
			let elm=document.querySelector(elmS);
			offsetX=(index-1)*wid+offTmp-row*offTmpDe;
			offsetY=(index-1)*hei+row*rowsHei;
			if(index===1){
				offsetX=0+offTmp-row*offTmpDe;
				offsetY=0+row*rowsHei;
			}
			elm.style.left=offsetX+'px';
			elm.style.top=offsetY+'px';
		}
	}
</script>
</html>