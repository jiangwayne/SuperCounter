<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${base_addr}/static/js/jquery1.4.2.min.js"></script>   
    <script type="text/javascript" src="${base_addr}/static/js/jquery.PrintArea.js"></script>   
    <script>  
    $(document).ready(function(){  
      $("input#biuuu_button").click(function(){  
      
      $("div#myPrintArea").printArea();  
      
    });  
    });  
       
    </script>  
</head>    
<body>   
<input id="biuuu_button" type="button" value="打印"></input> 	
<center>    
    <div id="myPrintArea"><img src="${base_addr}/gtb/file/createQRCodeImg?qrCode=${qrCode?if_exists}" height="200" width="200"></div>   
</center>    
</body>           
</html>