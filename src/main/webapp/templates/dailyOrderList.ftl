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
  <div class="icon">当前位置：日常补货列表</div>
</div>
<span class="con2">
<input name="button2" id="button2" value="添加日常补货" class="blue" onclick="location.href='toEdit'" type="button">
<br>
<br>
</span>
<form id="form1" method="post" action="list"  class="form_search">
<input class=input1 id="page" name="page" type="hidden">
<input value="" id="pageSize" name="pageSize" type="hidden">
  柜台：<select name="counterOrgId" class="ip" id="counterOrgId">
  		<option value=""></option>
		<#if counterList??>
			<#list counterList as s>
	            <option value="${s.id?if_exists}" <#if counterOrgId = s.id>selected</#if>>${s.name?if_exists}</option>
			</#list>
		</#if>
	    </select>
	父件：<select name="objParentId" class="ip" id="objParentId" >
			<option value=""></option>
			<#if objParentList??>
			<#list objParentList as s>
		        <option value="${s.id?if_exists}" <#if objParentId = s.id>selected</#if>>${s.name?if_exists}</option>
			</#list>
		</#if>
		</select>
	供应商：<select name="supplyOrgId" class="ip" id="supplyOrgId">
  		<option value=""></option>
		<#if supplyList??>
			<#list supplyList as s>
	            <option value="${s.id?if_exists}" <#if supplyOrgId = s.id>selected</#if>>${s.name?if_exists}</option>
			</#list>
		</#if>
	    </select>
      <input type="submit" name="Submit" value="查询">
  </form>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"></td>
    <td width="150">编号</td>
    <td align="center">柜台名</td>
    <td align="center">供应商</td>
    <td align="center">父件</td>
    <td align="center">数量</td>
    <td align="center">备注</td>
    <td align="center" width="150">添加日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if list??>
	<#list list as s>
      <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display"> 
	      <td bgcolor="#FFFFFF">&nbsp;</td>
	      <td bgcolor="#FFFFFF">${s_index+1}</td>
	      <td bgcolor="#FFFFFF">${s.dailyOrderNo?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.orgCounter.name?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.supplyOrg.name?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.objParent.name?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.comment?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	      <td align="center" bgcolor="#FFFFFF">
		      <a href="toEdit?id=${s.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
		      <a href="javascript:deleteOne(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
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
function deleteOne(id){
	if(!confirm('确定删除？')){
		return;
	}
	$.ajax({
		url: '${base_addr}/gtb/dailyOrder/delete' ,
        secureuri: false,
        data: {id: id},
        dataType: 'json',
        success: function (data) {
        	if(data.success){
        		alert('删除成功');
        		location.href='list';
        	}
        },
        error: function (data) {
            alert("删除失败--"+data);
        }
    });
}
</script>
</html>