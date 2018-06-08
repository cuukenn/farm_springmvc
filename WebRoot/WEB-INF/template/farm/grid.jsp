<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsBasePath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js"></script>
    <script type="text/javascript" src="<%=basePath%>ext/farm/sockjs.js"></script> 
</head>
<style>
*{
	padding:0px;
	margin:0px;
	border:none;
}
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
	border: none
}

.content{
	width:100%;
	height:100%;
	overflow:scroll;
}

.farm {
	position: relative;
	left: 0px;
	top: 0px;
}

.tools-imagePositioner-display {
	position: absolute;
	width: 200px;
	height: 101px;
}

.tools-imagePositioner-display>img:nth-child(1){
	position: absolute;
	width: 200px;
	height: 101px;
}
.tools-imagePositioner-display>img:nth-child(2){
	position: absolute;	
}
.insect{
	position: absolute;	
	width:20px;
	height:20px;
	left:10px;
	top:10px;
	display:none;
}
.tran{
	transform-style: preserve-3d;
    transform-origin:50% 50%;
    transform: perspective(8000px) rotateX(50deg) rotateZ(-5deg);
}

.bozhong{
	cursor:url('<%=basePath%>images/cursors/bozhong.cur'),default                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ;
}
.chuchong{
	cursor:url('<%=basePath%>images/cursors/chuchong.cur'),default;
}
.chukucao{
	cursor:url('<%=basePath%>images/cursors/chukucao.cur'),default;
}
.dengdai{
	cursor:url('<%=basePath%>images/cursors/dengdai.cur'),default;
}
.shouhuo{
	cursor:url('<%=basePath%>images/cursors/shouhuo.cur'),default;
}

</style>
<body>
	<div class="content">
		<div class="tran">
			<div class="farm"></div>
		</div>
	</div>
