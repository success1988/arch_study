<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>
<div class="easyui-layout  container"
     data-options="fit : true,border : false" align="center">
    <div class="table-info" data-options="region:'north',border:false"
         style="overflow: hidden; font-size: 12px; margin: 5px;">
        <form id="taskInfo_form" autocomplete="off">
            <table>
                <tr>
                    <th>状 态：</th>
                    <td><input class="easyui-combobox" name="status"
                               style="width: 150px"
                               data-options="
						valueField: 'value',
						textField: 'text',
						editable:false,
						panelHeight:'auto',
						data: [{
							 value:'1',
							text: '运行'
						},{
							value:'0',
							text: '停止'
						}]">
                    </td>
                    <th>运行节点：</th>
                    <td>
                        <input class="easyui-combobox"
                               name="clusterip"
                               data-options="url:'${ctx}/sys/dict?dictType=clusterip',
                               method:'get',
					            valueField:'key',
							editable:false,
							textField:'value',
							panelHeight:'auto' " style="width:200px">
                    </td>
                    <th>已注册节点：</th>
                    <td><span id="span_acct_clusters"></span></td>
                </tr>
            </table>
        </form>
    </div>

    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height:100%; width:1130px; overflow-y: hidden; overflow-x:auto;">
        <table class="easyui-datagrid" id="dg_taskInfo";
               data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${ctx}/task/taskInfo/datagrid',method:'post',pageSize:20,fit:true,toolbar:'#taskInfoTopBtn'">
            <thead>
            <tr>
                <th data-options="field:'code',width:'200px'">任务编号</th>
                <th data-options="field:'name',width:'150px'">任务名称</th>
                <th data-options="field:'clusterip',width:'120px'">节点IP</th>
                <th data-options="field:'expression',width:'160px'">定时表达式</th>
                <th data-options="field:'statusName',width:'100px'">任务状态</th>
                <th data-options="field:'startTime',width:'150px'">启动时间</th>
                <th data-options="field:'stopTime',width:'150px'">停止时间</th>
                <th data-options="field:'jobclass',width:'280px'">定时任务全类名</th>
                <th data-options="field:'params',width:'300px'">用户参数</th>
                <th data-options="field:'createTime',width:'150px'">创建时间</th>
                <th data-options="field:'description',width:'150px'" >备注</th>
            </tr>
            </thead>
        </table>
        <div id="taskInfoTopBtn" style="padding: 2px 5px;">

            <div class="easyui-linkbutton" onclick="searchTaskInfo()"
                 iconCls="icon-search" plain="true">查询</div>

            <div class="easyui-linkbutton"
                 onclick="javascript:$('#taskInfo_form').form('reset');"
                 iconCls="icon-clear" plain="true">清空</div>
            <div class="easyui-linkbutton" onclick="createTaskInfo()"
                 iconCls="icon-add" plain="true">新增</div>
            <div class="easyui-linkbutton" onclick="editTaskInfo()"
                 iconCls="icon-edit" plain="true">修改</div>
            <div class="easyui-linkbutton" onclick="deleteTaskInfo()"
                 iconCls="icon-remove" plain="true">删除</div>
            <div class="easyui-linkbutton" onclick="optTaskInfo(1)"
                 iconCls="icon-remove" plain="true">启动</div>
            <div class="easyui-linkbutton" onclick="optTaskInfo(0)"
                 iconCls="icon-remove" plain="true">停止</div>
        </div>
    </div>
</div>



<div id="dlg_taskInfo" class="easyui-dialog" style="width:500px; height:520px" iconCls="icon-add"
     closed="true" buttons="#dlg_taskInfo-buttons">
    <form id="fm_taskInfo"  method="post" novalidate  style="margin:0;padding:20px 50px">
        <input class="easyui-textbox" type="hidden" id="fm_taskInfo_id" name="id">
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_code" name="code" class="easyui-textbox"  data-options="required:true,validType:['englishOrNum','minLength[2]']" label="任务编号" style="width:100%">
            <label style="color: red">必须唯一</label>
        </div>
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_name" name="name" class="easyui-textbox"  data-options="required:true,validType:['minLength[2]']" label="任务名称" style="width:100%">
        </div>
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_expression" name="expression" class="easyui-textbox" required="required" label="定时表达式" style="width:100%">
        </div>
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_jobClass" name="jobclass" class="easyui-textbox" required="required" label="定时任务类全名" style="width:100%">
        </div>
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_params" name="params" multiline="true" class="easyui-textbox" label="自定义参数" style="width:100%;height:80px;">
            <label style="color: red">参数为标准JSON格式，如{"name":"job","age":20}</label>
        </div>
        <div style="margin-bottom:10px" >
            <input id="form_taskInfo_status" class="easyui-combobox"
                   name="clusterip"
                   label="运行节点"
                   data-options="url:'${ctx}/sys/dict?dictType=clusterip',
                               method:'get',
							valueField:'key',
							editable:false,
							textField:'value',
							panelHeight:'auto' " style="width:200px">
        </div>
        <div style="margin-bottom:10px" >
            <input id="fm_taskInfo_description" class="easyui-textbox" name="description" label="备注" style="width:100%">
        </div>
    </form>
