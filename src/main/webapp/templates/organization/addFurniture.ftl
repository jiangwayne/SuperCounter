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
            $("#brand").val('${model.orgId?if_exists}');
        });
    </script>
</head>

<body>
<div class="loc">
    <div class="icon">当前位置：&nbsp;&gt;&nbsp;家具管理->编辑家具</div>
</div>
<form action="addFurniture" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
    <tr>
      <td align="right" class="title2">品牌</td>
      <td class="con2">
          <select id="brand" name="orgId" class="ip" id="select3">
          <#if brandList??>
            <#list brandList as s>
                <option value="${s.id?if_exists}">${s.name?if_exists}</option>
            </#list>
          </#if>
          </select>
      </td>
    </tr>
    <tr>
      <td width="200" align="right" class="title2"><strong>编号</strong></td>
      <td class="con2">
          <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
          <input name="furnitureNo" type="text" class="input1" id="textfield4" value="${model.furnitureNo?if_exists}" size="40" />
      </td>
    </tr>
    <tr>
      <td align="right" class="title2">家具名称</td>
      <td class="con2"><input name="name" type="text" class="input1" id="textfield" value="${model.name?if_exists}" size="40" /></td>
    </tr>
    <tr>
      <td align="right" class="title2">备注</td>
      <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${model.comment?if_exists}</textarea></td>
    </tr>
    <tr>
      <td align="right" class="title2">&nbsp;</td>
      <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
      <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listFurniture'" /></td>
    </tr>
  </table>
</form>
</body>
</html>
