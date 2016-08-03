<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>right</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css">
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css">
    <script src="${base_addr}/static/js/myjs.js"></script>
    <script src="${base_addr}/static/js/jquery-1.3.2.min.js"></script>
</head>

<body>
<div class="loc">
    <div class="icon">当前位置：任务列表</div>
</div>
<span class="con2">
<br>
</span>


<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody><tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="20"></td>
        <td width="150">订单号</td>
        <td width="150">用户</td>
        <td width="150">品牌</td>
        <td align="center" width="150">创建日期</td>
        <td align="center" width="150">供应商任务</td>
        <td align="center" width="150">柜台任务</td>
    </tr>
    <#if list??>
        <#list list as s>
        <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">${s_index+1}</td>
            <td bgcolor="#FFFFFF">${s.orderNo?if_exists}</td>
            <td bgcolor="#FFFFFF">${s.user.name?if_exists}</td>
            <td bgcolor="#FFFFFF">${s.brand.name?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="../orderSupplier/list?orderId=${s.id?if_exists}" target="_self">详细</a>
            </td>
            <td align="center" bgcolor="#FFFFFF">
                <a href="../orderCounter/list?orderId=${s.id?if_exists}" target="_self">详情</a>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>



</body>

</html>