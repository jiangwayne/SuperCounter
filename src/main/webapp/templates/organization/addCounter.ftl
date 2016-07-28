<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>right</title>
    <link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
    <link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
    <script src="${base_addr}/static/js/myjs.js"></script>
    <script src="${base_addr}/static/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#brand").val('${model.parentId?if_exists}');
            <#if model.orgId?if_exists>$("#brand").attr("disabled", "disabled");</#if>

            $("#mediaType").val('${model.mediaType?if_exists}');
            $("#styleId").val('${model.styleId?if_exists}');


        });
    </script>

</head>

<body>
<div class="loc">
  <div class="icon">当前位置：&nbsp;&gt;&nbsp;添加柜台</div>
</div>
<form action="addCounter" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
  <tr>
    <td align="right" class="title2">品牌</td>
      <td class="con2">
          <select id="brand" name="orgId" class="ip" id="select3">
          <#if brandList??>
            <#list brandList as s>
                <option value="${s.id?if_exists}">${s.name?if_exists}</option>
            </#list>
          </#if>
          </select>
      </td>
  </tr>
  <tr>
    <td align="right" class="title2">媒介类型</td>
    <td class="con2">
      <select id="mediaType" name="mediaType" class="ip" id="select3">
        <option value="1">商场</option>
        <option value="2">丝芙兰</option>
        <option value="3">机场</option>
      </select>
    </td>
  </tr>
  <tr>
    <td align="right" class="title2">柜台样式</td>
    <td class="con2">
    <select id="styleId" name="styleId" class="ip" id="select3">
    <#if styleList??>
      <#list styleList as s>
          <option value="${s.id?if_exists}">${s.name?if_exists}</option>
      </#list>
    </#if>
    </select>
    </td>
  </tr>
  <tr>
    <td align="right" class="title2">名称</td>
    <td class="con2">
        <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
        <input name="name" type="text" class="input1" id="textfield" value="${model.name?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">地址</td>
    <td class="con2"><input name="address" type="text" class="input1" id="textfield" value="${model.address?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">经纬度</td>
    <td class="con2"><input name="longLat" type="text" class="input1" id="textfield" value="${model.longLat?if_exists}" size="40" /></td>
  </tr>   
  <tr>
    <td align="right" class="title2">电话</td>
    <td class="con2"><input name="phone" type="text" class="input1" id="textfield3" value="${model.phone?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">编号</td>
    <td class="con2"><input name="counterNo" type="text" class="input1" id="textfield3" value="${model.counterNo?if_exists}" size="40" /></td>
  </tr>  
  <tr>
    <td align="right" class="title2">备注</td>
    <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${model.comment?if_exists}</textarea></td>
  </tr>
  <tr>
    <td align="right" class="title2">&nbsp;</td>
    <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
    <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listCounter'" /></td>
  </tr>
</table>

<#if model.id??>
<br>
<div class="icon">包括家具</div>
<strong>添加家具：</strong>
		<select id="addFurIdSelect">  
			<#if furList??>
			<#list furList as s>
				<option value="${s.id?if_exists}" >${s.name?if_exists}</option>
			</#list>
			</#if>  
        </select>  
        <input name="button" id="button" value="添加" class="blue" type="button" onclick="addFurnitureRel()">
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="5"></td>
    <td width="20"></td>
    <td width="150" align="left">家具编号</td>
    <td width="150" >家具名称</td>
    <td align="center" width="150">添加日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if counterDtlList??>
  <#assign index = 0>
	<#list counterDtlList as s>
  	<#if s.furniture??>
  	<#assign index = index+1>
	  <tr class="con">
	    <td bgcolor="#FFFFFF">&nbsp;</td>
	    <td bgcolor="#FFFFFF">${index}</td>
	    <td bgcolor="#FFFFFF">${s.furniture.furnitureNo?if_exists}</td>
	    <td bgcolor="#FFFFFF">${s.furniture.name?if_exists}</td>
	    <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	    <td align="center" bgcolor="#FFFFFF"><a href="javascript: deleteDtlRel(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a></td>
	  </tr>
	  </#if>  
	</#list>
	</#if>  
  
