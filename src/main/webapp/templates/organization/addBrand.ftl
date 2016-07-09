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
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;添加品牌</div>
</div>
<form action="addBrand" method="post">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
    <tr>
      <td align="right" class="title2">名称</td>
      <td class="con2">
          <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
          <input name="name" type="text" class="input1" id="textfield" value="${model.name?if_exists}" size="40" />
      </td>
    </tr>
    <tr>
      <td align="right" class="title2">客服电话</td>
      <td class="con2"><input name="phone" type="text" class="input1" id="textfield3" value="${model.phone?if_exists}" size="40" /></td>
    </tr>
    <tr>
      <td align="right" class="title2">客服微信</td>
      <td class="con2"><input name="wx" type="text" class="input1" id="textfield3" value="${model.wxUniqueCode?if_exists}" size="40" /></td>
    </tr>
    <tr>
      <td align="right" class="title2">Email</td>
      <td class="con2"><input name="email" type="text" class="input1" id="textfield3" value="${model.email?if_exists}" size="40" /></td>
    </tr>
    <tr>
      <td align="right" class="title2">备注</td>
      <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${model.comment?if_exists}</textarea></td>
    </tr>
    <tr>
      <td align="right" class="title2">&nbsp;</td>
      <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
      <input type="submit" name="button" id="button" value="返回" class="hui" onclick="location.href='listBrand'" /></td>
    </tr>
  </table>
</form>
</body>
</html>
