<HTML><head>
    <title>title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type=text/css>
        body  { background:#ececec; margin:0px; font:9pt 宋体; }
        table  { border:0px; }
        td  { font:normal 12px 宋体; }
        img  { vertical-align:bottom; border:0px; }
        a  { font:normal 12px 宋体; color:#000000; text-decoration:none; }
        a:hover  { color:#cc0000;text-decoration:underline; }
        .tr_top {
            color: #000000;
            font-family: "隶书";
            font-size: 35px;
            background-image: url('imagesz/admin_top_bg.gif');
            font-weight:bold;
            height: 35px;
            text-align:center
        }
        .tr_display {
            color: #000000;
            background-color: #fafafa;
            font-family: "隶书";
            font-size: 24px;
            height: 22px;
            text-align:center
        }

        .form_search {
            margin-left: 50px;
            color: red;
            font-family: "隶书";
            font-size: 10px;
        }

        .table_display {
            margin:0 auto;
        }
        A:link {
            color: purple;
            TEXT-DECORATION: none
        }
        A:visited {
            COLOR: yellow;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: #ff7f24;
            text-decoration: underline;
        }

        A:active {
            COLOR: #ff7f24;
            text-decoration: underline;
        }
    </style>

</head>
<BODY leftMargin=0 text=#000000 topMargin=0 marginheight="0" marginwidth="0">
<table>
    <tr>
        <td>
            <form name="xxx" method="post" action="/gtb/organization/list"  class="form_search">
                <input class=input1  name="keyword" size=15>
                <input type="submit" name="Submit" value="查询">
            </form>
        </td>
        <td>
            <form name="xxx" method="post" action="/gtb/organization/add"  class="form_search">
                <input type="submit" name="Submit2" value="添加组织">
            </form>
        </td>
    </tr>
</table>
<table border=1>
    <tr>
        <td>id</td>
        <td>组织名</td>
        <td>类型</td>
        <td>地址</td>
        <td>电话</td>
        <td>email</td>
        <td>媒介类型</td>
        <td>样式类型</td>
        <td>经纬度</td>
    </tr>
<#if list??>
    <#list list as s>
        <tr id="tr_${s_index}">
            <td>${s.id?if_exists}</td>
            <td>${s.name?if_exists}</td>
            <td>${s.type?if_exists}</td>
            <td>${s.address?if_exists}</td>
            <td>${s.phone?if_exists}</td>
            <td>${s.email?if_exists}</td>
            <td>${s.media_type?if_exists}</td>
            <td>${s.style_id?if_exists}</td>
            <td>${s.last_long_lat?if_exists}</td>
        </tr>
    </#list>
</#if>
</table>
</BODY></HTML>