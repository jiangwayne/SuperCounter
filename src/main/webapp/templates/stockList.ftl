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
  <div class="icon">当前位置：库存列表</div>
</div>
<span class="con2">
<br>
<br>
</span>
<form id="form1" method="post" action="list"  class="form_search">
<input class=input1 id="page" name="page" type="hidden">
<input value="" id="pageSize" name="pageSize" type="hidden">
类型：<select name="type" id="type">
		<option value="1" <#if type = 1>selected</#if>>父件</option>
		<option value="2" <#if type = 2>selected</#if>>子件</option>
	</select>
      名称：<input class=input1 id="name" name="name" value="${name?if_exists}" size=15>
      <input type="submit" name="Submit" value="查询">
  </form>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"></td>
    <td width="150">名称</td>
    <td >供应商</td>
    <td >数量</td>
    <td >图片</td>
    <td  width="150">添加日期</td>
  </tr>
  <#if list??>
	<#list list as s>
      <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display"> 
	      <td bgcolor="#FFFFFF">&nbsp;</td>
	      <td bgcolor="#FFFFFF">${s_index+1}</td>
	      <td bgcolor="#FFFFFF">${s.goodName?if_exists}</td>
	      <td bgcolor="#FFFFFF">${s.orgName?if_exists}</td>
	      <td bgcolor="#FFFFFF">${s.goodCount?if_exists}</td>
	      <td  bgcolor="#FFFFFF">
	      	<a href="${base_addr}/gtb/file/downloadFile?fileName=${s.picUrl?if_exists}" target="_blank">
	      		<img width="60" height="60" id="picUrlShow" src="${base_addr}/gtb/file/downloadFile?fileName=${s.picUrl?if_exists}">
	      	</a>
	      </td>
	      <td  bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
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
		url: '${base_addr}/gtb/child/delete' ,
        secureuri: false,
        data: {id: id},
        dataType: 'text',
        success: function (data) {
        	eval( "data = " + data );
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