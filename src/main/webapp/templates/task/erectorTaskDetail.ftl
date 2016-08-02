
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
            <td align="center">安装任务任务单详情</td>
        </tr>
        <tr>
            <td class="date">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="25%">编号：${orderSetup.orderCounterNo?if_exists}</td>
                        <td width="25%">创建日期：${(orderSetup.setupTime?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
                        <td width="25%">柜台名称：${orderSetup.orgCounter.name?if_exists}</td>
                        <td width="25%">安装工程师：${orderSetup.setupUser.name?if_exists}</td>
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
        <td>状态</td>
        <td align="center">数量</td>
        <td align="center">添加日期</td>
        <td width="150" align="center">操作</td>
    </tr>
<#if dtlCountList??>
    <#list dtlCountList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.objParent.name?if_exists}</td>
            <td bgcolor="#FFFFFF">未安装</td>
            <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="toEdit?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deleteOne(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
            </td>
        </tr>
    </#list>
</#if>
<#if setDtlList??>
    <#list setDtlList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.objParent.name?if_exists}</td>
            <td bgcolor="#FFFFFF">已安装</td>
            <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="toEdit?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deleteOne(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
            </td>
        </tr>
    </#list>
</#if>
</table>
<hr>
<br>
报损列表<br><br>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody><tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
        <td width="150">子件名称</td>
        <td>品牌</td>
        <td bgcolor="#FFFFFF">描述</td>
        <td align="center">数量</td>
        <td align="center">上传图片</td>
        <td align="center" width="150">添加日期</td>
        <td align="center" width="150">操作</td>
    </tr>
    <#if setDtlList??>
        <#list setDtlList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.objparent.name?if_exists}</td>
            <td bgcolor="#FFFFFF">ＨＲ</td>
            <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="toEdit?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deleteOne(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>

<br>
<hr>
<br>
上传照片列表<br><br>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">

    <tr class="con" align="center">
        <td bgcolor="#FFFFFF" colspan="4"><font color="blue"><strong>全局照片</strong></font></td>
    </tr>
    <tr class="con" align="center">
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
    </tr>
    <tr class="con" align="center">
        <td bgcolor="#FFFFFF" colspan="4"><font color="blue"><strong>局部照片</strong></font></td>
    </tr>

    <tr class="con" align="center">
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
        <td bgcolor="#FFFFFF"><img src="../images/lightPic.jpg" height="50" width="50"></td>
    </tr>

    </tbody>
</table>
</body>

</html>