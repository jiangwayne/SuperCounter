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
  <div class="icon">当前位置：子件列表</div>
</div>
<span class="con2">
<input name="button2" id="button2" value="添加子件" class="blue" onclick="location.href='toEdit'" type="button">
<br>
<br>
</span>
<form id="form1" method="post" action="list"  class="form_search">
<input class=input1 id="page" name="page" type="hidden">
<input value="" id="pageSize" name="pageSize" type="hidden">
      名称：<input class=input1 id="name" name="name" value="${name?if_exists}" size=15>
      <input type="submit" name="Submit" value="查询">
  </form>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"></td>
    <td width="150">名称</td>
    <td align="center">长宽高</td>
    <td align="center">二维码</td>
    <td align="center" width="150">添加日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if list??>
	<#list list as s>
      <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display"> 
	      <td bgcolor="#FFFFFF">&nbsp;</td>
	      <td bgcolor="#FFFFFF">${s_index+1}</td>
	      <td bgcolor="#FFFFFF">${s.name?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF">${s.length?if_exists}*${s.width?if_exists}*${s.height?if_exists}</td>
	      <td align="center" bgcolor="#FFFFFF"><a href=# onClick="javascript:window.open('${base_addr}/gtb/file/toQRCode?qrCode=${s.qrCode?if_exists}','','width=600,height=400,location=no,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');return false;">${s.qrCode?if_exists}</a></td>
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