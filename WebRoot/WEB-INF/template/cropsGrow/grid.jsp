<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/imgPosition.css?t=0901">
    <script type="text/javascript" src="<%=basePath%>ext/farm/imgPosition.js"></script> 
    
    <style>
    	#cropsGrowEditor tr td:nth-child(3){
    		padding-left:160px;
    	}
	</style>
	<div id="controlBox1" style="display:none;">
        <a href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-add" onclick="javascript:newRecord1()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-edit" onclick="javascript:editRecord1()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:cgrid.edatagrid('cancelRow')">取消</a>
        <a href="javascript:void(0)" class="easyui-linkbutton c5" iconCls="icon-cancel" onclick="javascript:deleteRecord1()">删除</a>
    </div> 
    <div id="codeCropContainer" class="easyui-dialog" style="width:800px;height:420px;padding:10px 10px;display:none;" closed="true" buttons="#codeCropContainerButtons">
   		 	<form id="cropsGrowEditor">
   		 		<table>
   		 			<tr>
   		 				<td>
   		 					ID:
   		 				</td>
   		 				<td>
   		 					<input type="text" name='id'   value='0'  class="easyui-textbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					种子ID:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='cId'  class="easyui-numberbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					<label>生长阶段:</label>
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='growStep'  class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					生长阶段标题:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='growCaption'  class="easyui-textbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					阶段生长时间:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='growTime'  class="easyui-numberbox" required="required"data-options="precision:0,min:1"/>
   		 				</td>
   		 				<td>
   		 					生虫概率:
   		 				</td>
   		 				<td>
	   		 				<input type="text"  name='insect'  class="easyui-numberbox" required="required"data-options="precision:1"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					图片宽度:
   		 				</td>
   		 				<td>
   		 					<input type="text"  imgD='width' name='width'  class="easyui-numberbox" required="required"data-options="editable:false"/>
   		 				</td>
   		 				<td>
   		 					图片高度:
   		 				</td>
   		 				<td>
   		 					<input type="text"  imgD='height' name='height'  class="easyui-numberbox" required="required" data-options="editable:false"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					图片offsetX:
   		 				</td>
   		 				<td>
   		 					<input type="text"  imgD='offsetX'  name="offsetX"  class="easyui-numberbox" required="required"data-options="editable:false"/>
   		 				</td>
   		 				<td>
   		 					图片offsetY:
   		 				</td>
   		 				<td>
   		 					<input type="text"  imgD='offsetY' name="offsetY" class="easyui-numberbox" required="required" data-options="editable:false"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					作物状态:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='status' class="easyui-combobox" panelHeight="auto"
						        data-options=" editable:false,
						        valueField:'code',
						        textField:'caption',
						        url:'<%=basePath%>codeCropStatus/data' " required="required"/>
   		 				</td>
   		 				<td>
   		 					
   		 				</td>
   		 				<td>
   		 					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:showImgEditor()">编辑图片位置</a>
   		 				</td>
   		 			</tr>
   		 		</table>
    		</form>
    </div>
    <div id="codeCropContainerButtons" style="display:none;">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveRecord1()">确定</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#codeCropContainer').dialog('close')">取消</a>
    </div> 
    <div id="positionDialog" class="easyui-dialog" style="width:240px;height:420px;padding:10px 10px;display:none;" closed="true" buttons="#positionDialogButtons">
    			<div id="tools-imagePositioner-display" class=" tools-imagePositioner-display">
    				 <img class="easyui-draggable easyui-resizable" data-options="onDrag:imagePositioneronDrag"  src="">
    			 </div>
    	</div>
	<div id="positionDialogButtons" style="display:none;">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="gainPostion()">确定</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#positionDialog').dialog('close')">取消</a>
    	</div>  
    <table id="grid1"></table>
    <script type="text/javascript">
 	var codeCropStatusData=[];
 	var cgrid;
 	getCodeCropStatus();
 	resizeFrame();
 	$(function(){
		cgrid = $('#grid1').edatagrid({
	    title: '成长阶段定义',
	    height: '420',
	    method:'post',
	    url: '<%=basePath%>cropsGrow/gridData/'+cId,
	    saveUrl: '<%=basePath%>cropsGrow/save',
	    updateUrl: '<%=basePath%>cropsGrow/save',
	    border: false,
	    rownumbers: true,
	    remoteSort: true,
	    nowrap: false,
	    singleSelect: true,
	    fitColumns: true,
	    pagination: false,
	    striped: true,
	    autoSave:true,
	    idField: "ID",
    	columns: [[
			    {field: 'id',title: 'ID' , width: 20, sortable: true,align:'center'},
			    {title: '种子ID', field: 'cId', width: 30, sortable: true,align:'center'},
			    {field: 'growStep',title: '生长阶段' , width: 30, sortable: true,align:'center'},
	
	    		{title: '生长阶段标题', field: 'growCaption', width: 50, sortable: true,align:'center',editor:{
				    type:'validatebox',
				    options: {
				    required:true
				    }
	    		}},
	    		{title: '阶段生长时间', field: 'growTime', width: 50, sortable: true,align:'center',editor:{
			        type:'numberbox',
			        options: {
			        required:true
			        }
			    }},
			    {title: '生虫概率', field: 'insect', width: 50, sortable: true,align:'center',editor:{
				    type:'numberbox',
				    options: {
				    required:true
				    }
			    }},
	
			    {title: '图片宽度', field: 'width', width: 50, sortable: true,align:'center',editor:{
				    type:'numberbox',
				    options: {
				    required:true
				    }
			    },
			    formatter:function(value,row){
				    if(value==undefined)return "";
				    return value+'px';
		    	}},
			    {title: '图片高度', field: 'height', width: 50, sortable: true,align:'center',editor:{
				    type:'numberbox',
				    options: {
				    required:true
				    }
	    			},
				    formatter:function(value,row){
					    if(value==undefined)return "";
					    return value+'px';
			    }},
			    {title: '图片offsetX', field: 'offsetX', width: 50, sortable: true,align:'center',editor:{
				    type:'numberbox',
				    options: {
				    required:true
				    }
			    },
			    formatter:function(value,row){
				    if(value==undefined)return "";
				    return value+'px';
		   		 }},
	
			    {title: '图片offsetY', field: 'offsetY', width: 50, sortable: true,align:'center',editor:{
			        type:'numberbox',
			        options: {
			        required:true}
			    },
				    formatter:function(value,row){
					    if(value==undefined)return "";
					    return value+'px';
			    }},
			    {title: '作物状态', field: 'status', width: 50, sortable: true,align:'center',editor:{
				    type:'numberbox',
				    options: {
				    required:true
				    }},
				    formatter:function(value,row){
				    	var rs="";
				        for(var index in codeCropStatusData){
				        	if(codeCropStatusData[index].code==value){
				        		rs=codeCropStatusData[index].caption;
				        		break;
				        	}
				        }
				        return rs;
				}}
		    ]],
		destroyMsg:{
		    norecord:{
		        title:'警告',
		        msg:'首先需要选中记录，然后在点击删除按钮'
		        },
		        confirm:{
			        title:'确认',
			        msg:'是否删除选中记录?'
		        }
		},
		onSuccess:function(index,row){
		    $.messager.show({
                title: "消息",
                msg: row.msg
            });
		},
		onDblClickRow:function (rowIndex, rowData){
			cgrid.datagrid("endEdit", rowIndex);
    	}
		});
		$("#controlBox1").css("display","block");
		$("#codeCropContainer").css("display","block");
		$("#codeCropContainerButtons").css("display","block");
		$("#positionDialog").css("display","block");
		$("#positionDialogButtons").css("display","block");
 	});
	function editRecord1(){
        var row = cgrid.datagrid('getSelected');
        if (row){
        	$('#codeCropContainer').dialog('open').dialog('center').dialog('setTitle','编辑数据');
            $('#cropsGrowEditor').form('load',row);
        } else {
            $.messager.show({
                title: "消息",
                msg: "请先选择一行数据，然后再尝试点击操作按钮！"
            });
        }
	};
	function deleteRecord1() {
    	var row = cgrid.datagrid('getSelected');
    	$.post( '<%=basePath%>cropsGrow/delete',row,function(data){
    		$.messager.show({
                title: "消息",
                msg: data.msg
            });
    	});
    	cgrid.datagrid('reload');
    };
	function newRecord1(){
		 $('#cropsGrowEditor').form("reset");
		 
        $('#codeCropContainer').dialog('open').dialog('center').dialog('setTitle','添加数据');
    };
    function saveRecord1() {
        $('#cropsGrowEditor').form('submit', {
            url: '<%=basePath%>cropsGrow/save',
            onSubmit: function (param) {
            return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.code == 0) {
                    $('#codeCropContainer').dialog('close');
                    cgrid.datagrid('reload');
                }
                $.messager.show({
                    title: "消息",
                    msg: result.msg
                });
            }
        	})
	};
	function showImgEditor(){
		var url='<%=basePath%>images/crops/'+getImageName();
		$("#tools-imagePositioner-display img").attr("src",url);
		
		imgExtData.offsetX = $('#cropsGrowEditor input[imgD="offsetX"]').textbox("getValue");
		imgExtData.offsetY =$('#cropsGrowEditor input[imgD="offsetY"]').textbox("getValue");
		imgExtData.width = $('#cropsGrowEditor input[imgD="width"]').textbox("getValue");
		imgExtData.height = $('#cropsGrowEditor input[imgD="height"]').textbox("getValue");
		positionerLoadImage();

    	$('#positionDialog').dialog('open').dialog('center').dialog('setTitle','定位编辑器');
    };
    function getImageName(){
    	var growStep=$("#cropsGrowEditor").find("input[name='growStep']").val();
    	var status=$("#cropsGrowEditor").find("input[name='status']").val();
    	var cId=$("#cropsGrowEditor").find("input[name='cId']").val();
    	var result="";
    	if(status==1){
    		result="basic/0.png";	
    	}else if(status==4){
    		result="basic/9.png"
    	}else{
    		result=cId+"/"+growStep+".png";
    	}
    	return result;
    };
    function gainPostion(){
		
    	var imgWidth=$('#cropsGrowEditor input[imgD="width"]');
    	var imgHeight=$('#cropsGrowEditor input[imgD="height"]');
    	var imgOffsetX=$('#cropsGrowEditor input[imgD="offsetX"]');
    	var imgOffsetY=$('#cropsGrowEditor input[imgD="offsetY"]');
    	
    	var imgDrag=$("#tools-imagePositioner-display img");
    	imgOffsetX.textbox("setValue",parseInt(imgDrag.css("left")));
    	imgOffsetY.textbox("setValue", parseInt(imgDrag.css("top")));
    	imgWidth.textbox("setValue",parseInt(imgDrag.css("width")));
    	imgHeight.textbox("setValue",parseInt(imgDrag.css("height")));
    	$('#positionDialog').dialog('close');
    }
    function getCodeCropStatus(){
    	$.post("<%=basePath%>codeCropStatus/data", function(data){
    		codeCropStatusData=data;
            });
    }
    function resizeFrame(){
    	window.parent.document.getElementById("tools").src="tools.jsp";
    	window.parent.document.getElementById("framesets").rows='60,*,50';
    }
</script>
 