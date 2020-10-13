<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>
<div style="padding:10px 10px 10px 10px">
	<form id="userCreateForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>真实姓名:</td>
	            <td><input class="easyui-textbox" type="text" name="realName" data-options="required:true" style="height:30px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>手机号:</td>
	            <td><input class="easyui-textbox" name="phoneNumber" data-options="required:true" style="height:30px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>婚姻状况:</td>
				<td>
					<input class="easyui-combobox"
						   name="marriageId"
						   label="婚姻状况"
						   style="width: 400px;"
						   data-options="url:'${ctx}/sys/dict?dictType=marriage',
											method:'get',
											valueField:'value',
											editable:false,
											textField:'key',
											required:true,
											panelHeight:'auto' ">
				</td>
	        </tr>
			<tr>
				<td>子女状况:</td>
				<td>
					<input class="easyui-combobox"
						   name="kidCountId"
						   label="子女状况"
						   style="width: 400px;"
						   data-options="url:'${ctx}/sys/dict?dictType=kidCount',
											method:'get',
											valueField:'value',
											editable:false,
											textField:'key',
											required:true,
											panelHeight:'auto' ">
				</td>
			</tr>
			<tr>
				<td>住房情况:</td>
				<td>
					<input class="easyui-combobox"
						   name="houseConditionId"
						   label="住房情况"
						   style="width: 400px;"
						   data-options="url:'${ctx}/sys/dict?dictType=houseCondition',
											method:'get',
											valueField:'value',
											editable:false,
											textField:'key',
											required:true,
											panelHeight:'auto' ">
				</td>
			</tr>
			<tr>
				<td>教育背景:</td>
				<td>
					<input class="easyui-combobox"
						   name="educationBackgroundId"
						   label="教育背景"
						   style="width: 400px;"
						   data-options="url:'${ctx}/sys/dict?dictType=educationBackground',
											method:'get',
											valueField:'value',
											editable:false,
											textField:'key',
											required:true,
											panelHeight:'auto' ">
				</td>
			</tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	function submitForm(){
		if(!$('#userCreateForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		$.messager.alert('提示',$("#dictCreateForm").serialize(),'info',function(){
			clearForm();//清空表单
		});

		/*$.post("${ctx}/dict/create",$("#dictCreateForm").serialize(), function(data){
			if(data.code == 0){
				$.messager.alert('提示','添加字典成功!','info',function(){
					clearForm();//清空表单
				});
			}
		});*/
	}
	
	function clearForm(){
		$('#userCreateForm').form('reset');
	}
</script>
