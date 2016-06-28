function announcement(){
	alert('test');
}

function categoryClick(obj,elem){
	//alert(elem);
	document.getElementById('h_cateId').value = elem;
	//obj.className="leftnav";
	//alert(document.getElementById('h_cateId'));
	document.getElementById('searchForm').submit();
}

function pageChange(elem){
	//alert(elem);
	document.getElementById('currentPage').value = elem;
	document.getElementById('searchForm').submit();
	
}
//预约分页
function pageChangeBook(elem){
	//alert(elem);
	document.getElementById('planBeginTime').value = document.getElementById('beginTime').value;
	document.getElementById('planEndTime').value = document.getElementById('endTime').value;
	document.getElementById('currentPage').value = elem;
	document.getElementById('searchFormBook').submit();
	
}
function pageChangeMyBook(elem){
//	alert(elem);
//	document.getElementById('planBeginTime').value = document.getElementById('beginTime').value;
//	document.getElementById('planEndTime').value = document.getElementById('endTime').value;
	document.getElementById('currentPage').value = elem;
//	alert(document.getElementById('searchMyFormBook').action);
	document.getElementById('searchMyFormBook').submit();
	
}
function pageChageUserInfoAudit(elem){
	document.getElementById('currentPage').value = elem;
	document.getElementById('userInfoForm').submit();
	
}
//审核用户
function auditUser(userid,elem){
	document.getElementById('toCheckStatus').value = elem;
	document.getElementById('id').value = userid;
	if(elem == null || elem == ''){
		document.getElementById('name').value = '';
		document.getElementById('telNo').value = '';
		document.getElementById('cardNo').value = '';
		
	}
	document.getElementById('userInfoForm').submit();
}

function cancleBook(elem1,elem2){
	document.getElementById('currentPage').value = elem1;
	document.getElementById('id').value = elem2;
	document.getElementById('searchMyFormBook').submit();
	
}

function unLoginBook(){
	top.document.location.href="/welcome";
}
function login(){
//	alert('test');
	document.getElementById('loginForm').submit();
}

function logout(){
	//alert('test');
	top.document.location.href="/welcome";	
}

function equipBook(elem1,elem2){
	alert(elem1);
	alert(elem2);
	document.location.href="/userBook";
}

function bookItem(){
	alert(document.getElementById('beginTime').value);
	alert(document.getElementById('endTime').value);
	 var sDate = new Date(document.getElementById("beginTime").value.replace(/\-/g, "\/"));
	 var eDate = new Date(document.getElementById("endTime").value.replace(/\-/g, "\/"));
	 if(eDate<sDate){
		 alert('error!预约结束时间不得早于预约开始时间');
	 }else{
		 document.getElementById('bookForm').submit();
		 
	 }
}


function register(){
	top.document.location.href = "/register_p";
}

function doregister(){
//	alert(document.getElementByName('isThisSchool').value);
	alert(document.getElementById('registerform'));
	document.getElementById('registerform').submit();
}


//弹出隐藏层
function ShowDiv(show_div,bg_div){
top.document.getElementById(show_div).style.display='block';
top.document.getElementById(bg_div).style.display='block' ;
var bgdiv = top.document.getElementById(bg_div);
bgdiv.style.width = top.document.body.scrollWidth;
// bgdiv.style.height = $(document).height();
$("#"+bg_div).height($(document).height());
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
top.document.getElementById(show_div).style.display='none';
top.document.getElementById(bg_div).style.display='none';
};

function equipmentManager(){
	
	document.getElementById('equipStandard').value = document.getElementById('equipStandard_txt').innerText;
	document.getElementById('equipIntroduce').value = document.getElementById('equipIntroduce_txt').innerText;
	document.getElementById('equipStandardIntroduce').value = document.getElementById('equipStandardIntroduce_txt').innerText;
	document.getElementById('equipOprProcess').value = document.getElementById('equipOprProcess_txt').innerText;
	document.getElementById('equipModifyForm').submit();
	
}

function auditOrder(){
	document.getElementById("orderRemark").value = document.getElementById('orderRemark_txt').innerText;
	document.getElementById("equipOrderForm").submit();
	
}

