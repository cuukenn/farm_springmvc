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
.tools-imagePositioner-display:hover{
	-ms-transform:scale(1.2); /* IE 9 */
	-moz-transform:scale(1.2); /* Firefox */
	-webkit-transform:scale(1.2); /* Safari and Chrome */
	-o-transform:scale(1.2); /* Opera */
	transform:scale(1.2);
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
	width:40px;
	height:auto;
	left:40px;
	top:10px;
	display:none;
}
.tran{
	transform-style: preserve-3d;
    transform-origin:50% 50%;
    transform: perspective(8000px) rotateX(50deg) rotateZ(-5deg);
}

</style>
<body>
	<audio id="audio" src="" style="visibility:hidden;"></audio>
	<div class="content">
		<div class="tran">
			<div class="farm"></div>
		</div>
	</div>
</body>
<script>
	let cIdGlobal=-1;
	let landGlobal;
	const cur=new Array( "url(<%=basePath%>images/cursors/bozhong.cur),default ",
			"url(<%=basePath%>images/cursors/dengdai.cur),default",
			"url(<%=basePath%>images/cursors/chuchong.cur),default",
			"url(<%=basePath%>images/cursors/shouhuo.cur),default",
			"url(<%=basePath%>images/cursors/chukucao.cur),default");

	const landImg = new Array("land1.png", "land2.png", "land3.png",
			"land4.png")
	const landAudio=new Array(
			"<%=basePath%>audios/收获.mp3",
			"<%=basePath%>audios/虫子吃菜.mp3",
			"<%=basePath%>audios/收获.mp3",
			"<%=basePath%>audios/除枯草.mp3"
			);//种植 收获 除虫 铲土
	$(function() {
		resizeFrame();
		getLandData();
		initWebSocket();
	})

	function resizeFrame() {
		window.parent.document.getElementById("tools").src = "tools.jsp";
		window.parent.document.getElementById("framesets").rows = '60,*,50';
	}
	const cols = 6;
	const rows = 4;

	let temlate = '<div onclick="showSelectSeed(idslot)" class="tools-imagePositioner-display" style="cursor:url(<%=basePath%>images/cursors/bozhong.cur),default;">'
				+'<img class="land" src="landslot" type="typeid" alt="">'//土地图片
				+'<img class="crop" src="" alt="">'//作物图片
				+'<img class="insect" src="<%=basePath%>images/insect.png" alt="">'//虫图片
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
			rs += temlate.replace('idslot', i+1).replace('landslot','<%=basePath%>images/lands/'+landImg[tmp]).replace('typeid',tmp);
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
		drawSeed(data);
	}
	function drawSeed(data){
		//画作物
		if(data===undefined){
	       	$.messager.show({
                title: "消息",
                msg: "读取信息错误"
            });
	       	return
		}
		for(let index=0,len=data.length;index<len;index++){
			let elmS = '.farm>div:nth-child(' +data[index].landId+ ')';
		
			let elm =$(elmS);
			console.log(data[index].id==0)
			if(data[index].id==0){
				elm.find('.crop').attr('src',"");
				elm.find('.insect').css('display','none');
				elm.css("cursor",cur[0]);
				elm.attr('onclick','showSelectSeed('+data[index].landId+')');
			}
			

			//作物
			let crop=elm.find('.crop');
			crop.attr('src','<%=basePath%>images/crops/'+data[index].imgUrl);
			crop.css({
				"left":data[index].offsetX,
				"top":(data[index].offsetY-200),
				"width":data[index].width,
				"height":data[index].height
			})
			let title='\n\n\n名称:'+data[index].caption
					+'\n状态:'+data[index].growCaption
					+'\n产量:'+data[index].curHarvestNum
					+'\n时间:'+data[index].curCropsEndTime;
			crop.attr('title',title);
			
			if(data[index].worm==0) {
				elm.find('.insect').css('display','none');
			}
			
			if(data[index].worm!=0){
				elm.find('.insect').css('display','block');
				elm.css("cursor",cur[2]);
				elm.attr('onclick','killWormAction('+data[index].landId+')');
			}

			//更改光标
			else if(data[index].cropsCaption=="成熟阶段"){
				elm.css("cursor",cur[3]);
				elm.attr('onclick','harvestAction('+data[index].landId+')');
			}
			else if(data[index].cropsCaption=="枯草阶段"){
				elm.css("cursor",cur[4]);
				elm.attr('onclick','cleanLandAction('+data[index].landId+')');
			}
			else {
				elm.css("cursor",cur[1]);
				elm.attr('onclick','javascript:void()');
			}
			

		}
	}
	//显示种子袋
	function showSelectSeed(id) {
		landGlobal=id;
        var content = '<iframe src="' + '<%=basePath%>seedBag/grid' + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';  
        var boarddiv = '<div id="msgwindow" style="width:100%"></div>'
        $(document.body).append(boarddiv);  
        win = $('#msgwindow').window({  
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
	
	const actionPlantUrl='<%=basePath%>action/plant';
	const actionKillWormUrl='<%=basePath%>action/killWorm';
	const actionHarvestUrl='<%=basePath%>action/harvest';
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
    let action =-1;
    function doSend(websocket,object,method,methodURL,successFunction,action) {  
    	action=action;
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
		if(result.code==0){
			let audio=$('#audio');
			audio.attr('src',landAudio[action]);
			audio.oncanplay=function(){
				audio.play();
			}
		}
	}
	
	function onMessage(eve){
		let data=JSON.parse(eve.data);
		console.log(data);
		drawSeed(data);
	}
    
    function plantAction(cId){  
    	let obj={landId:landGlobal,cId:cId};
    	console.log(obj)
    	console.log("plant")
    	doSend(socket,obj,'POST',actionPlantUrl,callBack,0);
    }
    function killWormAction(landId){
    	let obj={landId:landId};
    	console.log(obj)
    	console.log("killWorm")
    	doSend(socket,obj,'POST',actionKillWormUrl,callBack,1);
    }
    function harvestAction(landId){
    	let obj={landId:landId};
    	console.log(obj)
    	console.log("harvest")
    	doSend(socket,obj,'POST',actionHarvestUrl,callBack,2);
    }
    function cleanLandAction(landId){
    	let obj={landId:landId};
    	console.log(obj)
    	console.log(" cleanLand")
    	doSend(socket,obj,'POST',actionCleanLandUrl,callBack,3);
    }
	
    window.close = function () { 
    	socket.close();
    } 
</script>
</html>