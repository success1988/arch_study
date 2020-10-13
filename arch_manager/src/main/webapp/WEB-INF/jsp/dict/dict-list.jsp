<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>
<table class="easyui-datagrid" id="dictList" title="字典列表"
       data-options="singleSelect:false,collapsible:true,pagination:false,url:'${ctx}/dict/list',method:'get',toolbar:toolbar">
    <thead>
        <tr>
            <th data-options="field:'category'" width="200px">字典分类 </th>
            <th data-options="field:'name'" width="200px">字典键</th>
            <th data-options="field:'value'" width="200px">字典值</th>
            <th data-options="field:'remark'" width="250px">备注</th>
            <th data-options="field:'createTime',width:300,align:'center',formatter:KANG_CHENG.formatDateTime">创建日期</th>
        </tr>
    </thead>
</table>
<div id="dictEditWindow" class="easyui-window" title="编辑字典" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${ctx}/dict/dict-edit'" style="width:50%;height:60%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var dictList = $("#dictList");
    	var sels = dictList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'刷新',
        iconCls:'icon-reload',
        handler:function(){
            $('#dictList').datagrid('reload');
        }
    },{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增字典')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个字典才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个字典!');
        		return ;
        	}
        	
        	$("#dictEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#dictList").datagrid("getSelections")[0];
        			data.createTime = KANG_CHENG.formatDateTime(data.createTime);
        			$("#dictEditForm").form("load",data);
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中字典!');
        		return ;
        	}
            if(ids.indexOf(',') > 0){
                $.messager.alert('提示','只能选择一个字典!');
                return ;
            }

            $.messager.confirm('确认','确定删除ID为 '+ids+' 的字典吗？',function(r){
        	    if (r){
        	    	var params = {"id":ids};
                	$.post("${ctx}/dict/delete",params, function(data){
            			if(data.code == 0){
            				$.messager.alert('提示','删除字典成功!',undefined,function(){
            					$("#dictList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>