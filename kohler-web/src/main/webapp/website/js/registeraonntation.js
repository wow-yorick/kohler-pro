$(function(){
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	   //input接收前页面的值
	   $("#accountName_xf").val(theRequest['accountName']);//消费手机
	   $("#accountName_jz").val(theRequest['accountName']);//家装手机
	   $("#realName_jz").val(theRequest['realName']);//家装姓名
	   $("#email_jz").val(theRequest['email']);//家装email
	   $("#accountName_gz").val(theRequest['accountName']);//工装手机
	   $("#realName_gz").val(theRequest['realName']);//工装姓名
	   $("#email_gz").val(theRequest['email']);//工装email
	   $("#accountName_kf").val(theRequest['accountName']);//开发商手机
	   $("#realName_kf").val(theRequest['realName']);//开发商姓名
	   $("#email_kf").val(theRequest['email']);//开发商email
	   $("#accountName_vip").val(theRequest['accountName']);//vip手机
	   $("#realName_vip").val(theRequest['realName']);//vip姓名
	   $("#email_vip").val(theRequest['email']);//vip email
	 //注册  邀请注册消费
		$(".register_yqxf").click(function(){
			var companyTelephone = $("#accountName_xf").val();
			var password = $("#password_xf").val();
			if(companyTelephone == null || companyTelephone == ''){
				showTooltips('check_xf_accountName','请输入手机号');
				return;
			}
			if(companyTelephone.length != 11 || isNaN(companyTelephone)){
				showTooltips('check_xf_accountName','请输入正确的手机号码');
				return;
			}
			if(password == null || password == ''){
				showTooltips('check_xf_password','请输入密码');
				return;
			}
			$.getJSON(chinaweb+'/reguster/createReguster.action?&'+$("#kfRegisterForm").serialize()+"&jsoncallback=?",function(json){
				if(json.result=='success'){
					$('.popup_register_panel.success.consumer_success').show();
					$('.product_popup').show();
				}else if(json.result=='error'){
					alert('这个手机号码已经注册过了！');
				}	
			});
		});
		//注册  邀请注册家装
		$(".register_yqjz").click(function(){
			var companyTelephone = $("#accountName_jz").val();
			var password = $("#password_jz").val();
			var realName = $("#realName_jz").val();
			var email = $("#email_jz").val();
			if(companyTelephone == null || companyTelephone == ''){
				showTooltips('check_jz_accountName','请输入正确的手机号码');
				return;
			}
			if(companyTelephone.length != 11 || isNaN(companyTelephone)){
				showTooltips('check_jz_accountName','请输入正确的手机号码');
				return;
			}
			if(password == null || password == ''){
				showTooltips('check_jz_password','请输入正确的密码');
				return;
			}
			if(realName == null || realName == ''){
				showTooltips('check_jz_realName','请输入正确的姓名');
				return;
			}
			if(email == null || email == ''){
				showTooltips('check_jz_email','请输入正确的email');
				return;
			}
			$.getJSON(chinaweb+'/reguster/createReguster.action?&'+$("#jzRegisterForm").serialize()+"&jsoncallback=?",function(json){
				if(json.result=='success'){
					$('.popup_register_panel.success.member_success').show();
					$('.product_popup').show();
				}else if(json.result=='error'){
					alert('这个手机号码已经注册过了！');
				}	
			});
		});
		//注册  邀请注册工装
		$(".register_yqgz").click(function(){
			var companyTelephone = $("#accountName_gz").val();
			var password = $("#password_gz").val();
			var realName = $("#realName_gz").val();
			var email = $("#email_gz").val();
			if(companyTelephone == null || companyTelephone == ''){
				showTooltips('check_gz_accountName','请输入正确的手机号码');
				return;
			}
			if(companyTelephone.length != 11 || isNaN(companyTelephone)){
				showTooltips('check_gz_accountName','请输入正确的手机号码');
				return;
			}
			if(password == null || password == ''){
				showTooltips('check_gz_password','请输入正确的密码');
				return;
			}
			if(realName == null || realName == ''){
				showTooltips('check_gz_realName','请输入正确的姓名');
				return;
			}
			if(email == null || email == ''){
				showTooltips('check_gz_email','请输入正确的email');
				return;
			}
			$.getJSON(chinaweb+'/reguster/createReguster.action?&'+$("#gzRegisterForm").serialize()+"&jsoncallback=?",function(json){
				if(json.result=='success'){
					$('.popup_register_panel.success.member_success').show();
					$('.product_popup').show();
				}else if(json.result=='error'){
					alert('这个手机号码已经注册过了！');
				}	
			});
		});
		//注册  邀请注册工装
		$(".register_yqkf").click(function(){
			var companyTelephone = $("#accountName_kf").val();
			var password = $("#password_kf").val();
			var realName = $("#realName_kf").val();
			var email = $("#email_kf").val();     
			var companyName = $("#companyName_kf").val();
			var companyDepartment = $("#companyDepartment_kf").val();
			if(companyTelephone == null || companyTelephone == ''){
				showTooltips('check_kf_accountName','请输入正确的手机号码');
				return;
			}
			if(companyTelephone.length != 11 || isNaN(companyTelephone)){
				showTooltips('check_kf_accountName','请输入正确的手机号码');
				return;
			}
			if(password == null || password == ''){
				showTooltips('check_kf_password','请输入正确的密码');
				return;
			}
			if(realName == null || realName == ''){
				showTooltips('check_kf_realName','请输入正确的姓名');
				return;
			}
			if(email == null || email == ''){
				showTooltips('check_kf_email','请输入正确的email');
				return;
			}
			if(companyName == null || companyName == ''){
				showTooltips('check_kf_companyName','请输入公司');
				return;
			}
			if(companyDepartment == null || companyDepartment == ''){
				showTooltips('check_kf_companyDepartment','请输入部门');
				return;
			}
			$.getJSON(chinaweb+'/reguster/createReguster.action?&'+$("#kfRegisterForm").serialize()+"&jsoncallback=?",function(json){
				if(json.result=='success'){
					$('.popup_register_panel.success.member_success').show();
					$('.product_popup').show();
				}else if(json.result=='error'){
					alert('这个手机号码已经注册过了！');
				}	
			});
		});
		//注册  邀请注册工装
		$(".register_yqhvip").click(function(){
			var companyTelephone = $("#accountName_vip").val();
			var password = $("#password_vip").val();
			var realName = $("#realName_vip").val();
			var email = $("#email_vip").val();     
			var companyName = $("#companyName_vip").val();
			var companyDepartment = $("#companyDepartment_vip").val();
			if(companyTelephone == null || companyTelephone == ''){
				showTooltips('check_vip_accountName','请输入正确的手机号码');
				return;
			}
			if(companyTelephone.length != 11 || isNaN(companyTelephone)){
				showTooltips('check_vip_accountName','请输入正确的手机号码');
				return;
			}
			if(password == null || password == ''){
				showTooltips('check_vip_password','请输入正确的密码');
				return;
			}
			if(realName == null || realName == ''){
				showTooltips('check_vip_realName','请输入正确的姓名');
				return;
			}
			if(email == null || email == ''){
				showTooltips('check_vip_email','请输入正确的email');
				return;
			}
			$.getJSON(chinaweb+'/reguster/createReguster.action?&'+$("#vipRegisterForm").serialize()+"&jsoncallback=?",function(json){
				if(json.result=='success'){
					$('.popup_register_panel.success.member_success').show();
					$('.product_popup').show();
				}else if(json.result=='error'){
					alert('这个手机号码已经注册过了！');
				}	
			});
		});
})