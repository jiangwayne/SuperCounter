<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
a:link { color:#000000;text-decoration:none}
a:hover {color:#666666;}
a:visited {color:#000000;text-decoration:none}

td {FONT-SIZE: 9pt; FILTER: dropshadow(color=#FFFFFF,offx=1,offy=1); COLOR: #000000; FONT-FAMILY: "宋体"}
img {filter:Alpha(opacity:100); chroma(color=#FFFFFF)}
</style>
<base target="main">
<script>
function preloadImg(src)
{
	var img=new Image();
	img.src=src
}
preloadImg("${base_addr}/static/imagesz/admin_top_open.gif");

var displayBar=true;
function switchBar(obj)
{
	if (displayBar)
	{
		parent.frame.cols="0,*";
		displayBar=false;
		obj.src="${base_addr}/static/imagesz/admin_top_open.gif";
		obj.title="打开左边管理菜单";
	}
	else{
		parent.frame.cols="180,*";
		displayBar=true;
		obj.src="${base_addr}/static/imagesz/admin_top_close.gif";
		obj.title="关闭左边管理菜单";
	}
}
</script>
</head>

<body background="${base_addr}/static/imagesz/admin_top_bg.gif" leftmargin="0" topmargin="0">
<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0>
<tr valign=middle>
	<td width=50>
	<img onClick="switchBar(this)" src="${base_addr}/static/imagesz/admin_top_close.gif" title="关闭左边管理菜单" style="cursor:hand">
	</td>

	<td width=40>
		<img src="${base_addr}/static/imagesz/admin_top_icon_1.gif">
	</td>
	<td width=100><a href="password.asp" target="main">修改密码</a></td>
	
	<!--
	<td width=40><img src="${base_addr}/static/imagesz/admin_top_icon_5.gif"></td>
	<td width=100><a href="http://mail.chem18.com/mg/login.asp" target="_blank">邮箱管理</a></td>
	---->
<!--
	<td width=40>
		<img src="${base_addr}/static/imagesz/admin_top_icon_2.gif">
	</td>
	<td width=100>
		<a href="#">我的通讯录</a>
	</td>
	<td width=40>
		<img src="${base_addr}/static/imagesz/admin_top_icon_3.gif">
	</td>
	<td width=100>
		<a href="#">我的备忘录</a>
	</td>
	<td width=40>
		<img src="${base_addr}/static/imagesz/admin_top_icon_4.gif">
	</td>
	<td width=100>
		<a href="#">我的短信息</a>
	</td>
-->	
	<td align="right">门店宝后台管理系统</td>
</tr>
</table>
</body>
