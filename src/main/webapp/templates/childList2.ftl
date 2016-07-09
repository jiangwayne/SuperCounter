<HTML><head>
<title>title</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${base_addr}/static/css/gtb_list.css" rel="stylesheet" type="text/css" />
<script src="${base_addr}/static/js/jquery-1.3.2.min.js"></script>
</head>
<BODY leftMargin=0 text=#000000 topMargin=0 marginheight="0" marginwidth="0">
	<form id="form1" method="post" action="list"  class="form_search">
	<input class=input1 id="page" name="page" type="hidden">
	<input value="" id="pageSize" name="pageSize" type="hidden">
          名称：<input class=input1 id="name" name="name" value="${name?if_exists}" size=15>
          编码：<input class=input1 id="qrCode" name="qrCode" value="${qrCode?if_exists}" size=15>
          <input type="submit" name="Submit" value="查询">
      </form>
      <br>
      <div class="form_search"><a href="toEdit">添加</a> </div>
      <table class="table_display" colspan=8 cellpadding=1 border=0 width="90%" cellspacing="1"  bgcolor="#006699" align="center">
        <tr class="tr_top"> 
          <td width="80">序号</td>
          <td width="100">名称</td>
          <td width="100">编码</td>
          <td width="60">长</td>
          <td width="60">宽</td>
          <td width="60">高</td>
          <td width="60">图片</td>
          <td width="60"></td>
        </tr>
        <#if list??>
      	<#list list as s>
	        <tr onMouseOut="this.style.backgroundColor='ffffff'" onMouseOver="this.style.backgroundColor='FFECA2'" class="tr_display"> 
	          <td >${s_index+1}</td>
	          <td >${s.name?if_exists}</td>
	          <td >${s.qrCode?if_exists}</td>
	          <td >${s.length?if_exists}</td>
	          <td >${s.width?if_exists}</td>
	          <td >${s.height?if_exists}</td>
	          <td >${s.picUrl?if_exists}</td>
	          <td >
	          	<a href="toEdit?id=${s.id?if_exists}">编辑</a>
	          </td>
	        </tr>
        </#list>
        </#if>
      </table>
      <div class="form_search">
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
		</div>

</BODY>
<script>
$(function (){
	//初始化js
});
function toPage(page){
	$("#page").val(page);
	$("#form1").submit();
}
</script>
</HTML>
