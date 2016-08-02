<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>right</title>
<link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
<link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
<script src="${base_addr}/static/js/jquery-1.3.2.min.js"></script>
<script src="${base_addr}/static/js/myjs.js"></script>
<script src="${base_addr}/static/js/ajaxfileupload.js"></script>
</head>

<body>
<div class="loc">
  <div class="icon">当前位置：添加日常补货</div>
</div>
<form name="form1" method="post" action="${base_addr}/gtb/dailyOrder/saveOrUpdate">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
<input type="hidden" name="id" id="id" value="${oc.id?if_exists}">
  <tr>
    <td align="right" class="title2">柜台</td>
    <td class="con2">
	    <select name="orgCounterId" class="ip" id="orgCounterId">
		<#if counterList??>
			<#list counterList as s>
	            <option value="${s.id?if_exists}" <#if oc.orgCounterId = s.id>selected</#if>>${s.name?if_exists}</option>
			</#list>
		</#if>
	    </select>
    </td>
  </tr>
  <tr>
	  <td align="right" class="title2">父件</td>
	  <td class="con2">
		    <select name="objParentId" class="ip" id="objParentId" onchange="objParentIdChange()">
			<#if objParentList??>
				<#list objParentList as s>
		            <option value="${s.id?if_exists}" <#if oc.objParentId = s.id>selected</#if>>${s.name?if_exists}</option>
				</#list>
			</#if>
		    </select>
	  </td>
  </tr>
  <tr>
	  <td align="right" class="title2">供应商</td>
	  <td class="con2"><input disabled id="supplyName" value="${oc.supplyOrg.name?if_exists}" size="40" /></td>
	</tr>   
  <tr>
    <td align="right" class="title2">数量</td>
    <td class="con2"><input name="objParentCount" type="text" class="input1" id="objParentCount" value="${oc.objParentCount?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">备注</td>
    <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${oc.comment?if_exists}</textarea></td>
  </tr>
  <tr>
    <td align="right" class="title2">&nbsp;</td>
    <td class="con2">
	    <input type="submit" name="button4" id="button4" value="提交" class="blue" />
	    <input type="button" name="button" id="button" value="返回" class="hui" onclick="history.go(-1)" />
    </td>
  </tr>
</table>
</form>
</body>
<script>
$(function (){
	if($('#supplyName').val() == ''){
		objParentIdChange();
	}
});
	function submitForm(){
		alert('提交表单');
		$("#form1").submit();
	}
	function objParentIdChange(){
		var objParentId = $('#objParentId').val();
		$('#supplyName').val('');
		$.ajax({
			url: '${base_addr}/gtb/dailyOrder/selectSupplyNameByObjParentId' ,
	        secureuri: false,
	        data: {objParentId : objParentId},
	        dataType: 'json',
	        success: function (data) {
	        	if(data.success){
	        		$('#supplyName').val(data.msg);
	        	}
	        },
	        error: function (data) {
	        }
	    });
		
	}
</script>
</html>