</body>
<script>
	let cIdGlobal=-1;
	const cur=new Array("bozhong","chuchong","chukucao","dengdai","shouhuo");
	const landImg=new Array("land1.png","land2.png","land3.png","land4.png")
	$(function(){
		resizeFrame();
		getLandData();
		initWebSocket();
	})
	
	function resizeFrame() {
		window.parent.document.getElementById("tools").src = "tools.jsp";
		window.parent.document.getElementById("framesets").rows = '60,*,50';
	}
	const cols=6;
	const rows=4;

	let temlate='<div onclick="showSelectSeed(idslot)" class="tools-imagePositioner-display bozhong">'
				+'<img class="land" src="landslot" type="typeid" alt="">'//土地图片
				+'<img class="crop" src="" alt="">'//作物图片
				+'<img class="insect" src="<%=basePath%>images/user.png" alt="">'//虫图片
				+'</div>';

	let offsetX=0;
	let offsetY=0;

	const widF=180;
	const hei=60;
	const rowsHei=80;

	const offTmp=440;
	const offTmpDe=140;

	//初始化地
	function drawLand(data) {
		let rs = ""
		for (let i = 0, total = cols * rows; i < total; i++) {
			let tmp=~~(i/cols);
			rs += temlate.replace('idslot', i).replace('landslot','<%=basePath%>images/lands/'+landImg[tmp]).replace('typeid',tmp);
		}
		let farm = document.querySelector('.farm');
		farm.innerHTML = rs;
		farm.style.height = rows * hei + 300 + 'px';
		farm.style.width = cols * widF + 200 + 'px';
		
		//画土地
		for (let row = 0; row < rows; row++) {
			for (let index = 1; index <= cols; index++) {
				let elmS = '.farm>div:nth-child(' + (cols * row + index) + ')';
				let elm = document.querySelector(elmS);
				offsetX = (index - 1) * widF + offTmp - row * offTmpDe;
				offsetY = (index - 1) * hei + row * rowsHei;
				if (index === 1) {
					offsetX = 0 + offTmp - row * offTmpDe;
					offsetY = 0 + row * rowsHei;
				}
				elm.style.left = offsetX + 'px';
				elm.style.top = offsetY + 'px';
			}
		}
		//画作物
		if(data==undefined){
	       	$.messager.show({
                title: "消息",
                msg: "初始化失败，请刷新页面"
            });
		}
		for(let index=0,len=data.length;index<len;index++){
			let elmS = '.farm>div:nth-child(' +data[index].landId+ ')';
			let elm =$(elmS);
			//作物
			let crop=elm.find('.crop');
			crop.attr('src','<%=basePath%>images/crops/'+data[index].imgUrl);
			crop.css({
				"left":data[index].offsetX,
				"top":(data[index].offsetY-200),
				"width":data[index].width,
				"height":data[index].height
			})
			let title='名称:'+data[index].caption
					+'状态:'+data[index].growCaption
					+'产量:'+data[index].curHarvestNum
					+'时间:'+data[index].plantTime;
			crop.attr('title',title);
			if(data[index].worm!=0){
				let crop=elm.find('.insect').css('display','block');
			}
		}
	}
	
	//显示种子袋
	function showSelectSeed(id) {
		cIdGlobal=-1;
        var content = '<iframe src="' + '<%=basePath%>seedBag/grid' + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';  
        var boarddiv = '<div id="msgwindow" style="width:100%"></div>'
        $(document.body).append(boarddiv);  
        var win = $('#msgwindow').window({  
            content: content,
            height: 400, 
            title:'种子收纳袋',
            modal: true,  
            onClose: function () {  
                $(this).dialog('destroy');//后面可以关闭后的事件  
            }  
        });  
        win.dialog('open');
	}
	
	//获取种植信息
	function getLandData() {
		let url = '<%=basePath%>land/gridViewData';
		getRemoteData(url, function(data) {
			drawLand(data);
		});
	}
	
	const farmSocketUrl='<%=wsBasePath%>farm/action';
	
	const actionPlantUrl='<%=basePath%>actionkillWorm';
	const actionKillWormUrl='<%=basePath%>actionkillWorm';
	const actionHarvestUrl='<%=basePath%>actionharvest';
	const actionCleanLandUrl='<%=basePath%>action/cleanLand';
	
	let socket=initWebSocket(farmSocketUrl,onOpen,onMessage,onError,onClose);
	
	function initWebSocket(url,onOpen,onMessage,onError,onClose){
		let websocket;
	    if ('WebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new WebSocket(url);//WebSocket对应的地址  
	    }  
	    else if ('MozWebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new MozWebSocket(url);//SockJS对应的地址  
	    }  
	    else {  
	        //SockJS的连接  
	        websocket = new SockJS(url);//SockJS对应的地址    
	    }  
	    websocket.onopen = onOpen;  
	    websocket.onmessage = onMessage;  
	    websocket.onerror = onError;  
	    websocket.onclose = onClose;
	    return websocket;
    }
    function onOpen(evt) {  
        console.log("连接打开：",evt);  
    } 
    function onError(evt) {  
    	console.log("出现错误：",evt);
    }  
    function onClose(evt) {  
    	console.log("连接关闭：",evt);
    }  
    function doSend(websocket,object,method,methodURL,successFunction) {  
        if (websocket.readyState == websocket.OPEN) {  
            request(object,method,methodURL,successFunction);
            console.log("发送成功!");  
        } else {  
        	console.log("连接失败!");  
        }  
    }
	function callBack(result) {
		$.messager.show({
			title : "消息",
			msg : "<center>" + result.msg + "</center>"
		});
	}
	
	function onMessage(eve){
		if(eve==undefined)return;
		let elmS = '.farm>div:nth-child(' +eve.landId+ ')';
		let elm =$(elmS);
		
		
		if(eve==="NOLAND"){
			elm.find('.crop').css('display','none');
			elm.find('.insect').css('display','none');
		}
		//作物
		let crop=elm.find('.crop');
		crop.attr('src','imgUrl');
		crop.css({
			"left":data[index].offsetX,
			"top":data[index].offsetY
		})
		let title='名称:'+data[index].caption
				+'状态:'+data[index].growCaption
				+'产量:'+data[index].curHarvestNum
				+'时间:'+data[index].plantTime;
		crop.attr('title',title);
		
		//虫
		if(data[index].worm!=0){
			let crop=elm.find('.insect').css('display','block');
		}
	}
    
    function plantAction(landId,cId){                                                                                                                                                                                                                                
    	let obj={landId:landId,cId:cId};
    	doSend(socket,obj,'POST',actionPlantUrl,plantUpdate);
    }
    function killWormAction(landId){
    	let obj={landId:landId};
    	doSend(socket,obj,'POST',actionKillWormUrl,killWormUpdate);
    }
    function harvestAction(landId){
    	let obj={landId:landId};
    	doSend(socket,obj,'POST',actionHarvestUrl,harvestUpdate);
    }
    function cleanLandAction(landId){
    	let obj={landId:landId};
    	doSend(socket,obj,'POST',actionCleanLandUrl,cleanLandUpdate);
    }
	
    window.close = function () { 
    	socket.close();
    } 
</script>
</html>