</tbody></table>
<br>
<div class="icon">包括父件</div>
<#--<strong>添加父件：</strong>-->
		<#--<select id="furIdSelectForObjParent" onchange="loadObjParent()" >  -->
			<#--<#if furList??>-->
			<#--<#list furList as s>-->
				<#--<option value="${s.id?if_exists}" >${s.name?if_exists}</option>-->
			<#--</#list>-->
			<#--</#if>  -->
        <#--</select>  -->
        <#--<select id="objParentSelect">-->
			<#--<option value="-1" ></option>-->
	    <#--</select>  -->
	    <#--位置：<input type="text" id="objParentSiteNo" class="input1" value="">-->
        <#--<input name="button" id="button" value="添加" class="blue" type="button" onclick="addObjParentRel()">-->
<input name="button" id="button" value="添加灯片" class="blue" type="button" onclick="location='${base_addr}/gtb/parent/toEditCounterParent?counterId=${model.id?if_exists}'">
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr class="title1">
    <td width="20"></td>
    <td width="150">灯片编号</td>
    <td width="150">灯片名称</td>
    <td width="150" align="center">长宽高</td>
    <td width="150" align="center">位置</td>
    <td align="center" width="150">添加日期</td>
    <td align="center" width="150">操作</td>
  </tr>
  <#if parentList??>
  <#assign index = 0>
	<#list parentList as s>

  	<#assign index = index+1>
	  <tr class="con">
	    <td bgcolor="#FFFFFF">${index}</td>
	    <td bgcolor="#FFFFFF">${s.objParentNo?if_exists}</td>
	    <td bgcolor="#FFFFFF">${s.name?if_exists}</td>
        <td align="center" bgcolor="#FFFFFF">${s.length?if_exists}*${s.width?if_exists}*${s.height?if_exists}</td>
	    <td bgcolor="#FFFFFF" align="center">${s.siteNo?if_exists}</td>
	    <td align="center" bgcolor="#FFFFFF">${(s.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	    <td align="center" bgcolor="#FFFFFF">
            <a href="${base_addr}/gtb/parent/toEditCounterParent?id=${s.id?if_exists}&&counterId=${model.id?if_exists}" target="_self"><img src="${base_addr}/static/images/bj.jpg" height="18"></a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript: deleteDtlRel(${s.id?if_exists});"><img src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
        </td>
	  </tr>

	</#list>
	</#if>  
  
</tbody></table>
</#if>

</body>
<script>
function addFurnitureRel(){
	var furId = $('#addFurIdSelect').val();
	var counterOrgId= '${model.id?if_exists}';
	$.ajax({
		url: '${base_addr}/gtb/org/addOrUpdateCounterDtl' ,
        secureuri: false,
        data: {counterOrgId: counterOrgId,furId : furId},
        dataType: 'json',
        success: function (data) {
        	if(data.success){
        		location.reload();
        	}
        },
        error: function (data) {
            alert("添加失败--1"+data);
        }
    });
}
function deleteDtlRel(dtlId){
	if(!confirm('确定删除？')){
		return;
	}
	$.ajax({
		url: '${base_addr}/gtb/org/deleteCounterDtl' ,
        secureuri: false,
        data: {dtlId: dtlId},
        dataType: 'json',
        success: function (data) {
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
function loadObjParent(){
	var furId = $('#furIdSelectForObjParent').val();
	$.ajax({
		url: '${base_addr}/gtb/org/loadObjParentByFurId' ,
        secureuri: false,
        data: {furId: furId},
        dataType: 'json',
        success: function (data) {
        	if(data.success && data.objParentList && data.objParentList.length > 0){
        		var objParentList = data.objParentList;
        		var objParentSelect = $('#objParentSelect');
        		objParentSelect.empty();
        		objParentSelect.append('<option value="-1" ></option>');
        		for(var i = 0; i < objParentList.length; i++){
        			var objParent = objParentList[i];
        			objParentSelect.append("<option value='"+objParent.id+"'>"+objParent.name+"</option>");
        		}
        	}
        },
        error: function (data) {
            alert("添加失败--2"+data);
        }
    });
}
<#--<#if model.id??>-->
<#--loadObjParent();-->
<#--</#if>-->
function addObjParentRel(){
	var objParentId = $('#objParentSelect').val();
	var objParentSiteNo = $('#objParentSiteNo').val();
	var counterOrgId= '${model.id?if_exists}';
	$.ajax({
		url: '${base_addr}/gtb/org/addOrUpdateCounterDtl' ,
        secureuri: false,
        data: {counterOrgId: counterOrgId,objParentId : objParentId, siteNo : objParentSiteNo},
        dataType: 'json',
        success: function (data) {
        	if(data.success){
        		location.reload();
        	}
        },
        error: function (data) {
            alert("添加失败--3"+data);
        }
    });
}
</script>
</html>
