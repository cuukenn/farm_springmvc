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
	margin-bottom: 10px;
}

.cardViewContent {
	width: 100%;
	height: 80%;
	background: url(/farm/images/boder.png) no-repeat center center;
	background-size: cover;
	position: relative;
}

.cardViewBottomDescript {
	position: absolute;
	width: 90%;
	top: 2%;
	left:4%;
	z-index: 2;
}

.cardViewBottomDescript>p {
	text-align: center;
	color: white;
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
	height: 570px;
	left: 20%;
	top: 0%;
}
</style>
<body>
	<div id="tableBox">
		<table id="tt" style="width: 63%; height: 70%;">
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
	<div id="msgBox"></div> 
</body>
<script>
resizeFrame();
var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
	renderRow: function(target, fields, frozen, rowIndex, rowData){
		var cc = [];
		var imgUrl=rowData.cId+"/"+rowData.growStep+".png";
		var descript="名称:"+rowData.caption
		+"\n级别:"+rowData.cropLevel
		+"\n价格:"+rowData.price
		+"\n类别:"+rowData.type
		+"\n土地:"+rowData.landRequireCaption
		+"\n可收获季:"+rowData.harvestNum
		+"\n成熟时间:"+rowData.matureTime
		+"\n当季收获:"+rowData.output
		+"\n经验收获:"+rowData.exp
		+"\n单个果实可获金币:"+rowData.price4UnitSale;
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
						+'<button onclick="buy('+rowData.cId+',\''+rowData.caption+'\')" class="cardViewBottomButton">我要购买</button>'
					+'</div>'
				+'</div>';
		}
		cc.push(rs);
		return cc.join('');
	}
});
$('#tt').datagrid({
	title:"种子仓库",
	url: '<%=basePath%>seedStorage/gridData',
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
function buy(id,caption){
	var template="确认购买NULL的种子么?";
	template=template.replace("NULL",caption);
	$.messager.confirm('种子购买',template,function(r){
	    if (r){
	    	parent[2].buy(id,caption);
	    }
	});
}
function resizeFrame(){
	window.parent.document.getElementById("tools").src="seedBag/grid";
	window.parent.document.getElementById("framesets").rows='60,*,260';
}
</script>
</html>