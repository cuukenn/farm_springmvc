    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/green/easyui.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/easyui/themes/color.css">
        <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/easyui/plugins/jquery.edatagrid.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/easyui/locale/easyui-lang-zh_CN.js"></script>
        
        <script type="text/javascript" src="<%=basePath%>ext/farm/helper.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/farm/imgPosition.js"></script>
         <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/farm.css">
          <link rel="stylesheet" type="text/css" href="<%=basePath%>ext/farm/imgPosition.css">
        <title>欢迎首页</title>
        <style>
        body{
	        margin: 0px;
	        background-image:url(images/welcome.png);
	        background-size:100% 100%;
	        background-repeat:no-repeat;
	        background-color: transparent;
	        border:none;
	        width:100%;
	        height:100%;
        }
        #grid{
        	backgound-color:green;
        }
        table tbody tr>td:nth-child(3){
        	padding-left:120px;
        }
        </style>
        </head>
        <body>
        <div id="controlBox">
        <span style="color:white;">种子名称:</span>
        <input id="genderSearch" class="easyui-combobox" panelHeight="auto"
        data-options=" editable:false,
        valueField:'code',
        textField:'caption',
        url:'<%=basePath%>/codeGender/data'"
        >

        <a href="#" class="easyui-linkbutton c1" iconCls="icon-search"

        onclick="doSearch()">查询</a>

        <a href="#" class="easyui-linkbutton c2" iconCls="icon-add"

        onclick="javascript:newRecord()">添加</a>

        <a href="#" class="easyui-linkbutton c4" iconCls="icon-edit"

        onclick="javascript:grid.edatagrid('saveRow')">编辑</a>

        <a href="#" class="easyui-linkbutton c3" iconCls="icon-remove"

        onclick="javascript:grid.edatagrid('cancelRow')">取消</a>

        <a href="#" class="easyui-linkbutton c5" iconCls="icon-cancel"

        onclick="javascript:grid.edatagrid('destroyRow')">删除</a>
        </div>
         <div id="formContainer" class="easyui-dialog" style="width:800px;height:420px;padding:10px 10px" closed="true" buttons="#positionDialogButtons">
   		 	<form id="formEditor">
   		 		<table>
   		 			<tr>
   		 				<td>
   		 					ID:
   		 				</td>
   		 				<td>
   		 					<input name='ID' type="text"/>
   		 				</td>
   		 				<td>
   		 					种子ID:
   		 				</td>
   		 				<td>
   		 					<input name='cId' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					<label>种子名称</label>
   		 				</td>
   		 				<td>
   		 					<input name='caption' type="text"/>
   		 				</td>
   		 				<td>
   		 					X季作物:
   		 				</td>
   		 				<td>
   		 					<input name='harvestNum' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					种子等级:
   		 				</td>
   		 				<td>
   		 					<input name='cropLevel' type="text"/>
   		 				</td>
   		 				<td>
   		 					种子类型:
   		 				</td>
   		 				<td>
   		 					<input name='type' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					可获经验:
   		 				</td>
   		 				<td>
   		 					<input name='exp' type="text"/>
   		 				</td>
   		 				<td>
   		 					每季成熟所需时间:
   		 				</td>
   		 				<td>
   		 					<input name='matureTime' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每季成熟可获收:
   		 				</td>
   		 				<td>
   		 					<input name='output' type="text"/>
   		 				</td>
   		 				<td>
   		 					种子采购价:
   		 				</td>
   		 				<td>
   		 					<input name='price' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每个收获的果实:
   		 				</td>
   		 				<td>
   		 					<input name='price4UnitSale' type="text"/>
   		 				</td>
   		 				<td>
   		 					土地需求:
   		 				</td>
   		 				<td>
   		 					<input name='landRequirement' type="text"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每季成熟可获积分:
   		 				</td>
   		 				<td>
   		 					<input name='score' type="text"/>
   		 				</td>
   		 				<td>
   		 					提示信息:
   		 				</td>
   		 				<td>
   		 					<input name='tip' type="text"/>
   		 				</td>
   		 			</tr>
   		 		</table>
    		</div>
    	</div>
    	<div id="positionDialogButtons">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="gainPostion()">确定</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#formContainer').dialog('close')">取消</a>
    	</div> 
        <table id="grid"></table>
        <div id="msgBox"></div>
        <script>
        var grid;
        $(document).ready(function () {
        //配置表格
        grid = $('#grid').edatagrid({
        title: '种子清单',
        height: 600,
        method:'post',
        url: '<%=basePath%>/seed/gridData',
        saveUrl: '<%=basePath%>/seed/save',
        updateUrl: '<%=basePath%>/seed/save',
        destroyUrl: '<%=basePath%>/seed/delete',
        border: false,
        rownumbers: true,
        remoteSort: true,
        nowrap: false,
        singleSelect: true,
        fitColumns: true,
        pagination: true,
        striped: true,
        autoSave:true,
        idField: "ID",
        columns: [[
        {field: 'ID',title: 'ID' , width: 20, sortable: true,align:'center'},
        {title: '种子ID', field: 'cId', width: 30, sortable: true,align:'center'},
        {field: 'caption',title: '种子名称' , width: 30, sortable: true,align:'center'},

        {title: 'X季作物', field: 'harvestNum', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '种子等级', field: 'cropLevel', width: 50, sortable: true,align:'center',editor:{
	        type:'validatebox',
	        options: {
	        required:true
	        }
        }},
        {title: '种子类型', field: 'type', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},

        {title: '可获经验', field: 'exp', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '每季成熟所需时间', field: 'matureTime', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        },
        formatter:function(value,row){
        if(value==undefined)return "";
        return value+'秒';
        }},
        {title: '每季成熟可获收', field: 'output', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},

        {title: '种子采购价', field: 'price', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        },
        formatter:function(value,row){
        if(value==undefined)return "";
        return value+'金币';
        }},
        {title: '每个收获的果实', field: 'price4UnitSale', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '土地需求', field: 'landRequirement', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},

        {title: '每季成熟可获积分', field: 'score', width: 50, sortable: true,align:'center',
        editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '提示信息', field: 'tip', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '操作', field: 'ID', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
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
	        console.log(row);
	        $("#msgBox").text(row.msg);
        },
        onDestroy:function(index,row){
	        console.log(row);
	        $("#msgBox").text(row.msg);
        }
        });
        grid.datagrid("getPager").pagination({
	        pageSize: 5,
	        pageList: [5,10,15,20]
        });
        grid.datagrid("resize",{
	        height:($(window).height()-36)
	        });
        });
        function doSearch(){
	        grid.datagrid("load",{
	        gender: $("#genderSearch").val()
        	})
	    };
        
        function editRecord(){
            var row = grid.datagrid('getSelected');
            if (row){

                $('#formContainer').dialog('open').dialog('center').dialog('setTitle','编辑数据');
                $('#formEditor').form('load',row);
            } else {
                $.messager.show({
                    title: "消息",
                    msg: "请先选择一行数据，然后再尝试点击操作按钮！"
                });
            }
    }   
    
    function newRecord(){
        $('#formContainer').dialog('open').dialog('center').dialog('setTitle','添加数据');     
        $('#formEditor').form('clear');
        $('#formEditor').find("input[name='id']").val(0);
    }
    
    function saveRecord() {
        $('#formEditor').form('submit', {
            url: '<%=basePath%>/seed/save',
            onSubmit: function (param) {
            return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.code == 0) {
                    $('#formContainer').dialog('close');
                    grid.datagrid('reload');
                }
                $.messager.show({
                    title: "消息",
                    msg: result.msg
                });
            }
        	})
        };
        </script>
        </body>
        </html>