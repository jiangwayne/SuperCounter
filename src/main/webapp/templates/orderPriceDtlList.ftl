
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>计划单详细</title>
<link href="${base_addr}/static/css/main.css" rel="stylesheet" type="text/css" />
<link href="${base_addr}/static/css/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base_addr}/static/js/laydate.dev.js"></script>
<script src="${base_addr}/static/js/jquery1.4.2.min.js" type="text/javascript"></script>
</head>
<style>
  body{ background:#ebf1fa}
</style>
<body>

    <div class="wztitle">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center">生产任务单详情</td>
        </tr>
        <tr>
          <td class="date">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="25%">编号：${orderSupplier.orderSupplierNo?if_exists}</td>
              <td width="25%">创建日期：${(orderSupplier.gmtCreate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
              <td width="25%">供应商：${orderSupplier.orgSupplier.name?if_exists}</td>
            </tr>
          </table>
          </td>
        </tr>
      </table>
    </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#cccccc" class="bgg">
      <tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="150"></td>
        <td>道具名称</td>
          <td>长宽高</td>
          <td>出血长宽高</td>
        <td align="center">数量</td>
        <td align="center">RMB单价</td>
        <td align="center">美元单价</td>
        <td align="center">欧元单价</td>        
        <td align="center">RMB报价</td>
        <td align="center">美元报价</td>
        <td align="center">欧元报价</td>       
        <td align="center">最终RMB单价</td>
        <td align="center">最终美元单价</td>
        <td align="center">最终欧元单价</td>        
        <td align="center">最终RMB报价</td>
        <td align="center">最终美元报价</td>
        <td align="center">最终欧元报价</td>            
        <td align="center">PO号</td> 
        <td align="center">预计入库完成时间</td> 
        <td align="center">预计出库发送时间</td>     

        <td width="150" align="center">更新</td>
      </tr>
   <#if dtlList??>
  	<#list dtlList as s>
	      <tr class="con">
	        <td bgcolor="#FFFFFF">&nbsp;</td>
	        <td bgcolor="#FFFFFF">${s_index+1}</td>
	        <td bgcolor="#FFFFFF">${s.objParent.name?if_exists}</td>
              <td bgcolor="#FFFFFF">${s.objParent.length?if_exists}*${s.objParent.width?if_exists}*${s.objParent.height?if_exists}</td>
              <td bgcolor="#FFFFFF">${s.objParent.lengthUp?if_exists}*${s.objParent.widthUp?if_exists}*${s.objParent.heightUp?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.unitPriceCny?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.unitPriceUsd?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.unitPriceEur?if_exists}</td>        
	        <td align="center" bgcolor="#FFFFFF">${s.priceCny?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.priceUsd?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.priceEur?if_exists}</td>       
	        <td align="center" bgcolor="#FFFFFF">${s.finalUnitPriceCny?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.finalUnitPriceUsd?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.finalUnitPriceEur?if_exists}</td>        
	        <td align="center" bgcolor="#FFFFFF">${s.finalPriceCny?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.finalPriceUsd?if_exists}</td>
	        <td align="center" bgcolor="#FFFFFF">${s.finalPriceEur?if_exists}</td>            
	        <td align="center" bgcolor="#FFFFFF">${s.poNumber?if_exists}</td> 
	        <td align="center" bgcolor="#FFFFFF">${(s.inStockTime?string("yyyy-MM-dd"))!''}</td> 
	        <td align="center" bgcolor="#FFFFFF">${(s.outStockTime?string("yyyy-MM-dd"))!''}</td>                 
	
	        <td align="center" bgcolor="#FFFFFF">
	        	<a href="toEditDtl?dtlId=${s.id?if_exists}&orderSupplierId=${orderSupplierId?if_exists}" target="_self">编辑</a>
	        </td>
	      </tr>
      </#list>
      </#if>   
  </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><a href="${base_addr}/gtb/orderPrice/list" class="closewd">关闭</a></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>

</body>
<script>
$(function (){
	//初始化js
});
function updateDtl(dateInputId,dtlId){
	var inDateStr = $('#in_'+dateInputId).val();
	var outDateStr = $('#out_'+dateInputId).val();
	if(!(inDateStr) && !(outDateStr)){
		alert('时间不能都为空');
	}
	$.ajax({
		url: '${base_addr}/gtb/orderSupplier/updateDtl' ,
        secureuri: false,
        async : false,
        data: {dtlId : dtlId, inDateStr : inDateStr, outDateStr : outDateStr},
        dataType: 'json',
        success: function (data) {
        	if(data.success){
        		location.reload();
        	}
        }
    });
}
</script>
</html>