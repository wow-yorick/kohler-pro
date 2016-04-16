$(function() {
	// 分割线高度
	var lineHeight = $('.register_panel').height();
	$('.register_ver_line').height(lineHeight);

	// 选择框
	$('.icon_select').click(function() {
		$(this).toggleClass('select1');
	})

	// 顶部登录按钮
	$('#login_btn').click(function() {
		$('.product_popup').show();
		$('.popup_register_panel.login').show();
		var lineHeight = $('.reg_consumer .register_panel').height();
		$('.reg_consumer .register_ver_line').height(lineHeight);
	})

	// 顶部注册按钮
	$('#register_btn').click(function() {
		$('.product_popup').show();
		$('.popup_register_panel.reg_consumer').show();
		var lineHeight = $('.reg_consumer .register_panel').height();
		$('.reg_consumer .register_ver_line').height(lineHeight);
	})

	// 关闭注册/登录弹出层
	$('.popup_register_panel .pop_close').click(function() {
		$('.product_popup').hide();
		$('.popup_register_panel').hide();
	})

	// 注册/登录弹出层切换
	$('.popup_register_panel .account_type').click(function() {
		var type = $(this).data('type');
		$('.popup_register_panel.'+type+'').show().siblings().hide();
		var lineHeight = $('.'+type+' .register_panel').height();
		$('.'+type+' .register_ver_line').height(lineHeight);
	})

	// 注册/登录弹出层确认按钮
	$('.popup_register_panel .register_btn').click(function() {
		$('.popup_register_panel').hide();
		if($(this).hasClass('consumer_login_btn')) {
			// 消费者
			$('.popup_register_panel.success.consumer_success').show();
		}else if($(this).hasClass('confirm_btn')) {
			// 注册成功确认
			$('.product_popup').hide();
		}else if($(this).hasClass('login_btn')) {
			// 登录
			$('.product_popup').hide();
		}else if(!$(this).hasClass('btn_step1')&&!$(this).hasClass('btn_step2')) {
			$('.popup_register_panel.success.member_success').show();
		}

	})

	// 外链/邀请注册页面确认按钮
	$('#reg_btn').click(function() {
		alert('a');
	})
})