
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>right</title>
<link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
<link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
<script src="${base_addr}/static/js/jquery1.4.2.min.js" type="text/javascript"></script>
<script src="${base_addr}/static/js/myjs.js"></script>

</head>

<body>
<div class="loc">
  <div class="icon">当前位置：分配道具</div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    <div class="rybox">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30" valign="top"><div class="t1"><strong>家具列表：</strong>
        <input name="furNameParam" type="text" class="input1" id="furNameParam" value="" size="40" />
        <input onclick="queryFur()" value="查询" class="blue" type="button">
      </div></td>
      <td align="left" valign="top">
	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		  		<td width="8"><p>&nbsp;</p></td>
		  		<td><div class="t1"><strong>道具、图片</strong>
			  		<input type="checkbox" name="checkbox2" id="checkbox2" onclick="selectAllObjParentRelFur(this)">全选<br>
			  		<input type="text" class="input1" id="objParentNameParam" value="" size="40" />
        <input onclick="queryObjParentByFur_doQuery()" value="查询" class="blue" type="button">
        </div>
			  	</td>
        	</tr>
      	 </table>
      </td>
    </tr>
    <tr>
      <td width="50%">
      <select onchange="queryObjParentByFur_change()" size="15" id="furSelect" style=" width:100%; border:#999 solid 1px; height:302px">
    </select>
        
      </td>
      <td width="50%" align="left" valign="top">
      <div class="people">
        <ul id="objectParentULByFur"> </ul>
      </div></td>
    </tr>
  </table>
  <div style="margin: 10px">  <input onclick="saveAssign('objectParentULByFur')" name="button" type="button" value="保存分配" class="blue"/> </div>

</div>
    </td>
    <td width="2%">&nbsp;</td>
    <td width="48%" valign="top">
    
    </td>
  </tr>
  <tr>
    <td>
    <div class="rybox">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="30" valign="top"><div class="t1"><strong>柜台列表：</strong>
        <input name="counterName" type="text" class="input1" id="counterName" value="" size="40" />
        <input onclick="queryCounter()" value="查询" class="blue" type="button">
      </div></td>
      <td align="left" valign="top">
	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		  		<td width="8"><p>&nbsp;</p></td>
		  		<td><div class="t1"><strong>灯片</strong>
			  		<input type="checkbox" name="checkbox2" id="checkbox2" onclick="selectAllObjParentRelCounter(this)">全选<br>
			  		<input name="objParentNameParam_counter" type="text" class="input1" id="objParentNameParam_counter" value="" size="40" />
        <input name="button" onclick="queryObjParentByCounter_doQuery()" id="button" value="查询" class="blue" type="button">
        </div>
			  	</td>
        	</tr>
      	 </table>
      </td>
    </tr>
    <tr>
      <td width="50%"><select onchange="queryObjParentByCounter_counterChange()" size="15" id="counterSelect" style=" width:100%; border:#999 solid 1px; height:302px">
    </select>
        
      </td>
      <td width="50%" align="left" valign="top">
      <div class="people">
        <ul id="objectParentUL_counter">
        </ul>
      </div></td>
    </tr>
  </table>
  <div style="margin: 10px">  <input type="button" onclick="saveAssign('objectParentULByCounter')" name="button" id="button" value="保存分配" class="blue"/> </div>

</div>
    </td>
    <td width="2%">&nbsp;</td>
    <td width="48%" valign="top">
    
    </td>
  </tr>
</table>
<hr>
<strong>计划单：</strong>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody id="planCargoTable">
  <tr class="title1">
    <td width="5">&nbsp;</td>
    <td width="20"><input name="checkbox" id="objParentCheckbox_" type="checkbox"></td>
    <td>柜台</td>
    <td>父件</td>
    <td>供应商</td>
    <td align="center">数量</td>
    <td align="center" width="150">操作</td>
  </tr>
</tbody></table>
<input name="button" id="button" value="全部分配" onclick="doAssign()" class="blue" type="button">

</body>

