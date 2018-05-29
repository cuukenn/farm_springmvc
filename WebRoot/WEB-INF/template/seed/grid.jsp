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
<body>
        <div id="controlBox" style="background-color:green;">
        	<span style="color:white;">种子名称:</span>
        	<input id="genderSearch"  type="text"/>

        	<a href="javascript:void(0)" class="easyui-linkbutton c1" iconCls="icon-search" onclick="doSearch()">查询</a>

        	<a href="javascript:void(0)" class="easyui-linkbutton c2" iconCls="icon-add" onclick="javascript:newRecord()">添加</a>

        	<a href="javascript:void(0)" class="easyui-linkbutton c4" iconCls="icon-edit" onclick="javascript:editRecord()">编辑</a>

        	<a href="javascript:void(0)" class="easyui-linkbutton c3" iconCls="icon-remove" onclick="javascript:grid.edatagrid('cancelRow')">取消</a>

        	<a href="javascript:void(0)" class="easyui-linkbutton c5" iconCls="icon-cancel" onclick="javascript:deleteRecord()">删除</a>
        </div>
         <div id="formContainer" class="easyui-dialog" style="width:800px;height:420px;padding:10px 10px" closed="true" buttons="#formContainerButtons">
   		 	<form id="formEditor">
   		 		<table>
   		 			<tr>
   		 				<td>
   		 					ID:
   		 				</td>
   		 				<td>
   		 					<inpu type="text"  name='id'   value='0'   class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					种子ID:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='cId'   class="easyui-numberbox " data-options="precision:0,min:1"  required="required"></td>
          			  </tr>
   		 			<tr>
   		 				<td>
   		 					<label>种子名称</label>
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='caption'     class="easyui-textbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					X季作物:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='harvestNum'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					种子等级:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='cropLevel'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					种子类型:
   		 				</td>
   		 				<td>
	   		 				<input type="text"  name='type' class="easyui-combobox" panelHeight="auto"
						        data-options=" editable:false,
						        valueField:'code',
						        textField:'caption',
						        url:'<%=basePath%>codeSeedType/data'"  required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					可获经验:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='exp'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					每季成熟所需时间:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='matureTime'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每季成熟可获收:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='output'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					种子采购价:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='price'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每个收获的果实:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='price4UnitSale'  class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					土地需求:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='landRequirement' class="easyui-combobox" panelHeight="auto"
						        data-options=" editable:false,
						        valueField:'code',
						        textField:'caption',
						        url:'<%=basePath%>codeLandRequire/data'"  class="easyui-textbox" required="required"/>
   		 				</td>
   		 				</td>
   		 			</tr>
   		 			<tr>
   		 				<td>
   		 					每季成熟可获积分:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='score'    class="easyui-numberbox" required="required"/>
   		 				</td>
   		 				<td>
   		 					提示信息:
   		 				</td>
   		 				<td>
   		 					<input type="text"  name='tip'    class="easyui-textbox" required="required"/>
   		 				</td>
   		 			</tr>
   		 		</table>
    		</form>
    	</div>
    	<div id="formContainerButtons">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveRecord()">确定</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#formContainer').dialog('close')">取消</a>
    	</div> 
        <table id="grid"></table>
        <div id="msgBox"></div> 
        <div id="cropGrow"  style="overflow-y:hidden!important;"></div> 
        <script>
        var grid;
        var cId;
        var codeLandRequire;
        resizeFrame();
        $(document).ready(function () {
        getCodeLandRequire();
        //配置表格
        grid = $('#grid').edatagrid({
        title: '种子清单',
        height: 600,
        method:'post',
        url: '<%=basePath%>seed/gridData',
        saveUrl: '<%=basePath%>seed/save',
        updateUrl: '<%=basePath%>seed/save',
        destroyUrl: '<%=basePath%>seed/delete',
        border: false,
        rownumbers: true,
        remoteSort: true,
        nowrap: false,
        singleSelect: true,
        fitColumns: true,
        striped: true,
        pagination: true,
        autoSave:true,
        idField: "ID",
        columns: [[
        {field: 'id',title: 'ID' , width: 20, sortable: true,align:'center'},
        {title: '种子ID', field: 'cId', width: 30, sortable: true,align:'center'},
        {field: 'caption',title: '种子名称' , width: 30, sortable: true,align:'center'},

        {title: 'X季作物', field: 'harvestNum', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
        options: {
        required:true
        }
        },
	    formatter:function(value,row){
	    	return value+"季作物";
		}},
        {title: '种子等级', field: 'cropLevel', width: 50, sortable: true,align:'center',editor:{
	        type:'numberbox',
	        options: {
	        required:true
	        }
        },
	    formatter:function(value,row){
	    	return value+"级作物";
		}},
        {title: '种子类型', field: 'type', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
        options: {
        required:true
        }
        }},

        {title: '可获经验', field: 'exp', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
        options: {
        required:true
        }
        }},
        {title: '每季成熟所需时间', field: 'matureTime', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
        options: {
        required:true
        }
        },
        formatter:function(value,row){
        if(value==undefined)return "";
        return value+'秒';
        }},
        {title: '每季成熟可获收', field: 'output', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
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
        type:'numberbox',
        options: {
        required:true
        }
        }},
        {title: '土地需求', field: 'landRequirement', width: 50, sortable: true,align:'center',editor:{
        type:'numberbox',
        options: {
        required:true
        }
        },formatter:function(value,row){
	    	return getCodeLandRequireFromCode(value);
		}},

        {title: '每季成熟可获积分', field: 'score', width: 50, sortable: true,align:'center',
        editor:{
        type:'numberbox',
        options: {
        required:true
        }
        },formatter:function(value,row){
	    	return value+"积分";
		}},
        {title: '提示信息', field: 'tip', width: 50, sortable: true,align:'center',editor:{
        type:'validatebox',
        options: {
        required:true
        }
        }},
        {title: '操作', field: 'option', width: 50,align:'center',
        	formatter:function(value,row){
                return  '<a href="javascript:void(0)" style="background-color:white;border-radius:5px;"  class="easyui-linkbutton" onclick="javascript:showCropsGrowEdit(+'+row.cId+')">成长阶段</a>';
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
        onSuccess:function(index,row){
	        console.log(row);
	        $("#msgBox").text(row.msg);
        },
        onDblClickRow:function (rowIndex, rowData){
        		grid.datagrid("endEdit", rowIndex);
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
	        caption: $("#genderSearch").val()
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
    	 $('#formEditor').form("reset");
    	 $('#formEditor').find('input[name="id"]').val("0");
        $('#formContainer').dialog('open').dialog('center').dialog('setTitle','添加数据');
    }
    function saveRecord() {
        $('#formEditor').form('submit', {
            url: '<%=basePath%>seed/save',
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
        function deleteRecord() {
        	var row = grid.datagrid('getSelected');
        	$.post('<%=basePath%>seed/delete',row,function(data){
        		 $.messager.show({
                     title: "消息",
                     msg: data.msg
                 });
        	});
        	grid.datagrid('reload');
        };
        function showCropsGrowEdit(value){
        	cId=value;
        	$("#cropGrow").window({
        		width:'800',
        		height:'420',
        		title:'编辑成长阶段',
        		href:'<%=basePath%>cropsGrow/grid',
        		closed:false,
        		modal:true,
        		cache:false
        	})
    	}
        function getCodeLandRequire(){
        	$.post('<%=basePath%>codeLandRequire/data',
        			function(data){
        		codeLandRequire=data;
        	})
        }
        function getCodeLandRequireFromCode(code){
        	for(var index in codeLandRequire){
        		if(codeLandRequire[index].code==code){
        			return codeLandRequire[index].caption;
        		}
        	}
        }
        function resizeFrame(){
        	window.parent.document.getElementById("tools").src="tools.jsp";
        	window.parent.document.getElementById("framesets").rows='60,*,50';
        }
        </script>
     </body>
</html>