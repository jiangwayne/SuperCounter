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
    <div class="icon">当前位置：&nbsp;&gt;&nbsp;添加安装公司</div>
</div>
<form action="addInstallationCompany" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
        <tr>
            <td align="right" class="title2">品牌</td>
            <td class="con2">
                <div class="people">
                    <ul>
                        <ul>
                        <#if brandList??>
                            <#list brandList as s>
                                <li><input type="checkbox" value="${s.id?if_exists}" name="brandIds" <#if model.brandIds?contains(s.id)>checked="checked"</#if> ><span>${s.name?if_exists}</span></li>
                            </#list>
                        </#if>
                        </ul>
                    </ul>
                </div>
            </td>
        </tr>
        <tr>
            <td align="right" class="title2">名称</td>
            <td class="con2">
                <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
                <input name="name" type="text" class="input1"  value="${model.name?if_exists}" size="40" />
            </td>
        </tr>
        <tr>
            <td align="right" class="title2">地址</td>
            <td class="con2"><input name="address" type="text" class="input1"  value="${model.address?if_exists}" size="40" /></td>
        </tr>
        <tr>
            <td align="right" class="title2">经纬度</td>
            <td class="con2"><input name="longLat" type="text" class="input1"  value="${model.longLat?if_exists}" size="40" /></td>
        </tr>
        <tr>
            <td align="right" class="title2">电话</td>
            <td class="con2"><input name="phone" type="text" class="input1"  value="${model.phone?if_exists}" size="40" /></td>
        </tr>
        <tr>
            <td align="right" class="title2">Email</td>
            <td class="con2"><input name="email" type="text" class="input1"  value="${model.email?if_exists}" size="40" /></td>
        </tr>
        <tr>
            <td align="right" class="title2">备注</td>
            <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2">${model.comment?if_exists}</textarea></td>
        </tr>
        <tr>
            <td align="right" class="title2">&nbsp;</td>
            <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
                <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listSupplier'" /></td>
        </tr>
    </table>
</form>
</body>
</html>
