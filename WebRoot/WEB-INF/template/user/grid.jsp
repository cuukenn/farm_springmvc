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
</style>
<body>
    <div id="windowContainer" class="easyui-window" title="用户管理" style="width:100%;height:100%;" closed="false" buttons="#windowContainerButtons">
		 <div id="controlBox" style="background-color:green;">
        	<span style="color:white;">用户昵称:</span>
        	<input id="genderSearch"  type="text"/>
        	<a href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-search" onclick="doSearch()">查询</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-add" onclick="javascript:grid.edatagrid('addRow')">添加</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:grid.edatagrid('cancelRow')">取消</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton c5" iconCls="icon-cancel" onclick="javascript:deleteRecord()">删除</a>
         <div id="formContainer" class="easyui-dialog" style="width:460px;height:150px;padding:10px 10px" closed="true" buttons="#formContainerButtons">
   		 	<form id="formEditor" enctype="multipart/form-data" method="post">
   		 		<table>
   		 			<tr>  
                       <td> 上传头像:</td>  
                       <td>  
                           <input type="file" id="fuImportMultipleShipmentStatus" name="filePathName" />  
                       </td>
                   </tr> 
   		 		</table>
    		</form>
    	</div>
    	<div id="formContainerButtons">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="ImportShipmentStatusList()">开始上传</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#formContainer').dialog('close')">取消</a>
    	</div> 
        <table id="grid"></table>
        <div id="msgBox"></div> 
        <div id="windowContainerButtons" style="position:absolute;bottom:0px;right:30px">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#windowContainer').dialog('close')">关闭窗口</a>
   		</div>
    </div>
    
</body>
<script>
        var indexGloble;
        var rowGloble;
        var grid;
        var cId;
        $(document).ready(function () {
        //配置表格
        grid = $('#grid').edatagrid({
        title: '用户清单',
        height: '460px',
        method:'post',
        url: '<%=basePath%>user/gridData',
        saveUrl: '<%=basePath%>user/save',
        updateUrl: '<%=basePath%>user/save',
        destroyUrl: '<%=basePath%>user/delete',
        border: false,
        rownumbers: true,
        remoteSort: true,
        nowrap: false,
        singleSelect: true,
        fitColumns: true,
        striped: true,
        pagination: true,
        autoSave:true,
        idField: "id",
        columns: [[
        {title: 'id', field: 'id', width: 50, sortable: true,align:'center'},
        {title: '头像', field: 'heads', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'validatebox',
		        options: {
		        required:true
		        }
        	},
	    	formatter:function(value,row){
	    		return '<img style="height:30px;" src="<%=basePath%>images/headImages/'+value+'"/>';
			}
        },
        {title: '用户名', field: 'username', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'validatebox',
		        options: {
		        	required:true
		        }
        	}
        },
        {title: '用户昵称', field: 'nickname', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'validatebox',
		        options: {
		        required:true
		        }
        	}
        },
        {title: '经验值', field: 'exp', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'numberbox',
		        options: {
		        	required:true
		        }
        	},
	    	formatter:function(value,row){
	    		return '<img style="height:30px;"  src="<%=basePath%>images/exp.png">'+value;
			}
        },
        {title: '积分', field: 'score', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'numberbox',
		        options: {
		        required:true
		        }
        	},
	    	formatter:function(value,row){
	    		return '<img style="height:30px;"  src="<%=basePath%>images/积分.png">'+value;
			}
        },
        {title: '金币', field: 'price', width: 50, sortable: true,align:'center',
        	editor:{
		        type:'numberbox',
		        options: {
		        required:true
		        }
        	},
	    	formatter:function(value,row){
	    		return '<img style="height:30px;"  src="<%=basePath%>images/金币.png">'+value;
			}
        },
        {title: '操作', field: 'option', width: 50,align:'center',
        	formatter:function(value,row,index){
                return  '<a href="javascript:void(0)" style="background-color:white;border-radius:5px;"  class="easyui-linkbutton" onclick="javascript:showFormEdit('+index+')">上传头像</a>'
                		+'<a href="javascript:void(0)" style="background-color:white;border-radius:5px;margin-left:30px;"  class="easyui-linkbutton" onclick="javascript:grid.edatagrid(\'saveRow\')">保存数据</a>'
               }
        }
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
        onBeforeEdit:function(rowIndex, rowData){
        	if(rowData&&rowData.isNewRecord)rowData.id=0;
        },
        onSuccess:function(index,row){
	        $.messager.show({
                title: "消息",
                msg: row.msg
            });
	        grid.datagrid('reload');
        }
        });
        grid.datagrid("getPager").pagination({
	        pageSize: 5,
	        pageList: [5,10,15,20]
        });
        });
        function doSearch(){
	        grid.datagrid("load",{
	        nickname: $("#genderSearch").val()
        	})
	    };   
        function deleteRecord() {
        	var row = grid.datagrid('getSelected');
        	$.post('<%=basePath%>user/delete',row,function(data){
        		 $.messager.show({
                     title: "消息",
                     msg: data.msg
                 });
        	});
        	grid.datagrid('reload');
        };
        function showFormEdit(index,row){
        	indexGloble=index;
        	$('#formContainer').dialog('open').dialog('center').dialog('setTitle','上传头像');
    	}
        function ImportShipmentStatusList() {
        	var row = grid.datagrid('getSelected');
        	var rowIndex=grid.datagrid('getRowIndex',row);
            if ($("#fuImportMultipleShipmentStatus").val() == "" ) { 
                $.messager.show({
                    title: "消息",
                    msg: "请至少选择一个需要上传的文件"
                });
                return;  
            }  
            var images=$("#fuImportMultipleShipmentStatus").val().split('\\');
            var imageName=images[images.length-1];
            $('#formEditor').form('submit', {
                url: '<%=basePath%>file/saveHeadImg',
                success: function (result) {
                    var result = eval('(' + result + ')');
                    if (result.code == 0) {
                        $('#formContainer').dialog('close');
                    }
                    $.messager.show({
                        title: "消息",
                        msg: result.msg
                    });
                    $('#grid').edatagrid('beginEdit',rowIndex);
                    var edt=$('#grid').edatagrid('getEditor',{
                    		index:rowIndex,
                    		field:'heads'
                    	});
                    $(edt.target).val(imageName);
                    $('#grid').edatagrid('endEdit',rowIndex);
                    }
                })
        }
</script>
</html>