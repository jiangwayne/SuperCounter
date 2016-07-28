
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>计划单详细</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${base_addr}/static/js/laydate.dev.js"></script>
    <script src="${base_addr}/static/js/jquery1.4.2.min.js" type="text/javascript"></script>
</head>
<style>
    body{ background:#ebf1fa}
</style>
<body>

<div class="wztitle">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td align="center">生产任务单详情</td>
        </tr>
        <tr>
            <td class="date">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="25%">编号：${orderCounter.orderCounterNo?if_exists}</td>
                        <td width="25%">创建日期：${(orderCounter.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td width="25%">供应商：${orderCounter.orgCounterId?if_exists}</td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#cccccc" class="bgg">
    <tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="150"></td>
        <td>道具名称</td>
        <td align="center">数量</td>
        <td align="center">预入库完成日期</td>
        <td align="center">预出库完成日期</td>

        <td width="150" align="center">更新</td>
    </tr>
<#if dtlList??>
    <#list dtlList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.objParent?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">
                <input id="in_${s_index+1}" type="text" value="${(s.inStockTime?string("yyyy-MM-dd"))!''}" size="20" />
                <script type="text/javascript">laydate({elem: '#in_${s_index+1}'});</script>
            </td>
            <td align="center" bgcolor="#FFFFFF">
                <input id="out_${s_index+1}" type="text" value="${(s.outStockTime?string("yyyy-MM-dd"))!''}" size="20" />
                <script type="text/javascript">laydate({elem: '#out_${s_index+1}'});</script>
            </td>

            <td align="center" bgcolor="#FFFFFF">
                <input type="button" onclick="updateDtl('${s_index+1}','${s.id?if_exists}')" value="更新" class="blue" />
            </td>
        </tr>
    </#list>
</#if>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td align="center"><a href="javascript:history.go(-1)" class="closewd">关闭</a></td>
    </tr>
    <tr>
        <td>&nbsp;</td>
    </tr>
</table>

</body>
<script>
    $(function (){
        //初始化js
    });
    function updateDtl(dateInputId,dtlId){
        var inDateStr = $('#in_'+dateInputId).val();
        var outDateStr = $('#out_'+dateInputId).val();
        if(!(inDateStr) && !(outDateStr)){
            alert('时间不能都为空');
        }
        $.ajax({
            url: '${base_addr}/gtb/orderSupplier/updateDtl' ,
            secureuri: false,
            async : false,
            data: {dtlId : dtlId, inDateStr : inDateStr, outDateStr : outDateStr},
            dataType: 'json',
            success: function (data) {
                if(data.success){
                    location.reload();
                }
            }
        });
    }
</script>
</html>