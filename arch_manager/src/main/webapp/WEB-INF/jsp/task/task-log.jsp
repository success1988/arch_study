<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/jsp/taglibs.jsp"%>
<div class="easyui-layout  container"
     data-options="fit : true,border : false" align="center">
    <div class="table-info" data-options="region:'north',border:false"
         style="overflow: hidden; font-size: 12px; margin: 5px;">
        <form id="acct_taskLogs_form" autocomplete="off">
            <table>
                <tr>
                    <th>运行类型：</th>
                    <td><input class="easyui-combobox" name="type"
                               style="width: 150px"
                               data-options="
						valueField: 'value',
						textField: 'text',
						editable:false,
						panelHeight:'auto',
						data: [{
							 value:'S',
							text: '启动'
						},{
							 value:'R',
							text: '运行'
						},{
							value:'P',
							text: '停止'
						}]">
                    </td>
                    <th>状 态：</th>
                    <td><input class="easyui-combobox" name="status"
                               style="width: 150px"
                               data-options="
						valueField: 'value',
						textField: 'text',
						editable:false,
						panelHeight:'auto',
						data: [{
							 value:'2',
							text: '成功'
						},{
							value:'3',
							text: '失败'
						}]">
                    </td>
                    <th>任务编号：</th>
                    <td>
                        <input class="easyui-textbox" name="code" style="width:220px">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height:100%; width:1130px; overflow-y: hidden; overflow-x:auto;">
        <table class="easyui-datagrid" id="dg_acct_taskLogs";
               data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${ctx}/task/taskLogs/datagrid',method:'post',pageSize:20,fit:true,toolbar:'#acctTaskLogsTopBtn'">
            <thead>
            <tr>
                <th data-options="field:'code',width:'200px'">任务编号</th>
                <th data-options="field:'name',width:'200px'">任务名称</th>
                <th data-options="field:'expression',width:'160px'">定时表达式</th>
                <th data-options="field:'typeText',width:'100px',align:'center'">类型</th>
                <th data-options="field:'startTime',width:'170px',align:'center'">启动时间</th>
                <th data-options="field:'endTime',width:'170px',align:'center'">停止时间</th>
                <th data-options="field:'execTime',width:'100px',align:'right'">执行时长ms</th>
                <th data-options="field:'host',width:'150px'">主机IP</th>
                <th data-options="field:'statusText',width:'100px',align:'center'">运行状态</th>
                <th data-options="field:'createTime',width:'170px',align:'center'">创建时间</th>
                <th data-options="field:'errorMsg',width:'300px'" >异常信息</th>
            </tr>
            </thead>
        </table>
        <div id="acctTaskLogsTopBtn" style="padding: 2px 5px;">
            <div class="easyui-linkbutton" onclick="searchAcctTaskLogs()"
                 iconCls="icon-search" plain="true">查询</div>
            <div class="easyui-linkbutton"
                 onclick="javascript:$('#acct_taskLogs_form').form('reset');"
                 iconCls="icon-clear" plain="true">清空</div>
        </div>
    </div>
</div>


<script type="text/javascript">

    /**
     * 搜索业务单
     */
    function searchAcctTaskLogs(){
        var queryJson = $('#acct_taskLogs_form').serializeJSONNoNull();
        $('#dg_acct_taskLogs').datagrid('load', queryJson);
    }
</script>