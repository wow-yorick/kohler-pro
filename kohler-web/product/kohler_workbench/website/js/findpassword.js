$(function() {

	$('.title.forget').click(function() {
		$('.popup_register_panel').hide();
		$('.popup_register_panel.find_password.step_1').show();
	})
	// 顶部登录按钮
	$('.send_identify_code').click(function() {
		hideAllTooltips();
		var accountname = $('#findpasswordidentify input[name=accountName]').val();
		if(accountname=='' || !isMobilePhone(accountname)) {
			showTooltips('mobile_input','请输入正确的手机号码');
			return;
		}
		$.getJSON(chinaweb+"/account/find-password/sendIdentifyCode.action?accountName="+accountname+"&jsoncallback=?",function(json){
			if(json.result!='success'){
				showTooltips('mobile_input','手机号码未注册');
			}
		});
	})
	
	$('.popup_register_panel .register_btn.btn_step1').click(function() {
		//忘记密码手机验证
		var name = $('#findpasswordidentify input[name=accountName]').val();
		var code = $('#findpasswordidentify input[name=identifyCode]').val();
		hideAllTooltips();
		var ckh_result = true;
		if(name=='' || !isMobilePhone(name)) {
			showTooltips('mobile_input','请输入正确的手机号码');
			ckh_result = false;
		}
		if(code=='') {
			showTooltips('code_input','请输入验证码');
			ckh_result = false;
		}
		if(ckh_result==false){
			$('.popup_register_panel.step_1').show();
			return;
		}
		$.getJSON(chinaweb+"/account/find-password/identifyNameAndCode.action?"+$('#findpasswordidentify').serialize()+"&jsoncallback=?",function(json){
			if(json.result=='success'){
				
				$('.popup_register_panel').hide();
				$('#operation_error').hide();
				$('.popup_register_panel.step_2').show();
				$('#findandcofirmpassword input[name=accountName]').val(name);
				$('#findandcofirmpassword input[name=identifyCode]').val(code);
				
			}else if(json.result=='nameerror'){
				showTooltips('mobile_input','手机号码未注册');
				$('.popup_register_panel.step_1').show();
			}else{
				showTooltips('code_input','验证码不正确');
				$('.popup_register_panel.step_1').show();
			}
		});	
	})
	
	$('.popup_register_panel .register_btn.btn_step2').click(function() {
		$('#operation_error').hide();
		hideAllTooltips();
		var ckh_result = true;
		if($('#firstpassowrd_input').val()==''){
		
			showTooltips('first_password_input','请输入密码');
			ckh_result = false;
		}
		if($('#confirmpassowrd_input').val()==''){
			showTooltips('confirm_password_input','请再次输入密码');
			ckh_result = false;
			
		}else if($('#confirmpassowrd_input').val()!=$('#firstpassowrd_input').val()){
			showTooltips('confirm_password_input','两次密码不一致');
			ckh_result = false;
		}
		if(ckh_result==false){
			$('.popup_register_panel.step_2').show();
			return;
		}
		$.getJSON(chinaweb+"/account/find-password/changePassword.action?"+$('#findandcofirmpassword').serialize()+"&jsoncallback=?",function(json){
			if(json.result=='success'){
				$('.popup_register_panel').hide();
				$('.popup_register_panel.step_3').show();	
			}else{
				$('#operation_error').show();
				$('.popup_register_panel.step_2').show();
			}
		});
	})
	
})