<script>
$(function (){
	queryFur();
	//queryObjParentByFur(null);
	queryCounter();
	//queryObjParentByCounter_doQuery(null);
});
//**************************************家具，道具图片**************************************************/
	function queryFur(){
		var furName = $('#furNameParam').val();
		$.ajax({
			url: '${base_addr}/gtb/assignTask/queryFur' ,
	        secureuri: false,
	        data: {furName: furName},
	        dataType: 'json',
	        success: function (data) {
	        	var furSelect = $('#furSelect');
        		furSelect.empty();
	        	var objParentList = data.data;
	        	if(data.success && objParentList && objParentList.length > 0){
	        		for(var i = 0; i < objParentList.length; i++){
	        			var objParent = objParentList[i];
	        			furSelect.append("<option value='"+objParent.id+"'>"+objParent.name+"</option>");
	        		}
	        	}
	        },
	        error: function (data) {
	            alert("失败--"+data);
	        }
	    });
	}
	function selectAllObjParentRelFur(thisDom){
		var isAllCheck = thisDom.checked;
		$("input[name='checkbox_objectParentULByFur']").each(function (index,dom){
			//alert(index+"--"+dom);
			dom.checked=isAllCheck;
		});
	}
	function queryObjParentByFur_change(){
		var furId = $('#furSelect').val();
		var objParentName = '';
		$('#objParentNameParam').val(objParentName);
		queryObjParentByFur(furId,objParentName);
	}
	function queryObjParentByFur_doQuery(){
		var furId = $('#furSelect').val();
		if(furId == null){
			return;
		}
		var objParentName = $('#objParentNameParam').val();
		queryObjParentByFur(furId,objParentName);
	}
	function queryObjParentByFur(furId,objParentName){
		$.ajax({
			url: '${base_addr}/gtb/assignTask/queryObjParentByFur' ,
	        secureuri: false,
	        data: {objParentName: objParentName, furId : furId},
	        dataType: 'json',
	        success: function (data) {
	        	var objParentList = data.data;
	        	var objectParentULByFur = $('#objectParentULByFur');
        		objectParentULByFur.empty();
	        	if(data.success && objParentList && objParentList.length > 0){
	        		for(var i = 0; i < objParentList.length; i++){
	        			var objParent = objParentList[i];
	        			incrementFlag++;
	        			var tmpStr = '<li><input type="checkbox" id="checkbox_objectParentULByFur_'+incrementFlag+'" value="'+objParent.id+'" name="checkbox_objectParentULByFur"><span id="span_objectParentULByFur_'+objParent.id+'">'+objParent.name+'</span><input type="text" class="input1" style="min-width:5px" id="countInput_objectParentULByFur_'+incrementFlag+'" value="1" size="3"/>个</li>';
	        			//tmpStr = "<option value='"+objParent.id+"'>"+objParent.name+"</option>";
	        			objectParentULByFur.append(tmpStr);
	        		}
	        	}
	        },
	        error: function (data) {
	            alert("失败--"+data);
	        }
	    });
	}
//**************************************柜台，灯片**************************************************/
	function queryCounter(){
		var counterName = $('#counterName').val();
		$.ajax({
			url: '${base_addr}/gtb/assignTask/queryCounter' ,
	        secureuri: false,
	        data: {counterName: counterName},
	        dataType: 'json',
	        success: function (data) {
	        	var furSelect = $('#counterSelect');
        		furSelect.empty();
	        	var objParentList = data.data;
	        	if(data.success && objParentList && objParentList.length > 0){
	        		for(var i = 0; i < objParentList.length; i++){
	        			var objParent = objParentList[i];
	        			furSelect.append("<option value='"+objParent.id+"'>"+objParent.name+"</option>");
	        		}
	        	}
	        },
	        error: function (data) {
	            alert("失败--"+data);
	        }
	    });
	}
	function selectAllObjParentRelCounter(thisDom){
		var isAllCheck = thisDom.checked;
		$("input[name='checkbox_objectParentULByCounter']").each(function (index,dom){
			//alert(index+"--"+dom);
			dom.checked=isAllCheck;
		});
	}
	function queryObjParentByCounter_counterChange(){
		var counterId = $('#counterSelect').val();
		var objParentName = '';
		$('#objParentNameParam_counter').val(objParentName);
		queryObjParentByCounter(counterId,objParentName);
	}
	function queryObjParentByCounter_doQuery(){
		var counterId = $('#counterSelect').val();
		if(counterId == null){
			return;
			//counterId = -1;
		}
		var objParentName = $('#objParentNameParam_counter').val();
		queryObjParentByCounter(counterId,objParentName);
	}
	var incrementFlag = 0;
	function queryObjParentByCounter(counterId,objParentName){
		
		$.ajax({
			url: '${base_addr}/gtb/assignTask/queryObjParentByCounter' ,
	        secureuri: false,
	        data: {objParentName: objParentName, counterId : counterId},
	        dataType: 'json',
	        success: function (data) {
	        	var objParentList = data.data;
	        	var objectParentULByFur = $('#objectParentUL_counter');
        		objectParentULByFur.empty();
	        	if(data.success && objParentList && objParentList.length > 0){
	        		for(var i = 0; i < objParentList.length; i++){
	        			var objParent = objParentList[i];
	        			incrementFlag++;
	        			var tmpStr = '<li><input type="checkbox" id="checkbox_objectParentULByCounter_'+incrementFlag+'" value="'+objParent.id+'" name="checkbox_objectParentULByCounter"><span id="span_objectParentULByCounter_'+objParent.id+'">'+objParent.name+'</span><input type="text" class="input1" style="min-width:5px" id="countInput_objectParentULByCounter_'+incrementFlag+'" value="1" size="3"/>个</li>';
	        			//tmpStr = "<option value='"+objParent.id+"'>"+objParent.name+"</option>";
	        			objectParentULByFur.append(tmpStr);
	        		}
	        	}
	        },
	        error: function (data) {
	            alert("失败--"+data);
	        }
	    });
	}
