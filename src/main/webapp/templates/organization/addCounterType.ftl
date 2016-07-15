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
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;添加柜台样式</div>
</div>
<form action="addCounterType" method="post">
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
      <td align="right" class="title2">名称</td>
      <td class="con2">
          <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
          <input name="name" type="text" class="input1" id="textfield" value="${model.name?if_exists}" size="40" />
      </td>
    </tr>
    <tr>
      <td align="right" class="title2">备注</td>
      <td class="con2">
          <textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${model.description?if_exists}</textarea>
      </td>
    </tr>
    <tr>
      <td align="right" class="title2">&nbsp;</td>
      <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
      <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listCounterType'" /></td>
    </tr>
  </table>
</form>
<br>
<hr>
<br>
<div class="icon">包括家具</div>
<br>
<strong>添加家具：</strong>

<select id="brand2" class="ip" id="select3">
    <#if brandList??>
        <#list brandList as s>
            <option value="${s.id?if_exists}">${s.name?if_exists}</option>
        </#list>
    </#if>
</select>
<form action="addCounterType"
<select name="furniture">

</select>
<input name="button" id="button" value="添加" class="blue" type="button">

<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody><tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
        <td width="150">家具编号</td>
        <td>家具名称</td>
        <td align="center" width="150">添加日期</td>
        <td align="center" width="150">操作</td>
    </tr>
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
        <td bgcolor="#FFFFFF"><a href="http://www.digitech.sh.cn/test/laoz/yojianto.html" target="_blank">F01</a></td>
        <td bgcolor="#FFFFFF">衣柜</td>
        <td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>
        <td align="center" bgcolor="#FFFFFF"><a href="#"><img src="../images/sc.jpg" height="18" width="13"></a></td>
    </tr>
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
        <td bgcolor="#FFFFFF"><a href="http://www.digitech.sh.cn/test/laoz/yojianto.html" target="_blank">F01</a></td>
        <td bgcolor="#FFFFFF">衣柜</td>
        <td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>
        <td align="center" bgcolor="#FFFFFF"><a href="#"><img src="../images/sc.jpg" height="18" width="13"></a></td>
    </tr>
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
        <td bgcolor="#FFFFFF"><a href="http://www.digitech.sh.cn/test/laoz/yojianto.html" target="_blank">F01</a></td>
        <td bgcolor="#FFFFFF">衣柜</td>
        <td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>
        <td align="center" bgcolor="#FFFFFF"><a href="#"><img src="../images/sc.jpg" height="18" width="13"></a></td>
    </tr>
    <tr class="con">
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
        <td bgcolor="#FFFFFF"><span class="con2">
      <input name="button" id="button" value="全部删除" class="blue" type="button">
    </span></td>
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td bgcolor="#FFFFFF">&nbsp;</td>
        <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    </tbody></table>

</body>
</html>
