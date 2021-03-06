<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/arch.css" />
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>

<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>字典1管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'dict/dict-list'}">查询字典</li>
					<li data-options="attributes:{'url':'dict/dict-add'}">新增字典</li>
	         	</ul>
         	</li>
			<li>
				<span>用户2管理</span>
				<ul>
					<li data-options="attributes:{'url':'user/user-add'}">新增用户</li>
				</ul>
			</li>
			<li>
				<span>任务管理</span>
				<ul>
					<li data-options="attributes:{'url':'task/task-list-quartz'}">quartz任务配置</li>
					<li data-options="attributes:{'url':'task/task-list'}">任务配置</li>
					<%--<li data-options="attributes:{'url':'task/task-log'}">任务日志</li>--%>
				</ul>
			</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>