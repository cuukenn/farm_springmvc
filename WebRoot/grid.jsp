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
	<table id="tt" style="width:100%;height:100%" 
			title="DataGrid - CardView" 
			singleSelect="true" 
			fitColumns="true" 
			remoteSort="false"
			data=[	{
						"itemid":"dasds",
						"listprice":"listprice",
						"unitcost":"unitcost",
						"attr1":"attr1",
						"status":"status"
					}]
			sortName="itemid">
		<thead>
			<tr>
				<th field="itemid" width="80" sortable="true">Item ID</th>
				<th field="listprice" width="80" sortable="true">List Price</th>
				<th field="unitcost" width="80" sortable="true">Unit Cost</th>
				<th field="attr1" width="150" sortable="true">Attribute</th>
				<th field="status" width="60" sortable="true">Status</th>
			</tr>
		</thead>
	</table>	
</body>
<script>
var cardview = $.extend({}, $.fn.datagrid.defaults.view, {
	renderRow: function(target, fields, frozen, rowIndex, rowData){
		var cc = [];
		cc.push('<td colspan=' + fields.length + ' style="padding:10px 5px;border:0;">');
		if (!frozen){
			var aa = rowData.itemid.split('-');
			var img = 'shirt' + aa[1] + '.gif';
			cc.push('<img src="images/' + img + '" style="width:150px;float:left">');
			cc.push('<div style="float:left;margin-left:20px;">');
			for(var i=0; i<fields.length; i++){
				var copts = $(target).datagrid('getColumnOption', fields[i]);
				cc.push('<p><span class="c-label">' + copts.title + ':</span> ' + rowData[fields[i]] + '</p>');
			}
			cc.push('</div>');
		}
		cc.push('</td>');
		return cc.join('');
	}
});
$('#tt').datagrid({
	view: cardview
});
</script>
</html>