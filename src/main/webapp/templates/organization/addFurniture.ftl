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
        $(document).ready(function(){
            $("#brand").val('${model.orgId?if_exists}');

            <#if model.orgId?if_exists>$("#brand").attr("disabled","disabled");</#if>

            $("#brand").change(function(){
                $.getJSON("${base_addr}/gtb/org/listObjParent?brandId=" + $(this).val() + "&keyword=" +$("#keyword").val(),function(data){
                    $("#objParentList").children().remove();
                    for(var i = 0; i < data.length; i++) {
                        //alert(data[i].id);
                        var li = $('<li><input type="checkbox" value="'+ data[i].id +'" name="objParentIds"><span>'+ data[i].name+'</span></li>');
                        $("#objParentList").append(li);
                    }
                    //alert(data.length);
                });
            });

            $("#brand").change();
            $("#search").click(function(){
                $("#brand").change();
            });
        });
        function addCounterTemplate(){
            if($("#id").val() == ""){
                alert('请先保存样式')
                return false;
            }
            var objParentIds = [];
            $('input[name="objParentIds"]:checked').each(function () {
                objParentIds.push($(this).val());
            });
            if(objParentIds.length==0){
                alert('你还没有选择任何父件！')
                return false;
            }
            $.getJSON("${base_addr}/gtb/org/addFurnitureTemplate?furnitureId=" + $("#id").val() + "&objParentIds=" + objParentIds,function(data){
                $("#templateList").children().remove();
                for(var i = 0; i < data.length; i++) {
                    //alert(data[i].id);
                    var tr = $('<tr class="con">' +
                            '<td bgcolor="#FFFFFF">&nbsp;</td>' +
                            '<td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>' +
                            '<td bgcolor="#FFFFFF">' + data[i].objParentNo + '</td>' +
                            '<td bgcolor="#FFFFFF">' + data[i].objParentName + '</td>' +
                            '<td bgcolor="#FFFFFF">' + data[i].objParentCount + '</td>' +
                            '<td align="center" bgcolor="#FFFFFF">' + data[i].gmtCreate + '</td>' +
                            '<td align="center" bgcolor="#FFFFFF">' +
                            '<a href="#"><img onclick="deleteCounterTemplate(\'' + data[i].id + '\')" src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>' +
                            '</td>' +
                            '</tr>');
                    $("#templateList").append(tr);
                }
                //alert(data.length);
            });
        }
    </script>
</head>

<body>
<div class="loc">
    <div class="icon">当前位置：&nbsp;&gt;&nbsp;家具管理->编辑家具</div>
</div>
<form action="addFurniture" method="post">
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
      <td width="200" align="right" class="title2"><strong>编号</strong></td>
      <td class="con2">
          <input name="id" type="hidden" class="input1" id="id" value="${model.id?if_exists}" size="40" />
          <input name="furnitureNo" type="text" class="input1" id="textfield4" value="${model.furnitureNo?if_exists}" size="40" />
      </td>
    </tr>
    <tr>
      <td align="right" class="title2">家具名称</td>
      <td class="con2"><input name="name" type="text" class="input1" id="textfield" value="${model.name?if_exists}" size="40" /></td>
    </tr>
    <tr>
      <td align="right" class="title2">备注</td>
      <td class="con2"><textarea name="comment" cols="40" rows="5" class="input2" id="textfield2">${model.comment?if_exists}</textarea></td>
    </tr>
    <tr>
      <td align="right" class="title2">&nbsp;</td>
      <td class="con2"><input type="submit" name="button4" id="button4" value="保存" class="blue" />
      <input type="button" name="button" id="button" value="返回" class="hui" onclick="location.href='listFurniture'" /></td>
    </tr>
  </table>
</form>

<br>
<hr>
<br>
<div class="icon">包括灯片</div>
<br>
<strong>添加道具：</strong>

<table>
    <tr>
        <td>
            <input id="keyword" type="text" class="input1" value="" size="40"  />
            <input id="search" value="查询" class="blue" type="button">
        </td>
    </tr>
</table>
<table>
    <tr>
        <td class="con2">
            <div class="people" style="width:200px;">
                <ul>
                    <ul id="objParentList">

                    </ul>
                </ul>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <input onclick="addCounterTemplate()" value="添加" class="blue" type="button">
        </td>
        </td>
    </tr>
</table>
<table class="bgg" bgcolor="#cccccc" border="0" cellpadding="0" cellspacing="0" width="100%">
    <thead><tr class="title1">
        <td width="5">&nbsp;</td>
        <td width="20"><input name="checkbox" id="checkbox" type="checkbox"></td>
        <td>道具名称</td>
        <td>道具数量</td>
        <td align="center" width="150">添加日期</td>
        <td align="center" width="150">操作</td>
    </tr>
    </thead>
    <tbody id ="templateList">
    <#if furnitureObjParentList??>
        <#list furnitureObjParentList as s>
        <tr class="con">
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF"><input name="checkbox2" id="checkbox" type="checkbox"></td>
            <td bgcolor="#FFFFFF">${s.objParentName?if_exists}</td>
            <td bgcolor="#FFFFFF">${s.objParentCount?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF">${s.gmtCreate?if_exists}</td>
            <td align="center" bgcolor="#FFFFFF"><a href="#"><img onclick="deleteCounterTemplate('${s.id?if_exists}'')" src="${base_addr}/static/images/sc.jpg" height="18" width="13"></a>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
    </thead></table>
</body>
</html>