</div>
<div id="dlg_taskInfo-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveTaskInfo()" style="width:90px">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg_taskInfo').dialog('close')" style="width:90px">取消</a>
</div>



<script type="text/javascript">

    $(function () {
        $.post("${ctx}/task/taskInfo/registClusters", function (data) {
            $('#span_acct_clusters').text(data);
        });
    });

    /**
     * 搜索业务单
     */
    function searchTaskInfo(){
        
        var queryJson = $('#taskInfo_form').serializeJSONNoNull();
        $('#dg_taskInfo').datagrid('load', queryJson);
    }

    /**
     * 新增
     */
    function createTaskInfo(){
        $('#dlg_taskInfo').dialog({title:'新增定时任务',iconCls:'icon-add'}).dialog('open').dialog('center');
        $('#fm_taskInfo').form('clear');
        $('#form_taskInfo_status').combobox('reload');
        $('#fm_taskInfo_code').textbox('readonly',false);
    }

    /**
     * 修改
     */
    function editTaskInfo(){
        var row = $('#dg_taskInfo').datagrid('getSelected');
        if (row){
            $('#dlg_taskInfo').dialog({title:'修改定时任务',iconCls:'icon-edit'}).dialog('open').dialog('center');
            $('#fm_taskInfo').form('load',row);
            $('#fm_taskInfo_code').textbox('readonly', true);
        }else{
            $.messager.alert({
                title: '系统提示',
                msg: '请选择要修改的数据'
            });
        }
    }

    /**
     * 保存 更新
     */
    function saveTaskInfo() {
        
        var id = $('#fm_taskInfo_id').textbox('getValue');
        var url = "${ctx}/task/taskInfo/create";
        if(id!="" && parseInt(id)>0){
            url = "${ctx}/task/taskInfo/update";
        }
        $('#fm_taskInfo').form('submit',{
            url: url,
            onSubmit: function(){
                var isValid = $(this).form('validate');
                if (isValid){
                    $.messager.progress({
                        msg:'Loading ...'
                    });
                }else{
                    $.messager.alert({
                        title: '系统提示',
                        msg: "请输入必填项信息！"
                    });
                }
                return isValid;
            },
            success: function(result){
                console.log(result);
                var result = eval('('+result+')');
                if (result.code == 1){
                    $.messager.alert({ title: '系统提示', msg: '保存成功！'});
                    $('#dlg_taskInfo').dialog('close');
                    $('#dg_taskInfo').datagrid('reload');
                } else {
                    $.messager.alert({
                        title: '系统提示',
                        msg: result.msg
                    });
                }
                $.messager.progress('close');
            }
        });
    }

    //删除
    function deleteTaskInfo() {
        
        var row = $('#dg_taskInfo').datagrid('getSelected');
        if (row) {
            $.messager.confirm('确认', '你确定要删除此数据吗？', function(r) {
                if (r) {
                    $.messager.progress({
                        msg : 'Loading ...'
                    });
                    $.post('${ctx}/task/taskInfo/delete', {
                        id : row.id
                    }, function(result) {
                        console.log(result);
                        if (result.code == 1) {
                            $.messager.alert({
                                title : '系统提示',
                                msg : '删除成功！'
                            });
                            $('#dg_taskInfo').datagrid('reload');
                        } else {
                            $.messager.alert({
                                title : '系统提示',
                                msg : result.msg
                            });
                        }
                        $.messager.progress('close');
                    }, 'json');
                }
            });
        }else{
            $.messager.alert({
                title: '系统提示',
                msg: '请选择要删除的数据'
            });
        }
    }

    //启动、停止
    function optTaskInfo(act) {
        
        var row = $('#dg_taskInfo').datagrid('getSelected');
        if (row) {
            var m = act==1?"启动":"停止";
            var postAction = act==1?"start":"stop";
            $.messager.confirm('确认', '你确定要'+m+'此任务吗？', function(r) {
                if (r) {
                    $.messager.progress({
                        msg : 'Loading ...'
                    });
                    $.post('${ctx}/task/taskInfo/' + postAction, {
                        id : row.id
                    }, function(result) {
                        console.log(result);
                        console.log(result.code);
                        if (result.code == 1) {
                            $.messager.progress('close');
                            $.messager.alert('系统提示',m + '成功！');
                            $('#dg_taskInfo').datagrid('reload');
                        } else {
                            $.messager.progress('close');
                            $.messager.alert('系统提示', result.msg);
                        }
                    }, 'json');
                }
            });
        }else{
            $.messager.alert({
                title: '系统提示',
                msg: '请选择要启动或停止的数据'
            });
        }
    }
</script>
