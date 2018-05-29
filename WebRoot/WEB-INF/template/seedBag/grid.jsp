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
	ackground-image: url(/farm/images/welcome.png);
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-color: transparent;
	border: none;
}

.seedBagContainer {
	position: absolute;
	width: 40%;
	height: 90%;
	left: 30%;
	top: 5%;
}

.seedBagPreButton, .seedBagNextButton {
	width: 10%;
	height: 100%;
	float: left;
}

.seedBagContent {
	box-sizing:border-box;
	width: 80%;
	height: 100%;
	float: left;
	border:1px solid white;
	overflow: hidden;
}

.scrollBox {
	width: 0px;
	height: 100%;
	background-color: green;
}

.singleSeed {
	width: 140px;
	height: 100%;
	background-color: white;
	position: relative;
	margin-left: 10px;
	float: left;
}

.seedBagImg>img {
	width: 100%;
}

.seedBagCount {
	margin-left: auto;
	margin-right: auto;
	width: 20px;
	height: 20px;
	background-color: red;
	border-radius: 10px;
}

.seedBagPreButton {
	position: relative;
}

.seedBagNextButton {
	position: relative;
}

.seedBagPreButton>button {
	position: absolute;
	width: 60%;
	height: 20%;
	background: url(/farm/images/pre.jpg) no-repeat;
	background-size: contain;
	top: 30%;
	left:20%;
}

.seedBagNextButton>button {
	position: absolute;
	width: 60%;
	height: 20%;
	background: url(/farm/images/next.jpg) no-repeat;
	background-size: contain;
	top: 30%;
	left:20%;
}
</style>
<body>
	<div class="seedBagContainer">
		<div class="seedBagPreButton">
			<button type="button" onclick="buttonClick(-1)"></button>
		</div>
		<div class="seedBagContent">
			<div class="scrollBox">
			</div>
		</div>
		<div class="seedBagNextButton">
			<button type="button"  onclick="buttonClick(1)"></button>
		</div>
	</div>
	<div id="msgBox"></div> 
</body>
<script>
var listGloble;
var wid=150;
init();
function init(){
	$.post('<%=basePath%>shop/gridData',function(data){
		listGloble=data;
		 draw();
	})
}
function draw(){
	var template='<div class="singleSeed"><div class="seedBagCount">countPlaceolder</div><div class="seedBagImg"><img src="imgPlaceholder" /></div></div>';
	var rs="";
	for(var i=0;i<listGloble.length;i++){
		var tmp=template.replace('countPlaceolder',listGloble[i].cNumber).replace('imgPlaceholder','<%=basePath%>images/crops/'+listGloble[i].cId+'/5.png'); 
		rs=rs+tmp;
	}
	var scrollBox=document.querySelector('.scrollBox');
	scrollBox.style.width=(wid)*listGloble.length+"px";
	var scrollBox=document.querySelector('.scrollBox');
	scrollBox.innerHTML=rs;
	
}
var seedBagContent=document.querySelector('.seedBagContent');
function buttonClick(state){
	if(state>=0){
		seedBagContent.scrollLeft+=wid>>2;
	}
	else{
		seedBagContent.scrollLeft-=wid>>2;
	}
}
function buy(id,caption){
	$.ajax({
		contentType:"application/json",
		url:'<%=basePath%>shop/save',
		type:'post',
		dataType:'json',
		data:JSON.stringify({"cId": id}),
		success:function(data){
			var ms=data.msg;
			if(data.code==0){
				ms="购买成功"
				init();
				parent[0].init();
			}
			$.messager.show({
	                title: "消息",
	                msg: ms
	         });
		}
	})
}
</script>
</html>