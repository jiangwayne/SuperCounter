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
  <div class="icon">当前位置：BA列表</div>
</div>
<span class="con2">
<input name="button2" id="button2" value="添加BA" class="blue" onclick="location.href='addBA'" type="button">
<br>
<br>
</span>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
    <td width="150">编号</td>
    <td>用户名</td>
    <td align="center">所属组织</td>
    <td align="center">角色类型</td>
    <td align="center">备注</td>
    <td align="center" width="150">添加日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if list??>
    <#list list as s>
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
        <td bgcolor="#FFFFFF">${s.id?if_exists}</td>
        <td bgcolor="#FFFFFF">${s.name?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">${s.orgName?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">BA</td>
        <td align="center" bgcolor="#FFFFFF">${s.comment?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">${s.gmtCreate?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">
            <a href="addBA?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
    </tr>
    </#list>
  </#if>
</tbody></table>
</body></html>