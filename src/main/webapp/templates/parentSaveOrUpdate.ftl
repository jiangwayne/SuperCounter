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
  <div class="icon">当前位置：添加父件</div>
</div>
<form id="form1" method="get" action="${base_addr}/gtb/parent/saveOrUpdate">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
<input type="hidden" name="id" id="id" value="${oc.id?if_exists}">
	<tr>
	<td align="right" class="title2">供应商</td>
	<td class="con2">
		<select name="orgId" class="ip" id="orgId">
			<#if orgList??>
			<#list orgList as s>
				<option value="${s.id?if_exists}" <#if oc.orgId = s.id>selected</#if>>${s.name?if_exists}</option>
			</#list>
			</#if>
		</select>
	</td>
	</tr>
	<tr>
		<td align="right" class="title2">类型</td>
		<td class="con2">
			<select name="type" class="ip" id="type">
			  <option value=1 <#if oc.type = 1>selected</#if>>图片</option>
			  <option value=2 <#if oc.type = 2>selected</#if>>道具</option>
			  <option value=3 <#if oc.type = 3>selected</#if>>灯片</option>
			</select>
		</td>
	</tr>
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
	  <td align="right" class="title2">出血长</td>
	  <td class="con2"><input type="text" name="lengthUp" class="input1" value="${oc.lengthUp?if_exists}" size="40"></td>
	</tr>
	<tr>
		<td align="right" class="title2">出血宽</td>
		<td class="con2"><input name="widthUp" type="text" class="input1" id="textfield" value="${oc.widthUp?if_exists}" size="40" /></td>
	</tr>
	<tr>
		<td align="right" class="title2">出血高</td>
		<td class="con2"><input name="heightUp" type="text" class="input1" id="textfield" value="${oc.heightUp?if_exists}" size="40" /></td>
	</tr>
	<tr>
		<td align="right" class="title2">材质</td>
		<td class="con2"><input name="content" type="text" class="input1" id="textfield" value="${oc.content?if_exists}" size="40" /></td>
	</tr>
	<tr>
		<td align="right" class="title2">内容</td>
		<td class="con2"><input name="material" type="text" class="input1" id="textfield" value="${oc.material?if_exists}" size="40" /></td>
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
<#if oc.id??>
	<div class="icon">包括子件</div> <br>
	<strong>添加子件：</strong>
			<select id="addChildIdSelect">  
				<#if childList??>
				<#list childList as s>
					<option value="${s.id?if_exists}" >${s.name?if_exists}</option>
				</#list>
				</#if>  
	        </select>  
	        数量：<input type="text" id="childCount" class="input1" value="1">
	        <input name="button" id="button" value="添加" class="blue" type="button" onclick="addChildRel()">
	<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
	  <tbody><tr class="title1">
	    <td width="5">&nbsp;</td>
	    <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
	    <td width="150">子件名称</td>
	    <td width="150">数量</td>
	    <td>图片</td>
	    <td>二维码</td>
	    <td align="center" width="150">添加日期</td>
	    <td align="center" width="150">操作</td>
	  </tr>
	  <#if relChildList??>
		<#list relChildList as s>
		  <tr class="con">
		    <td bgcolor="#FFFFFF">&nbsp;</td>
		    <td bgcolor="#FFFFFF">${s_index+1}</td>
		    <td bgcolor="#FFFFFF">${s.objectChild.name?if_exists}</td>
		    <td bgcolor="#FFFFFF">${s.objChildCount?if_exists}</td>
		    <td bgcolor="#FFFFFF"><img src="${base_addr}/gtb/file/downloadFile?fileName=${s.objectChild.picUrl?if_exists}" height="60" width="60"></td>
		    <td bgcolor="#FFFFFF"><a href=# onClick="javascript:window.open('${base_addr}/gtb/file/toQRCode?qrCode=${s.objectChild.qrCode?if_exists}','','width=600,height=400,location=no,toolbar=no, status=no, menubar=no, resizable=yes, scrollbars=yes');return false;">${s.objectChild.qrCode?if_exists}</a></td>
		    <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
		    <td align="center" bgcolor="#FFFFFF"><a href="javascript:deleteRel(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a></td>
		  </tr>
		</#list>
		</#if>  
	  
	</tbody></table>
</#if>
</form>
</body>
<script>
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
	function addChildRel(){
		var childId = $('#addChildIdSelect').val();
		var count = $('#childCount').val();
		if(count <= 0){
			alert("数量不正确");
		}
		var parentId = "${oc.id?if_exists}";
		$.ajax({
			url: '${base_addr}/gtb/parent/addChildRel' ,
	        secureuri: false,
	        data: {childId: childId, parentId: parentId, count: count},
	        dataType: 'json',
	        success: function (data) {
	        	//eval( "data = " + data );
	        	if(data.success){
	        		location.reload();
	        	}else{
	        		alert("添加失败--"+data.msg);
	        	}
	        },
	        error: function (data) {
	            alert("添加失败--"+data);
	        }
	    });
	}
	function deleteRel(id){
		if(!confirm('确定删除？')){
			return;
		}
		$.ajax({
			url: '${base_addr}/gtb/parent/deleteChildRel' ,
	        secureuri: false,
	        data: {id: id},
	        dataType: 'json',
	        success: function (data) {
	        	//eval( "data = " + data );
	        	if(data.success){
	        		alert('删除成功');
	        		location.reload();
	        	}
	        },
	        error: function (data) {
	            alert("删除失败--"+data);
	        }
	    });
	}
</script>
</html>