//**************************************保存分配**************************************************/
	var planCargoTable_tr_count = 0;
	function saveAssign(cbName){
		//objectParentULByFur  objectParentULByCounter
		var planCargoTable = $('#planCargoTable');
		$("input[name='checkbox_"+cbName+"']").each(function (index,dom){
			//alert(index+"--"+dom);
			if(dom.checked){
				var objParentId = dom.value;
				var domIdSplit = dom.id.split("_");
				var objParentName=$('#span_'+cbName+'_'+objParentId).html();
				var count=$('#countInput_'+cbName+'_'+domIdSplit[2]).val();
				if('objectParentULByFur' == cbName){
					$.ajax({
						url: '${base_addr}/gtb/assignTask/querySupplierByObjParentId' ,
				        secureuri: false,
				        async : false,
				        data: {objParentId: objParentId},
				        dataType: 'json',
				        success: function (data) {
				        	if(data.success && data.data){
				        		var supplier = data.data;
				        		var furId = $('#furSelect').val();
				        		$.ajax({
									url: '${base_addr}/gtb/assignTask/queryCounterByFurId' ,
							        secureuri: false,
							        async : false,
							        data: {furId: furId},
							        dataType: 'json',
							        success: function (data2) {
							        	if(data2.success && data2.data){
							        		var counterList = data2.data;
							        		if(counterList != null && counterList.length > 0){
							        			for(var k = 0; k < counterList.length; k++){
							        				var counterId = counterList[k].counter.id;
							    					var counterName = counterList[k].counter.name;
									        		var html = '';
													planCargoTable_tr_count++;
													var trId = "planCargoTable_tr_"+planCargoTable_tr_count;
													var checkboxValue = counterId+"_"+objParentId+"_"+supplier.id+"_"+counterList[k].count*count;
													html += ('<tr id="'+trId+'" class="con"><td bgcolor="#FFFFFF">&nbsp;</td>');
													html += ('<td bgcolor="#FFFFFF"><input name="plancheckbox" type="checkbox" value="'+checkboxValue+'"></td>');
													html += ('<td bgcolor="#FFFFFF">'+counterName+'</td>');
													html += ('<td bgcolor="#FFFFFF">'+objParentName+'</td>');
													html += ('<td bgcolor="#FFFFFF">'+supplier.name+'</td>');
													html += ('<td align="center" bgcolor="#FFFFFF">'+counterList[k].count*count+'</td>');
													html += ('<td align="center" bgcolor="#FFFFFF"><a href="javascript: removeTr(\''+trId+'\')">删除</a></td></tr>');
													planCargoTable.append(html);
							        			}
							        		}
							        	}
							        }
							    });
				        	}
				        }
				    });
					
				}else if('objectParentULByCounter' == cbName){
					var counterId = $('#counterSelect').val();
					var counterName = $("#counterSelect").find("option:selected").text();
					$.ajax({
						url: '${base_addr}/gtb/assignTask/querySupplierByObjParentId' ,
				        secureuri: false,
				        async : false,
				        data: {objParentId: objParentId},
				        dataType: 'json',
				        success: function (data) {
				        	if(data.success && data.data){
				        		var supplier = data.data;
				        		var html = '';
								planCargoTable_tr_count++;
								var trId = "planCargoTable_tr_"+planCargoTable_tr_count;
								var checkboxValue = counterId+"_"+objParentId+"_"+supplier.id+"_"+count;
								html += ('<tr id="'+trId+'" class="con"><td bgcolor="#FFFFFF">&nbsp;</td>');
								html += ('<td bgcolor="#FFFFFF"><input name="plancheckbox" type="checkbox" value="'+checkboxValue+'"></td>');
								html += ('<td bgcolor="#FFFFFF">'+counterName+'</td>');
								html += ('<td bgcolor="#FFFFFF">'+objParentName+'</td>');
								html += ('<td bgcolor="#FFFFFF">'+supplier.name+'</td>');
								html += ('<td align="center" bgcolor="#FFFFFF">'+count+'</td>');
								html += ('<td align="center" bgcolor="#FFFFFF"><a href="javascript: removeTr(\''+trId+'\')">删除</a></td></tr>');
								planCargoTable.append(html);
				        	}
				        }
				    });
					
				}
			}
		});
	}
	function removeTr(trId){
		$('#'+trId).remove();
	}
//**************************************分配**************************************************/
	function doAssign(){
		var assignValueArrStr = '';
		$("input[name='plancheckbox']").each(function (index,dom){
			var arr = dom.value;
			assignValueArrStr += arr+",";
		});
        alert(assignValueArrStr);
		$.ajax({
			url: '${base_addr}/gtb/assignTask/doAssign' ,
	        secureuri: false,
	        async : false,
	        data: {assignValueArrStr: assignValueArrStr},
	        dataType: 'json',
	        success: function (data) {
	        	if(data.success){
	        		alert("保存成功");
	        	}
	        }
	    });
	}
</script>
</html>
