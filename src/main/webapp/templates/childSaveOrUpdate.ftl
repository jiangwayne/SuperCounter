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
  <div class="icon">当前位置：添加子件</div>
</div>
<form name="form1" method="post" action="${base_addr}/gtb/child/saveOrUpdate">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
<input type="hidden" name="id" id="id" value="${oc.id?if_exists}">
  <tr>
    <td align="right" class="title2">名称</td>
    <td class="con2"><input type="text" name="name"  class="input1" value="${oc.name?if_exists}" size="40"></td>
  </tr>
  <tr>
	  <td align="right" class="title2">二维码</td>
	  <td class="con2">
	  <input type="text" name="qrCode" class="input1" disabled size="40" value="<#if (oc.id??)>${oc.qrCode?if_exists}<#else>(系统自动生成)</#if>">
	  </td>
	</tr>
  <tr>
	  <td align="right" class="title2">长</td>
	  <td class="con2"><input type="text" name="length" class="input1" value="${oc.length?if_exists}" size="40"></td>
	</tr>
  <tr>
    <td align="right" class="title2">宽</td>
    <td class="con2"><input name="width" type="text" class="input1" id="textfield" value="${oc.width?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">高</td>
    <td class="con2"><input name="height" type="text" class="input1" id="textfield" value="${oc.height?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">图片</td>
    <td class="con2">
    	<input type="hidden" id="picUrl" name="picUrl" value="${oc.picUrl?if_exists}">
    	<img width="100" height="100" id="picUrlShow" src="${base_addr}/gtb/file/downloadFile?fileName=${oc.picUrl?if_exists}">
    		<input type="file" id="fileToUpload" name="fileToUpload" >
    		<input type="button" value="上传" onclick="ajaxUploadFile()" /> 
    </td>
  </tr>   
  <tr>
    <td align="right" class="title2">备注</td>
    <td class="con2"><textarea name="remark" cols="40" rows="5" class="input2" id="textfield2">${oc.remark?if_exists}</textarea></td>
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
	function submitForm(){
		alert('提交表单');
		$("#form1").submit();
	}
	function ajaxUploadFile(){
		$.ajaxFileUpload({
			url: '${base_addr}/gtb/file/uploadFile' ,
	        secureuri: false,
	        data: {fileName: 'fileToUpload'},
	        fileElementId: 'fileToUpload',
	        dataType: 'text',
	        success: function (data) {
	        	data = formatUpFileRetData(data);
	        	if(data.success){
	        		$('#picUrl').val(data.msg);
	        		$('#picUrlShow').attr("src","${base_addr}/gtb/file/downloadFile?fileName="+data.msg);
	        	}
	        },
	        error: function (data) {
	            alert("上传出错---"+data);
	        }
	    });
	}
	
	function formatUpFileRetData(data){
		var start = data.indexOf(">");  
        if(start != -1) {  
            var end = data.indexOf("<", start + 1);  
            if(end != -1) {  
                data = data.substring(start + 1, end);  
            }  
        }  
       eval( "data = " + data );  
       return data;
	}
</script>
</html>
