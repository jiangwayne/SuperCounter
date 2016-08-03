
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
            <td align="center">柜台任务任务单详情</td>
        </tr>
        <tr>
            <td class="date">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="25%">编号：${orderCounter.orderCounterNo?if_exists}</td>
                        <td width="25%">创建日期：${(orderCounter.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td width="25%">柜台名称：${orderCounter.orgCounter.name?if_exists}</td>
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
        <td align="center">长宽高</td>
        <td align="center">出血长宽高</td>
        <td align="center">添加日期</td>
        <td width="150" align="center">操作</td>
    </tr>
<#if dtlList??>
    <#list dtlList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.objParent.name?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td bgcolor="#FFFFFF">${s.objParent.length?if_exists}*${s.objParent.width?if_exists}*${s.objParent.height?if_exists}</td>
            <td bgcolor="#FFFFFF">${s.objParent.lengthUp?if_exists}*${s.objParent.widthUp?if_exists}*${s.objParent.heightUp?if_exists}</td>

            <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="toEdit?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deleteOne(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
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

</html>