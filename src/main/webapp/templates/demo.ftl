<html>
<head>
<title>用户demo</title>
<link href="/static/css/global.css" rel="stylesheet" type="text/css" />
<script src="/static/js/jquery-1.3.2.min.js"></script>
<script src="/static/js/default.js"></script>
<script src="/static/js/My97DatePicker/WdatePicker.js"></script>
</head>

<body>
	
		<table border="0">
		<form id="form1" action="" method="post">
		<tr>
			<td>姓名</td>
			<td>
				<input name="name">
			</td>
			<td>地区：</td>
			<td><select name="city">
				<option value="湖南"  >湖南</option>
				<option value="上海"  >上海</option>
				<option value="湖北"  >湖北</option>
				</select>
			</td>
			<td>生日：</td>
			<td>
		  		<input id="birthdayDate" name="birthdayDate" size="14" value="${(birthdayDate)!}" class="Wdate"  type="text" id="pickupDateStr"
		  		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMMdd',minDate:'2008-03-08 11:30:00',maxDate:'2100-03-10 20:59:30'})"> 
			</td>
		</tr>
		<tr>
			<td colspan=2><input type="submit" value="添加用户"> </td>
		</tr>
	</form>
	</table>
	<br>用户列表<br>
	<table border=1>
		<tr>
			<td>序号</td>
			<td>姓名</td>
			<td>地区</td>
			<td>生日</td>
		</tr>
		<#if list??>
          	<#list list as s>
         	 <tr id="tr_${s_index}">
              <td>${s_index+1}</td>
              <td>${s.name?if_exists}</td>
              <td>${s.city?if_exists}</td>
              <td>${s.birthdayDate?if_exists}</td>
            </tr>
         	</#list>
          </#if>
	</table>
</body>
<script>
	$(function (){
		//初始化js
	});
	
</script>
</html>