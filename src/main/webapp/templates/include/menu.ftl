<!--#include file="admin_check.asp"-->
<html>
<head>
<title>title</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type=text/css>
body  { background:#39867B; margin:0px; font:9pt 宋体; }
table  { border:0px; }
td  { font:normal 12px 宋体; }
img  { vertical-align:bottom; border:0px; }
a  { font:normal 12px 宋体; color:#000000; text-decoration:none; }
a:hover  { color:#cc0000;text-decoration:underline; }
.sec_menu  { border-left:1px solid white; border-right:1px solid white; border-bottom:1px solid white; overflow:hidden; background:#C6EBDE; }
.menu_title  { }
.menu_title span  { position:relative; top:2px; left:8px; color:#39867B; font-weight:bold; }
.menu_title2  { }
.menu_title2 span  { position:relative; top:2px; left:8px; color:#cc0000; font-weight:bold; }
.STYLE1 {
	color: #FBFDFF;
	font-family: "隶书";
	font-size: 24px;
}
</style>
<script language=javascript1.2>
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0">
<table width=100% cellpadding=0 cellspacing=0 border=0 align=left>
	<tr>
		<td valign=top><table width=158 border="0" align=center cellpadding=0 cellspacing=0>
				<tr>
					<td height=42 align="center" valign=middle><span class="STYLE1">欢迎光临</span></td>
				</tr>
			</table>
			<table cellpadding=0 cellspacing=0 width=158 align=center>
				<tr>
					<td height=25 class=menu_title onMouseOver=this.className='menu_title2'; onMouseOut=this.className='menu_title'; background=${base_addr}/static/imagesz/title_bg_quit.gif id=menuTitle0><span><a href="total.asp" target=main><b> 统计信息</b></a> | <a href=login.asp target=_top><b>退出</b></a></span> </td>
				</tr>
				<tr>
					<td style="display:" id='submenu0'><div class=sec_menu style="width:158">
							<table cellpadding=0 cellspacing=0 align=center width=130>
								<tr>
									<td height=20>用户名：</td>
								</tr>
								<tr>
									<td height=20>权&nbsp;&nbsp;限 ：</td>
								</tr>
							</table>
						</div>
					  <div  style="width:158"></div></td>
				</tr>
			</table>
			<table cellpadding=0 cellspacing=0 width=158 align=center>
				<tr>
					<td height=25 class=menu_title onMouseOver=this.className='menu_title2'; onMouseOut=this.className='menu_title'; background="${base_addr}/static/imagesz/admin_left_1.gif" id=menuTitle1 onClick="showsubmenu(1)" style="cursor:hand;"><span>柜台宝</span> </td>
				</tr>
				<tr>
					<td style="display:none" id='submenu1'><div class=sec_menu style="width:158">
							<table width=130 height="100" align=center cellpadding=0 cellspacing=0>
								<tr>
									<td height=20><a href="${base_addr}/gtb/user/list" target="main">用户管理</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">陈列手册</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">日常信息</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">柜台样式</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">子件列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">父件列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">家具列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">组织列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">快递单列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">生产加工单列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">报价单列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">库存列表</a></td>
								</tr>
								<tr>
									<td height=20><a href="${base_addr}/gtb/userList" target="main">分配任务</a></td>
								</tr>
					  </table>
						</div>
					  <div  style="width:158"></div></td>
				</tr>
			</table>
<table cellpadding=0 cellspacing=0 width=158 align=center>
				<tr>
					<td height=25 class=menu_title onMouseOver=this.className='menu_title2'; onMouseOut=this.className='menu_title'; background="${base_addr}/static/imagesz/admin_left_7.gif" id=menuTitle7 onClick="showsubmenu(7)" style="cursor:hand;"><span>操作管理</span></td>
				</tr>
				<tr>
					<td style="display:none" id='submenu7'><div class=sec_menu style="width:158">
							<table cellpadding=0 cellspacing=0 align=center width=130>
								<tr>
									<td height=20><a href="password.asp" target="main">密码设置</a></td>
								</tr>
							</table>
						</div>
						<div  style="width:158">
							<table cellpadding=0 cellspacing=0 align=center width=130>
								<tr>
									<td height=20></td>
								</tr>
							</table>
						</div></td>
				</tr>
		  </table>
			<table cellpadding=0 cellspacing=0 width=158 align=center>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
</table>
</body>
</html>
