
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
  <div class="icon">当前位置：报价单详情&nbsp;->报价单详情->报价单编辑</div>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="bgg">
  <tr>
    <td align="right" class="title2">父件</td>
    <td class="con2"><input type="text" class="input1" value="${orderSupplierDetail.objParent.name?if_exists}" size="40" disabled="true"/></td>
  </tr>
  <tr>
    <td align="right" class="title2">数量</td>
    <td class="con2"><input type="text" class="input1" value="${orderSupplierDetail.objParentCount?if_exists}" size="40" disabled="true"/></td>
  </tr>
  <tr>
    <td align="right" class="title2">RMB单价</td>
    <td class="con2"><input id="unitPriceCny" type="text" class="input1" value="${orderSupplierDetail.unitPriceCny?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">美元单价</td>
    <td class="con2"><input id="unitPriceUsd" type="text" class="input1" value="${orderSupplierDetail.unitPriceUsd?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">欧元单价</td>
    <td class="con2"><input id="unitPriceEur" type="text" class="input1" value="${orderSupplierDetail.unitPriceEur?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">RMB报价</td>
    <td class="con2"><input id="priceCny" type="text" class="input1" value="${orderSupplierDetail.priceCny?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">美元报价</td>
    <td class="con2"><input id="priceUsd" type="text" class="input1" value="${orderSupplierDetail.priceUsd?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">欧元报价</td>
    <td class="con2"><input id="priceEur" type="text" class="input1" value="${orderSupplierDetail.priceEur?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">最终RMB单价</td>
    <td class="con2"><input id="finalUnitPriceCny" type="text" class="input1" value="${orderSupplierDetail.finalUnitPriceCny?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">最终美元单价</td>
    <td class="con2"><input id="finalUnitPriceUsd" type="text" class="input1" value="${orderSupplierDetail.finalUnitPriceUsd?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">最终欧元单价</td>
    <td class="con2"><input id="finalUnitPriceEur" type="text" class="input1" value="${orderSupplierDetail.finalUnitPriceEur?if_exists}" size="40" /></td>
  </tr>     
  <tr>
    <td align="right" class="title2">最终RMB报价</td>
    <td class="con2"><input id="finalPriceCny" type="text" class="input1" value="${orderSupplierDetail.finalPriceCny?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">最终美元报价</td>
    <td class="con2"><input id="finalPriceUsd" type="text" class="input1" value="${orderSupplierDetail.finalPriceUsd?if_exists}" size="40" /></td>
  </tr>
  <tr>
    <td align="right" class="title2">最终欧元报价</td>
    <td class="con2"><input id="finalPriceEur" type="text" class="input1" value="${orderSupplierDetail.finalPriceEur?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">PO号</td>
    <td class="con2"><input id="poNumber" type="text" class="input1" value="${orderSupplierDetail.poNumber?if_exists}" size="40" /></td>
  </tr>    
  <tr>
    <td align="right" class="title2">备注</td>
    <td class="con2">
    	<textarea id="comment" cols="40" rows="5" class="input2" >${orderSupplierDetail.comment?if_exists}</textarea>
    </td>
  </tr>
  <tr>
    <td align="right" class="title2">&nbsp;</td>
    <td class="con2"><input type="button" onclick="updateDtl()" value="提交" class="blue" />
    <input type="button" value="返回" class="hui" onclick="history.go(-1)" /></td>
  </tr>
</table>
</body>
<script>
$(function (){
	//初始化js
});
function updateDtl(dateInputId,dtlId){
	var priceCny= $('#priceCny').val();
	var priceUsd= $('#priceUsd').val();
	var priceEur= $('#priceEur').val();
	var unitPriceCny= $('#unitPriceCny').val();
	var unitPriceUsd= $('#unitPriceUsd').val();
	var unitPriceEur= $('#unitPriceEur').val();
	var finalPriceCny = $('#finalPriceCny').val();
	var finalPriceUsd = $('#finalPriceUsd').val();
	var finalPriceEur = $('#finalPriceEur').val();
	var finalUnitPriceCny = $('#finalUnitPriceCny').val();
	var finalUnitPriceUsd = $('#finalUnitPriceUsd').val();
	var finalUnitPriceEur = $('#finalUnitPriceEur').val();
	var poNumber= $('#poNumber').val();
	var comment = $('#comment').val();
	var dtlId = ${dtlId};
	$.ajax({
		url: '${base_addr}/gtb/orderPrice/updateDtl',
        secureuri: false,
        async : false,
        data: {dtlId : dtlId, poNumber : poNumber, comment : comment,
			priceCny : priceCny, priceUsd : priceUsd, priceEur : priceEur, 
			unitPriceCny : unitPriceCny, unitPriceUsd : unitPriceUsd, unitPriceEur : unitPriceEur, 
			finalPriceCny : finalPriceCny, finalPriceUsd : finalPriceUsd, finalPriceEur : finalPriceEur, 
			finalUnitPriceCny : finalUnitPriceCny, finalUnitPriceUsd : finalUnitPriceUsd, finalUnitPriceEur : finalUnitPriceEur},
        dataType: 'json',
        success: function (data) {
        	if(data.success){
        		location.href = '${base_addr}/gtb/orderPrice/toDtlList?orderSupplierId=${orderSupplierId}';
        	}
        }
    });
}
</script>
</html> 