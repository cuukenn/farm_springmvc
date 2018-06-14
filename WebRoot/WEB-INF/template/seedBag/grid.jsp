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
	width: 60%;
	height: 90%;
	left: 20%;
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
	width: 200px;
	height: 100%;
	background-color: white;
	background: url(/farm/images/boder.png) no-repeat center center;
	background-size:100% 110%;
	position: relative;
	margin-left:10px;
	float: left;
}
.seedBagImg {
	width: 100%;
	margin-left:5%;
}


.seedBagImg>img {
	width: 80%;
}

.seedBagCount {
	margin-left: auto;
	margin-right: auto;
	width: 20px;
	height: 20px;
	line-height:20px;
	background-color: red;
	border-radius: 10px;
	color:yellow;
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
	background: url(/farm/images/pre.png) no-repeat;
	background-size: contain;
	background-position:center;
	top: 30%;
	left:20%;
	border:none;
}

.seedBagNextButton>button {
	position: absolute;
	width: 60%;
	height: 20%;
	background: url(/farm/images/next.png) no-repeat;
	background-size: contain;
	background-position:center;
	top: 30%;
	left:20%;
	border:none;
}
.seedBagPreButton>button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
}
.seedBagNextButton>button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
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
var landIdGloble=0;
var wid=210;
init();
function init(){
	$.post('<%=basePath%>seedBagView/gridData/'+landIdGloble,function(data){
		listGloble=data;
		 draw();
	})
}
function draw(){
	var template='<div class="singleSeed" onclick="selectSeed(cIdslot)"><div class="seedBagCount">countPlaceolder</div><div class="seedBagImg"><img src="imgPlaceholder" /></div></div>';
	var rs="";
	for(var i=0;i<listGloble.length;i++){
		var descript=listGloble[i].caption
			+"\n单季产量:"+listGloble[i].output
			+"\n果实单价:"+listGloble[i].price
			+"\n季数:"+listGloble[i].harvestNum
			+"\n土地:"+listGloble[i].landRequireCaption;
		var tmp=template.replace('cIdslot',listGloble[i].cId).replace('countPlaceolder',listGloble[i].cNumber).replace('imgPlaceholder','<%=basePath%>images/crops/'+listGloble[i].cId+'/'+listGloble[i].growStep+'.png'); 
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
		seedBagContent.scrollLeft+=wid;
	}
	else{
		seedBagContent.scrollLeft-=wid;
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
function selectSeed(cId){
	parent.plantAction(cId);
	parent.window.$('#seedBagContainer').window('close');
}
</script>
</html>