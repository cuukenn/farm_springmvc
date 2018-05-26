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

.cardView {
	width: 200px;
	height: 300px;
	background-color: green;
	float: left;
	margin-left: 10px;
}

.cardViewContent {
	width: 100%;
	height: 80%;
	background: url(/farm/images/boder.jpg) no-repeat center center;
	background-size: cover;
	position: relative;
}

.cardViewBottomDescript {
	position: absolute;
	width: 100%;
	top: 2%;
	z-index: 2;
}

.cardViewBottomDescript>p {
	text-align: center;
	color: black;
}

.cardViewBottomImg {
	position: absolute;
	z-index: 1;
	width: 100%;
	height: 80%;
	bottom: 0px;
}

.cardViewBottomImg>img {
	width: 76%;
	margin-left: 12%;
}

.cardViewBottom {
	width: 100%;
	height: 20%;
	background-color: yellow;
	position: relative;
}

.cardViewBottomButton {
	position: absolute;
	left: 25%;
	top: 30%;
	width: 50%;
	height: 40%;
	border-radius: 10px;
}

#tableBox {
	position: absolute;
	width: 100%;
	height: 600px;
	left: 20%;
	top: 0%;
}

.seedBagContainer {
	position: absolute;
	width: 40%;
	height: 30%;
	left: 30%;
	top: 70%;
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
	<div id="tableBox">
		<table id="tt" style="width: 60%; height: 70%;">
			<thead>
				<tr>
					<th field="caption" width="80" sortable="true">种子名称</th>
					<th field="cropLevel" width="80" sortable="true">种子等级</th>
					<th field="type" width="80" sortable="true">种子类型</th>
					<th field="langRequirement" width="150" sortable="true">种子需求</th>
				</tr>
			</thead>
		</table>
	</div>
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
var LandCationData;
getLandCationData();
init();
var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
	renderRow: function(target, fields, frozen, rowIndex, rowData){
		var cc = [];
		var imgUrl=rowData.cId+"/5.png";
		console.log(rowData)
		var descript="名称:"+rowData.caption
		+"\n级别:"+rowData.cropLevel
		+"\n价格:"+rowData.price
		+"\n类别:"+rowData.type
		+"\n土地:"+getLandCation(rowData.landRequirement)
		+"\n可收获季:"+rowData.harvestNum
		+"\n成熟时间:"+rowData.matureTime
		+"\n当季收获:"+rowData.output
		+"\n经验收获:"+rowData.exp
		+"\n单个果实可获金币:"+rowData.price4UnitSale;
		console.log(descript)
		if (!frozen){
			var rs='<div class="cardView">'
					+'<div class="cardViewContent">'
						+'<div class="cardViewBottomDescript">'
							+'<p>'+rowData.tip+'</p>'
						+'</div>'
						+'<div class="cardViewBottomImg">'
							+'<img src="'+'<%=basePath%>images/crops/'+imgUrl+'" title="'+descript+'" />'
						+'</div>'
					+'</div>'
					+'<div class="cardViewBottom">'
						+'<button onclick="buy('+rowData.cId+')" class="cardViewBottomButton">我要购买</button>'
					+'</div>'
				+'</div>';
		}
		cc.push(rs);
		return cc.join('');
	}
});
$('#tt').datagrid({
	title:"种子仓库",
	url: '<%=basePath%>seed/gridData/',
	method:'post',
	border: false,
	rownumbers: true,
	remoteSort: true,
	nowrap: false,
	singleSelect: true,
	fitColumns: true,
	pagination: true,
	striped: true,
	autoSave:true,
	view: cardview
});
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
function buy(id){
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
function getLandCationData(){
	$.ajax({
		url:'<%=basePath%>codeLandRequire/data',
		type:'post',
		dataType:'json',
		success:function(data){
			LandCationData=data;
		},
		async:false
	})
}
function getLandCation(id){
	for(var index in LandCationData){
		if(LandCationData[index].code==id){
			return LandCationData[index].caption;
		}
	}
}
</script>
</html>