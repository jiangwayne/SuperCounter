<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>right</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
    <script src="${base_addr}/static/js/myjs.js"></script>

</head>

<body>
<div class="loc">
    <div class="icon">当前位置：安装公司列表</div>
</div>
<span class="con2">
<input name="button2" id="button2" value="添加安装公司" class="blue" onclick="location.href='addInstallationCompany.html'" type="button">
<br>
<br>
</span>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody><tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
        <td width="150">名称</td>
    <#--<td>品牌</td>-->
        <td align="center">电话</td>
        <td align="center">Email</td>
        <td align="center">备注</td>
        <td align="center" width="150">添加日期</td>
        <td align="center" width="150">操作</td>
    </tr>
    <#if list??>
        <#list list as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF"><input name="checkbox2" value="${s.id?if_exists}" id="checkbox" type="checkbox"></td>
            <td bgcolor="#FFFFFF">${s.name?if_exists}</td>

            <td align="center" bgcolor="#FFFFFF">${s.phone?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.email?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.comment?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.gmtCreate?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="addSupplier?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>


</body></html>