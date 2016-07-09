// JavaScript Document
//说明 所有的元素以ul li ul li ul li的循环格式嵌套 如果没有下级分类 就用li a结束嵌套
$(document).ready(function(){
//$(".nav ul li").children("ul").hide();
$(".leftmenu").find("li").not(":has(ul)").children("a").css({textDecoration:"none",background:"none"})
.click(function(){
$(this).get(0).location.href="'"+$(this).attr("href")+"'";
});
$(".leftmenu").find("li:has(ul)").children("a").css({ background:"url(images/xsj.png) no-repeat 150px center"})
.click(function(){
if($(this).next("ul").is(":hidden")){
$(this).next("ul").slideDown("slow");
if($(this).parent("li").siblings("li").children("ul").is(":visible")){
$(this).parent("li").siblings("li").find("ul").slideUp("1000");
$(this).parent("li").siblings("li:has(ul)").children("a").css({})
.end().find("li:has(ul)").children("a").css({ background:"url(images/xsj.png) no-repeat 150px center"});}
$(this).css({background:"url(images/xsj.png) no-repeat 150px center"});
return false;
}else{
$(this).next("ul").slideUp("normal");
//不用toggle()的原因是为了在收缩菜单的时候同时也将该菜单的下级菜单以后的所有元素都隐藏
$(this).css({background:"url(images/xsj.png) no-repeat 150px center"});
$(this).next("ul").children("li").find("ul").fadeOut("normal");
$(this).next("ul").find("li:has(ul)").children("a").css({background:"url(images/xsj.png) no-repeat 150px center"});
return false;
}
});
});