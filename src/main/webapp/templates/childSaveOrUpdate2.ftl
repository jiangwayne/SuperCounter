<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="${base_addr}/gtb/child/saveOrUpdate">
<input type="hidden" name="id" id="id" value="${oc.id?if_exists}">
  <table width="643" border="0" align="center" cellpadding="2" cellspacing="1" class="border">
    <tr align="center"> 
      <td colspan="5" class="title" height="22">子件信息</td>
    </tr>
    <tr> 
      <td width="16%" class="tdbg" height="25" align="right">名称：</td>
      <td class="tdbg" height="25" colspan="4"> 
        <input type="text" name="name" value="${oc.name?if_exists}">
      </td>
      <td width="16%" class="tdbg" height="25" align="right">编码：</td>
      <td class="tdbg" height="25" colspan="4"> 
        <input type="text" name="qrCode" disabled  value="<#if (oc.id??)>${oc.qrCode?if_exists}<#else>(系统自动生成)</#if>">
      </td>
    </tr>
    <tr> 
      <td width="16%" class="tdbg" height="30" align="right">长：</td>
      <td class="tdbg" height="30" colspan="4"> 
      	<input type="text" name="length" value="${oc.length?if_exists}">
      </td>
      <td width="16%" class="tdbg" height="30" align="right">宽：</td>
      <td class="tdbg" height="30" colspan="4"> 
      	<input type="text" name="width" value="${oc.width?if_exists}">
      </td>
    </tr>
    <tr> 
	    <td width="16%" class="tdbg" height="30" align="right">高：</td>
	    <td class="tdbg" height="30" colspan="4"> 
	    	<input type="text" name="height" value="${oc.height?if_exists}">
	    </td>
	    <td width="16%" class="tdbg" height="30" align="right">图片：</td>
	    <td class="tdbg" height="30" colspan="4"> 
	    	<input type="text" name="picUrl" value="${oc.picUrl?if_exists}">
	    </td>
	</tr>
    <tr align="center"> 
      <td colspan="5" class="tdbg" height="53"> 
        <input type="submit" name="Button" value=" 提交 ">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
