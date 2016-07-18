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
  <div class="icon">当前位置：生产加工单列表</div>
</div>
<span class="con2">
<input name="button2" id="button2" value="添加生产加工单" class="blue" onclick="location.href='toEdit'" type="button">
<br>
<br>
</span>
<form id="form1" method="post" action="list"  class="form_search">
<input class=input1 id="page" name="page" type="hidden">
<input value="" id="pageSize" name="pageSize" type="hidden">
供应商:<select name="supplierId" class="ip" id="supplierIdSelect">
<#if supplierList??>
<#list supplierList as s>
    <option value="${s.id?if_exists}" <#if s.id==supplierId>selected</#if>>${s.name?if_exists}</option>
</#list>
</#if>
</select>
      <input type="submit" name="Submit" value="查询">
  </form>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"></td>
    <td width="150">订单号</td>
    <td width="150">供应商</td>
    <td align="center" width="150">创建日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if list??>
	<#list list as s>
      <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display"> 
	      <td bgcolor="#FFFFFF">&nbsp;</td>
	      <td bgcolor="#FFFFFF">${s_index+1}</td>
	      <td bgcolor="#FFFFFF">${s.orderSupplierNo?if_exists}</td>
	      <td bgcolor="#FFFFFF">${s.orgSupplier.name?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	      <td align="center" bgcolor="#FFFFFF">
		      <a href="toEditDtl?orderSupplierId=${s.id?if_exists}" target="_self">详细</a>
	      </td>
    
      </tr>
  </#list>
  </#if>
</tbody>
</table>
<#if (page>1) >
<a href="#" onclick="toPage(1)">第一页</a>｜
<a href="#" onclick="toPage(${page-1})">上一页</a>｜
<#else>
第一页｜上一页
</#if>
<#if (page<pages) >
<a href="#" onclick="toPage(${page+1})">下一页</a>｜
<a href="#" onclick="toPage(${pages})">最后一页</a>
<#else>
下一页｜最后一页
</#if>


</body>
<script>
$(function (){
	//初始化js
});
function toPage(page){
	$("#page").val(page);
	$("#form1").submit();
}
</script>
</html>