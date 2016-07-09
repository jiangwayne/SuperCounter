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
            form1.submit();
        }
    </script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="add">
    <table width="643" border="0" align="center" cellpadding="2" cellspacing="1" class="border">
        <tr align="center">
            <td colspan="5" class="title" height="22">增加组织</td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">组织名：</td>
            <td class="tdbg" height="25" colspan="4">
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">组织类型：</td>
            <td class="tdbg" height="25" colspan="4">
                <select name="type">
                    <option value="1">品牌</option>
                    <option value="2">柜台</option>
                    <option value="3">供应商</option>
                    <option value="4">物流</option>
                    <option value="5">安装商</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">上级组织：</td>
            <td class="tdbg" height="25" colspan="4">
                <select name="parentId">
                    <#if list??>
                        <#list list as s>
                            <option value="${s.id?if_exists}">${s.name?if_exists}
                        </#list>
                    </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">地址：</td>
            <td class="tdbg" height="25" colspan="4">
                <input type="text" name="address">
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="30" align="center">电话：</td>
            <td class="tdbg" height="30" colspan="4">
                <input type="text" name="phone">
            </td>
        </tr>
        <tr>
            <td width="16%" class="tdbg" height="25" align="center">email：</td>
            <td class="tdbg" height="25" colspan="4">
                <input type="text" name="email">
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
