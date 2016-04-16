$(function(){
	// 显示积分详情按钮
	$('.label_input_bar.scores_bar,.icon_scores').click(function() {
		$('.address_list_panel').toggle();
	})

	// 账户信息编辑按钮
	$('.btn_edit').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.edit_bar').show();
	})

	// 账户信息取消按钮
	$('#btn_cancel').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.info_bar').show();
	})

	// 账户信息保存按钮
	$('#btn_save').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.info_bar').show();
	})

	// 修改密码标签
	$('#tag_pw').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.pw_bar').show();
		$('.account_tag').removeClass('tag_selected');
		$(this).addClass('tag_selected');
	})

	// 修改手机号码标签
	$('#tag_mobile').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.mobile_bar').show();
		$('.account_tag').removeClass('tag_selected');
		$(this).addClass('tag_selected');
	})

	// 基本信息标签
	$('#tag_base').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.info_bar').show();
		$('.account_tag').removeClass('tag_selected');
		$(this).addClass('tag_selected');
	})

	// 密码/手机号码保存按钮
	$('.btn_save2').click(function() {
		$('.account_info_list').hide();
		$('.account_info_list.info_bar').show();
		$('.account_tag').removeClass('tag_selected');
		$('#tag_base').addClass('tag_selected');
	})
})