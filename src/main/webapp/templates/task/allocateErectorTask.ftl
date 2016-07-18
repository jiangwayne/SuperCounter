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
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;分配安装任务</div>
</div>
<form action="allocateErectorTask" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
  <tr>
    <td width="200" align="right" class="title2"><strong>柜台</strong></td>
    <td class="con2"><select name="select1" class="ip" id="select3">
        <select id="counter" name="orgCounterId" class="ip" id="select3">
        <#if counterList??>
          <#list counterList as s>
              <option value="${s.id?if_exists}">${s.name?if_exists}</option>
          </#list>
        </#if>
        </select>
    </select></td>
  </tr>
  <tr>
    <td width="200" align="right" class="title2"><strong>任务类型</strong></td>
    <td class="con2"><select name="taskType" class="ip" id="select3">
      <option value="1">上市任务</option>
      <option value="2">上市补货任务</option>
      <option value="3">日常补货任务</option>
    </select></td>
  </tr>
  <tr>
    <td width="200" align="right" class="title2"><strong>选择柜台任务编号</strong></td>
    <td class="con2">
    	<div width="450">
        <ul>
          <#if counterOrderList??>
            <#list counterOrderList as s>
                <li><input  value="${s.orderId?if_exists}" type="checkbox" name="orderCounterIds"><span>${s.orderCounterNo?if_exists}</span></li>
            </#list>
          </#if>
        </ul>
      </div>
    </td>
  </tr>
  <tr>
    <td align="right" class="title2">安装工程师</td>
    <td class="con2">
    <select name="select" class="ip" id="select3">
      <option>ＢＡ</option>
      <option>安装公司</option>     
    </select>
    <select name="select" class="ip" id="select3">
      <option>山东</option>
      <option>河北</option>      
      <option>北京</option>     
      <option>上海</option>                          
    </select>
    <select name="select" class="ip" id="select3">
      <option>北京</option>     
      <option>上海</option>     
      <option>济南</option>     
      <option>青岛</option>     
      <option>福州</option>     
      <option>哈尔滨</option>                             
    </select>
    <select name="userSetupId" class="ip" id="userSetupId">
    <#if userList??>
      <#list userList as s>
          <option value="${s.id?if_exists}" <#if model.userSetupId = s.id>selected</#if>>${s.name?if_exists}</option>
      </#list>
    </#if>
    </select></td>
  </tr>
  <tr>
    <td align="right" class="title2">时间</td>
    <td class="con2"><input id="J-xl" name="setupTime" type="text"  value="" size="40" />
	    <script type="text/javascript">
        laydate({
            elem: '#J-xl'
        });
    </script>
	    
    </td>
  </tr>
  <tr>
    <td align="right" class="title2">备注</td>
    <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2"></textarea></td>
  </tr>
  <tr>
    <td align="right" class="title2">&nbsp;</td>
    <td class="con2"><input type="submit" name="button4" id="button4" value="提交" class="blue" />
    <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listErectorTask'" /></td>
  </tr>
</table>
</form>

<#--<div style="margin: 10px"><strong> 安装计划单：</strong></div>-->
<#--<hr><br>-->
<#--<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">-->
  <#--<tbody><tr class="title1">-->
    <#--<td width="5">&nbsp;</td>-->
    <#--<td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>-->
    <#--<td width="150">柜台</td>-->
    <#--<td>安装工程师</td>-->
    <#--<td align="center">安装日期</td>-->
    <#--<td align="center" width="150">创建日期</td>-->
    <#--<td align="center" width="150">生成订单</td>-->
    <#--<td align="center" width="150">操作</td>-->
  <#--</tr>-->
  <#--<tr class="con">-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td bgcolor="#FFFFFF"><input name="checkbox3" id="checkbox3" type="checkbox"></td>-->
    <#--<td bgcolor="#FFFFFF"><a href="orderProductTaskDetail.html" target="_blank">ＨＲ南方商城柜台</a></td>-->
    <#--<td bgcolor="#FFFFFF">Jason</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-28</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>-->
    <#--<td align="center" bgcolor="#FFFFFF"><a href="#">生单</a></td>-->
    <#--<td align="center" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>-->
  <#--</tr>-->
    <#--<tr class="con">-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td bgcolor="#FFFFFF"><input name="checkbox3" id="checkbox3" type="checkbox"></td>-->
    <#--<td bgcolor="#FFFFFF"><a href="orderProductTaskDetail.html" target="_blank">ＨＲ南方商城柜台</a></td>-->
    <#--<td bgcolor="#FFFFFF">Jason</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-28</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>-->
    <#--<td align="center" bgcolor="#FFFFFF"><a href="#">生单</a></td>-->
    <#--<td align="center" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>-->
  <#--</tr>-->
    <#--<tr class="con">-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td bgcolor="#FFFFFF"><input name="checkbox3" id="checkbox3" type="checkbox"></td>-->
    <#--<td bgcolor="#FFFFFF"><a href="orderProductTaskDetail.html" target="_blank">ＨＲ南方商城柜台</a></td>-->
    <#--<td bgcolor="#FFFFFF">Jason</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-28</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">2015-07-07&nbsp;7:43:31</td>-->
    <#--<td align="center" bgcolor="#FFFFFF"><a href="#">生单</a></td>-->
    <#--<td align="center" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>-->
  <#--</tr>-->
  <#--<tr class="con">-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>-->
    <#--<td bgcolor="#FFFFFF">-->
      <#--<input name="button" id="button" value="全部删除" class="blue" type="button">-->
    <#--</td>-->
    <#--<td bgcolor="#FFFFFF">-->
	    <#--<input name="button" id="button" value="全部分配" class="blue" type="button">-->
	<#--</td>-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">&nbsp;</td>-->
    <#--<td align="center" bgcolor="#FFFFFF">&nbsp;</td>-->
  <#--</tr>-->
</tbody></table>
</body>
</html>
