<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>right</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
    <script src="${base_addr}/static/js/myjs.js"></script>

</head>

<body>
<div class="loc">
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;安装任务列表</div>
</div>
<div style="margin: 10px">
<input name="textfield" type="text" class="input1" id="textfield" value="" size="40" />
<input name="button" id="button" value="查询" class="blue" type="button">
</div>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
    <td width="200">任务编号</td>
    <td width="200">柜台</td>
    <td width="100">安装工程师</td>
    <td align="center">安装日期</td>
    <td align="center" width="150">创建日期</td>
    <td align="center" width="150">详情</td>
    <td align="center" width="150">查看</td>
  </tr>
  <#if list??>
    <#list list as s>
    <tr class="con">
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox3" id="checkbox3" type="checkbox"></td>
        <td bgcolor="#FFFFFF">${s.orderCounterNo?if_exists}</td>
        <td bgcolor="#FFFFFF"><a href="orderProductTaskDetail.html" target="_blank">${s.counterName?if_exists}</a></td>
        <td bgcolor="#FFFFFF">${s.setupUser?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">${s.setupTime?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">${s.gmtCreate?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF"><a href="#">${s.comment?if_exists}</a></td>
        <td align="center" bgcolor="#FFFFFF"><a href="toDisplayErectorDtl?orderSetupId=${s.id?if_exists}">查看</a></td>
    </tr>
    </#list>
  </#if>
</tbody></table>
</body>
</html>
