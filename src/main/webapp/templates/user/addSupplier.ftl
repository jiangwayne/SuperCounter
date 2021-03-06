<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>right</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
    <script src="${base_addr}/static/js/myjs.js"></script>
    <script src="${base_addr}/static/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#supplier").val('${model.orgId?if_exists}');
        });
    </script>
</head>

<body>
<div class="loc">
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;查询供应商->编辑供应商</div>
</div>
<form action="addSupplier" method="post">
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
      </div>
    </td>
</tr>
  <tr>
    <td width="200" align="right" class="title2"><strong>所属供应商</strong></td>
    <td class="con2"><select id="supplier" name="orgId" class="ip" id="select3">
    <#if supplierList??>
        <#list supplierList as s>
            <option value="${s.id?if_exists}">${s.name?if_exists}</option>
        </#list>
    </#if>
    </select></td>
  </tr>
    <tr>
        <td align="right" class="title2">用户名</td>
        <td class="con2">
            <input name="id" type="hidden"  value="${model.id?if_exists}" size="40" />
            <input name="name" type="text" class="input1"  value="${model.name?if_exists}" size="40" />
        </td>
    </tr>
    <tr>
        <td align="right" class="title2">密码</td>
        <td class="con2"><input name="pwd" type="password" class="input1" value="" size="40" /></td>
    </tr>
    <tr>
        <td align="right" class="title2">姓名</td>
        <td class="con2"><input name="fullName" type="text" class="input1"  value="${model.fullName?if_exists}" size="40" /></td>
    </tr>
    <tr>
        <td align="right" class="title2">电话</td>
        <td class="con2"><input name="phone" type="text" class="input1"  value="${model.phone?if_exists}" size="40" /></td>
    </tr>
    <tr>
        <td align="right" class="title2">备注</td>
        <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" >${model.comment?if_exists}</textarea></td>
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
