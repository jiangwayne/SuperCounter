<html>
<head>
    <title>Untitled Document</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="Admin_Style.css">
    <script language="javascript">
        function gocheck2(){
            if (form1.name.value==""){
                alert("请输入用名！");
                form1.name.focus();
                return ;
            }
            if (form1.pwd.value==""){
                alert("请输入新密码！！");
                form1.pwd.focus();
                return ;
            }
            if (form1.pw2.value==""){
                alert("请确认新密码！");
                form1.pw2.focus();
                return ;
            }
            if (form1.pw2.value!=form1.pwd.value){
                alert("两次输入不一至请重新输入！");
                form1.pwd.focus();
                return ;
            }
            form1.submit();
        }
    </script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="/gtb/user/add">
    <table width="643" border="0" align="center" cellpadding="2" cellspacing="1" class="border">
        <tr align="center">
            <td colspan="5" class="title" height="22">增加用户</td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">用户名：</td>
            <td class="tdbg" height="25" colspan="4">
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">组织：</td>
            <td class="tdbg" height="25" colspan="4">
                <select name="orgId">
                    <select name="parentId">
                    <#if list??>
                        <#list list as s>
                        <option value="${s.id?if_exists}">${s.name?if_exists}
                        </#list>
                    </#if>
                    </select>
                </select>
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">角色：</td>
            <td class="tdbg" height="25" colspan="4">
                <select name="roleId">
                    <option value="1">admin</option>
                    <option value="2">品牌经理</option>
                    <option value="3">柜台经理</option>
                    <option value="4">供应商</option>
                    <option value="5">陈列师</option>
                    <option value="6">物流</option>
                </select>
            </td>
        </tr>

        <tr>
            <td width="16%" class="tdbg" height="30" align="center">用户密码：</td>
            <td class="tdbg" height="30" colspan="4">
                <input type="password" name="pwd">
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">密码确认：</td>
            <td class="tdbg" height="25" colspan="4">
                <input type="password" name="pw2">
            </td>
        </tr>
        <tr align="center">
            <td colspan="5" class="tdbg" height="53">
                <input type="button" name="Button" value=" 增 加 " onclick="gocheck2()">
                <input type="reset" name="Submit2" value=" 重 填 ">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
