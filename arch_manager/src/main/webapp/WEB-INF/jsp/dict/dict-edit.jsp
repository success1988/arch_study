<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>
<div style="padding:10px 10px 10px 10px">
	<form id="dictEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
		<table cellpadding="5">
			<tr>
				<td>字典分类:</td>
				<td><input class="easyui-textbox" type="text" name="category" data-options="required:true" style="height:30px;width: 280px;"></input></td>
			</tr>
			<tr>
				<td>字典键:</td>
				<td><input class="easyui-textbox" name="name" data-options="required:true" style="height:30px;width: 280px;"></input></td>
			</tr>
			<tr>
				<td>字典值:</td>
				<td><input class="easyui-textbox" name="value" data-options="required:true" style="height:30px;width: 280px;"></input></td>

			</tr>
			<tr>
				<td>备注:</td>
				<td>
					<input id="easyui-textbox" name="remark" class="easyui-textbox" data-options="multiline:true" label="备注" style="width:100%;height:70px">	        </tr>
				</td>
			</tr>

		</table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">

	function submitForm(){
		if(!$('#dictEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		var paramJson = [];


		$.post("${ctx}/dict/update",$("#dictEditForm").serialize(), function(data){
			if(data.code == 0){
				$.messager.alert('提示','修改成功!','info',function(){
					$("#dictEditWindow").window('close');
					$("#dictList").datagrid("reload");
				});
			}
		});
	}
</script>
