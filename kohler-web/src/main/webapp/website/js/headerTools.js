$(document).ready(function(){
	isFreeze();
	logOut();
	logIn();
	//productHeatRecords();
});

function productHeatRecords() {
	var skucode = $(".product_feature span").text();
	console.log(skucode);
	
	var url = chinaweb + "heatrecord/add.action?callback=?";
	$.getJSON(url,{accountId:123},{}, function(data){
		
	},"jsonp");
	
}

//判断是否登录
function isFreeze() {
	var formSelect = "#login-form";
	var url = $(formSelect).attr("isFreeze");
	url = chinaweb + url +"?callback=?";
	$.getJSON(url,"",function(data){
		if(data.success) {
			$("#loginArea").show();
			$("#logoutArea").hide();
			$("#login_msg").text("Hi,"+data.obj.realName);
		}
	},"jsonp");
	
}

function logOut(){
	$("#logout").click(function() {
		var isOk = confirm("are you sure logout?");
		if(isOk) {
			var formSelect = "#login-form";
			var url = chinaweb + $(formSelect).attr("isLogout");
			url = url +"?callback=?"
			$.getJSON(url,"",function(data){
				if(data.success) {
					window.location.reload();
				} 
			},"jsonp");
		}
		
	});	
}

//登录
function logIn() {
	$("#login_btn_form").click(function(){
		var formSelect = "#login-form";
		formValidate(formSelect).init();
		var formObj = $(formSelect);
		var isValid = formObj.valid();
		if(isValid) {
			var url = chinaweb + formObj.attr("action");
			url = url +"?callback=?"
			$.getJSON(url,formObj.serialize(),function(data){
				if(data.success) {
					window.location.reload();
				}else {
					alert(data.msg);
				}
			},"jsonp");
		}
	});
}

function gotoMember(obj) {
	var url = chinaweb + $(obj).attr("data-target");
	window.open(url);
}